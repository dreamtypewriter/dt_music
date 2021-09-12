package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.CommentDto;
import cn.dtmusic.api.entity.Comment;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.CommentService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ description:
 * @ date:      2020/9/28
 * @ time:      19:53
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public PageInfo<CommentDto> getCommentsByPage(Integer offset, Integer limit, String searchKeyword, Integer keywordType, Byte searchType, Integer searchStatus) {
        return commentService.getCommentDtoPage(offset, limit, searchKeyword, keywordType, searchType, searchStatus);
    }

    @DeleteMapping("/d/{commentId}")
    public Integer disableComment(@PathVariable Integer commentId) {
        return commentService.disableComment(commentId);
    }

    @DeleteMapping("/e/{commentId}")
    public Integer enableComment(@PathVariable Integer commentId) {
        return commentService.enableComment(commentId);
    }

    @GetMapping("/list")
    public PageInfo<Comment> getCommentPage(Byte resourceType, Integer resourceId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentPage(pageNum, pageSize, resourceType, resourceId);
    }

    @PostMapping("/add")
    public Integer addComment(@RequestBody Comment comment) {
        User userOnline = (User) SecurityUtils.getSubject().getPrincipal();
        comment.setUserId(userOnline.getUserId());
        comment.setUserAvatar(userOnline.getUserAvatar());
        comment.setUserNickname(userOnline.getUserNickname());
        return commentService.addComment(comment);
    }

    @PostMapping("/adminLike/{commentID}")
    public Integer addAdminLike(@PathVariable Integer commentID) {
        Comment comment = new Comment();
        comment.setCommentId(commentID);
        return commentService.addAdminLike(comment);
    }

    @PostMapping("/clearAdminLike/{commentID}")
    public Integer clearAdminLike(@PathVariable Integer commentID) {
        Comment comment = new Comment();
        comment.setCommentId(commentID);
        return commentService.clearAdminLike(comment);
    }

}
