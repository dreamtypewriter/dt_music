package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Comment;
import cn.dtmusic.api.entity.Reply;
import cn.dtmusic.api.mapper.CommentMapper;
import cn.dtmusic.api.mapper.ReplyMapper;
import cn.dtmusic.api.service.ReplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/6
 * @ time:      16:12
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyMapper replyMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public ReplyServiceImpl(ReplyMapper replyMapper, CommentMapper commentMapper) {
        this.replyMapper = replyMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public PageInfo<Reply> getReplyPageByCommentId(Integer pageNum, Integer pageSize, Integer commentId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Reply> replyList = replyMapper.selectByCommentId(commentId);
        return new PageInfo<>(replyList);
    }

    @Override
    @Transactional
    public Integer addReply(Reply reply) {
        int insert = replyMapper.insert(reply);
        Comment comment = new Comment();
        comment.setCommentId(reply.getCommentId());
        commentMapper.updateReplyCount(comment);
        return insert * comment.getReplyCount();
    }
}
