package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.config.ResourceTypeConfig;
import cn.dtmusic.api.dto.CommentDto;
import cn.dtmusic.api.entity.*;
import cn.dtmusic.api.mapper.CommentMapper;
import cn.dtmusic.api.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/28
 * @ time:      17:46
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public PageInfo<CommentDto> getCommentDtoPage(Integer offset, Integer limit, String searchKeyword, Integer keywordType, Byte searchType, Integer searchStatus) {
        PageHelper.offsetPage(offset, limit);
        Integer searchId = null;
        if (keywordType == 1) {
            searchId = searchKeyword == null || searchKeyword.length() == 0 ? null : Integer.parseInt(searchKeyword);
            searchKeyword = null;
        }
        List<CommentDto> commentDtoList = commentMapper.selectByCondition(searchId, searchKeyword, searchType, searchStatus);
        for (CommentDto dto : commentDtoList) {
            dto.setTypeName(ResourceTypeConfig.getResourceTypeName(dto.getTypeId()));
        }
        return new PageInfo<>(commentDtoList);
    }

    @Override
    @Transactional
    public Integer disableComment(Integer commentId) {
        return commentMapper.disableComment(commentId);
    }

    @Override
    @Transactional
    public Integer enableComment(Integer commentId) {
        return commentMapper.enableComment(commentId);
    }

    @Override
    public PageInfo<Comment> getCommentPage(Integer pageNum, Integer pageSize, Byte resourceType, Integer resourceId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.selectByResourceId(resourceType, resourceId);
        return new PageInfo<>(comments);
    }

    @Override
    @Transactional
    public Integer addComment(Comment comment) {
        int update = updateCommentCount(comment.getTypeId(), comment.getResourceId());
        int insert = commentMapper.insert(comment);
        return update * insert;
    }

    @Override
    public Integer addAdminLike(Comment comment) {
        commentMapper.addAdminLike(comment);
        return comment.getAdminLike();
    }

    @Override
    public Integer clearAdminLike(Comment comment) {
        commentMapper.clearAdminLike(comment);
        return comment.getAdminLike();
    }

    private int updateCommentCount(Byte resourceType, Integer resourceId) {
        switch (resourceType) {
            case ResourceTypeConfig
                    .TYPE_SONG:
                Song song = new Song();
                song.setSongId(resourceId);
                commentMapper.updateSongCommentCount(song);
                return song.getCommentCount();
            case ResourceTypeConfig
                    .TYPE_SONG_LIST:
                SongList songList = new SongList();
                songList.setSongListId(resourceId);
                commentMapper.updateSongListCommentCount(songList);
                return songList.getCommentCount();
            case ResourceTypeConfig
                    .TYPE_ALBUM:
                Album album = new Album();
                album.setAlbumId(resourceId);
                commentMapper.updateAlbumCommentCount(album);
                return album.getCommentCount();
            case ResourceTypeConfig
                    .TYPE_SHARE:
                Share share = new Share();
                share.setShareId(resourceId);
                commentMapper.updateShareCommentCount(share);
                return share.getCommentCount();
            default:
                return 0;
        }
    }
}
