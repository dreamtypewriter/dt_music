package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Reply;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.ReplyService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ description:
 * @ date:      2020/10/6
 * @ time:      16:08
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{commentId}/{pageNum}")
    public PageInfo<Reply> getReplyPage(@PathVariable Integer commentId, @PathVariable Integer pageNum) {
        return replyService.getReplyPageByCommentId(pageNum, 5, commentId);
    }

    @PutMapping("")
    public Integer addReply(@RequestBody Reply reply) {
        User userOnline = (User) SecurityUtils.getSubject().getPrincipal();
        reply.setUserId(userOnline.getUserId());
        reply.setUserNickname(userOnline.getUserNickname());
        reply.setUserAvatar(userOnline.getUserAvatar());
        return replyService.addReply(reply);
    }
}
