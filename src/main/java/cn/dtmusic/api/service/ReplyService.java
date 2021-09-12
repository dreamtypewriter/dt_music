package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Reply;

/**
 * @ description:
 * @ date:      2020/10/6
 * @ time:      16:10
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface ReplyService {
    PageInfo<Reply> getReplyPageByCommentId(Integer pageNum, Integer pageSize, Integer commentId);

    Integer addReply(Reply reply);
}
