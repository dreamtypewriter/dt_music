package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.SongListCategory;

import java.util.List;


@Mapper
@Repository
public interface SongListMapper {

    int deleteByPrimaryKey(@Param("songListId") Integer songListId);

    int insert(SongList record);

    SongList selectByPrimaryKey(@Param("songListId") Integer songListId);

    List<SongList> selectAll();

    int updateByPrimaryKey(SongList record);

    List<SongList> selectSongListsByUser(@Param("userId") Integer userId);

    SongList selectSongListsById(@Param("songListId") Integer songListId, @Param("userId") Integer userId);

    SongList selectCollectedSingleSongListById(@Param("songListId") Integer songListId, @Param("userId") Integer userId);

    @Insert("INSERT INTO t_user_song_list(user_id,song_list_id) VALUES (${userId},${songListId})")
    Integer insertSongListAndUser(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    @Delete("delete from t_user_song_list where song_list_id = ${songListId}")
    void deleteSongListAndUser(@Param("songListId") Integer id);

    @Insert("INSERT INTO t_list_song(song_id,song_list_id) VALUES(${songId},${songListId})")
    Integer insertSongToSongListByUser(@Param("songId") Integer songId, @Param("songListId") Integer songListId);

    @Insert("INSERT INTO t_user_collection_song_list (user_id,song_list_id) VALUES(${userId},${songListId})")
    Integer insertSongListToCollection(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    @Update("UPDATE t_song_list SET collection_count=collection_count+1 WHERE song_list_id=${songListId}")
    Integer updateCollectionCount(@Param("songListId") Integer songListId);

    @Select("SELECT * FROM t_user_collection_song_list WHERE user_id=${userId} AND song_list_id=${songListId}")
    SongList selectSingleSongListByCollection(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    List<SongList> selectCollectedSongListsByUser(@Param("userId") Integer userId);

    @Select("SELECT COUNT(*) FROM  t_user_collection_song_list us " +
            " WHERE us.user_id=${userId} AND us.song_list_id=${songListId}")
    Integer selectCollectCountofSingleSongListByUser(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    @Select("SELECT COUNT(*)  FROM t_song_list sl WHERE sl.user_id=${userId} AND sl.song_list_id=${songListId}")
    Integer selectSingleSongListByUser(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    @Delete(" DELETE FROM t_user_collection_song_list WHERE song_list_id = ${songListId} AND user_id=${userId}")
    Integer delCollectedSingleSongListByUser(@Param("userId") Integer userId, @Param("songListId") Integer songListId);

    @Delete("DELETE FROM t_list_song WHERE song_list_id = ${songListId} AND song_id=${songId}")
    Integer delSongFromSongList(@Param("songId") Integer songId, @Param("songListId") Integer songListId);

    List<SongList> selectSongList(@Param("size") Integer size);

    @Select("SELECT sl.song_list_id FROM t_song_list sl" +
            " WHERE sl.song_list_name='我喜欢的音乐' AND sl.user_id=${userId}")
    Integer getIdOfOriginalSongList(@Param("userId") Integer userId);

    @Update("UPDATE t_song_list SET song_count=song_count+1 WHERE song_list_id=${songListId}")
    Integer updateSongCount(@Param("songListId") Integer songListId);

    @Update("UPDATE t_song_list SET song_count=song_count-1 WHERE song_list_id=${songListId}")
    Integer reduceSongCount(@Param("songListId") Integer songListId);

    List<SongList> selectSongListByPage(Integer categoryId);

    Integer insertSongListCates(List<SongListCategory> songListCategories);

    void deleteSongListCates(Integer songListId);
}
