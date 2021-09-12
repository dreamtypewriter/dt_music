package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Comment;
import cn.dtmusic.api.entity.Like;
import cn.dtmusic.api.entity.Reply;
import cn.dtmusic.api.entity.Share;

import java.util.List;

@Repository
public interface LikeMapper {

    int insert(Like record);

    Like selectByPrimaryKey(Integer likeId);

    List<Like> selectAll();

    int updateByPrimaryKey(Like record);

    void reduceShareLikeCount(Share share);

    void reduceCommentUserLike(Comment comment);

    void reduceReplyLikeCount(Reply reply);

    void addShareLikeCount(Share share);

    void addCommentUserLike(Comment comment);

    void addReplyLikeCount(Reply reply);

    Like selectByCondition(@Param("userId") Integer userId,
                           @Param("resourceType") Byte resourceType,
                           @Param("resourceId") Integer resourceId);

    int selectCount(@Param("resourceType") byte resourceType,
                    @Param("resourceId") int resourceId);
}
