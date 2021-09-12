package cn.dtmusic.api.mapper;

import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Reply;

import java.util.List;

@Repository
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    Reply selectByPrimaryKey(Integer replyId);

    List<Reply> selectAll();

    int updateByPrimaryKey(Reply record);

    List<Reply> selectByCommentId(Integer commentId);
}
