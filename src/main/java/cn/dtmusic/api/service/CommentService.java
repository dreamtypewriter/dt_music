package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.CommentDto;
import cn.dtmusic.api.entity.Comment;

/**
 * @ description:
 * @ date:      2020/9/28
 * @ time:      17:34
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface CommentService {
    PageInfo<CommentDto> getCommentDtoPage(Integer offset, Integer limit, String searchKeyword,
                                           Integer keywordType, Byte searchType, Integer searchStatus);

    Integer disableComment(Integer commentId);

    Integer enableComment(Integer commentId);

    PageInfo<Comment> getCommentPage(Integer pageNum, Integer pageSize, Byte resourceType, Integer resourceId);

    Integer addComment(Comment comment);

    Integer addAdminLike(Comment comment);

    Integer clearAdminLike(Comment comment);
}
