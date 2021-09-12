package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dtmusic.api.config.ResourceTypeConfig;
import cn.dtmusic.api.entity.Comment;
import cn.dtmusic.api.entity.Like;
import cn.dtmusic.api.entity.Reply;
import cn.dtmusic.api.entity.Share;
import cn.dtmusic.api.mapper.LikeMapper;
import cn.dtmusic.api.service.LikeService;

/**
 * @ description:
 * @ date:      2020/10/7
 * @ time:      15:13
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeMapper likeMapper;

    @Autowired
    public LikeServiceImpl(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }


    @Override
    @Transactional
    public int changeLikeCount(Like like) {
        Like oldLike = likeMapper.selectByCondition(like.getUserId(), like.getResourceType(), like.getResourceId());
        if (oldLike != null && oldLike.getLikeStatus() < 1) {
            oldLike.setLikeStatus((byte) 1);
            int update = likeMapper.updateByPrimaryKey(oldLike);
            int count = this.addLikeCount(oldLike);
            return update * count;
        } else if (oldLike != null && oldLike.getLikeStatus() > 0) {
            oldLike.setLikeStatus((byte) 0);
            int update = likeMapper.updateByPrimaryKey(oldLike);
            int count = this.reduceLikeCount(oldLike);
            return update * count;
        }
        int insert = likeMapper.insert(like);
        int update = this.addLikeCount(like);
        return insert * update;
    }

    @Override
    public int getLikeCount(byte resourceType, int resourceId) {
        return likeMapper.selectCount(resourceType, resourceId);
    }

    private int reduceLikeCount(Like like) {
        switch (like.getResourceType()) {
            case ResourceTypeConfig
                    .TYPE_SHARE:
                Share share = new Share();
                share.setShareId(like.getResourceId());
                likeMapper.reduceShareLikeCount(share);
                return share.getLikeCount();
            case ResourceTypeConfig
                    .TYPE_COMMENT:
                Comment comment = new Comment();
                comment.setCommentId(like.getResourceId());
                likeMapper.reduceCommentUserLike(comment);
                return comment.getUserLike();
            case ResourceTypeConfig
                    .TYPE_REPLY:
                Reply reply = new Reply();
                reply.setReplyId(like.getResourceId());
                likeMapper.reduceReplyLikeCount(reply);
                return reply.getLikeCount();
            default:
                return 0;
        }
    }

    private int addLikeCount(Like like) {
        switch (like.getResourceType()) {
            case ResourceTypeConfig
                    .TYPE_SHARE:
                Share share = new Share();
                share.setShareId(like.getResourceId());
                likeMapper.addShareLikeCount(share);
                return share.getLikeCount();
            case ResourceTypeConfig
                    .TYPE_COMMENT:
                Comment comment = new Comment();
                comment.setCommentId(like.getResourceId());
                likeMapper.addCommentUserLike(comment);
                return comment.getUserLike();
            case ResourceTypeConfig
                    .TYPE_REPLY:
                Reply reply = new Reply();
                reply.setReplyId(like.getResourceId());
                likeMapper.addReplyLikeCount(reply);
                return reply.getLikeCount();
            default:
                return 0;
        }
    }
}
