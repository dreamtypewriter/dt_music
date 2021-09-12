package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.CommentDto;
import cn.dtmusic.api.entity.*;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<CommentDto> selectByCondition(@Param("searchId") Integer searchId,
                                       @Param("searchKeyword") String searchKeyword,
                                       @Param("searchType") Byte searchType,
                                       @Param("searchStatus") Integer searchStatus);

    Integer disableComment(Integer commentId);

    Integer enableComment(Integer commentId);

    List<Comment> selectByResourceId(@Param("resourceTye") Byte resourceTye,
                                     @Param("resourceId") Integer resourceId);

    void updateSongCommentCount(Song song);

    void updateSongListCommentCount(SongList songList);

    void updateAlbumCommentCount(Album album);

    void updateShareCommentCount(Share share);

    void updateReplyCount(Comment comment);

    void addAdminLike(Comment comment);

    void clearAdminLike(Comment comment);
}
