package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.SongDto;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper {

    @Select("SELECT COUNT(*) FROM t_list_song ls " +
            "LEFT JOIN t_user_song_list usl ON ls.`song_list_id`=usl.song_list_id " +
            " WHERE usl.`user_id`=${userId} AND ls.`song_id`=${songId} AND ls.`song_list_id`=${songListId}")
    Integer selectCountofCollectedSongByUser(@Param("userId") Integer userId, @Param("songId") Integer songId,@Param("songListId")  Integer songListId);

    @Select("SELECT COUNT(*) FROM t_song WHERE album_id = ${albumId}")
    Integer selectSongCountByAlbum(@Param("albumId") Integer albumId);

    @Select("SELECT COUNT(*) FROM t_song ts WHERE ts.album_id = ${albumId} AND ts.song_id  IN " +
            " (SELECT song_id FROM t_list_song WHERE song_list_id = ${songListId})")
    Integer selectSongCountByList(@Param("albumId")Integer albumId, @Param("songListId")Integer songListId);

    /*@Select("SELECT * FROM t_song ts WHERE ts.album_id = ${albumId} AND ts.song_id NOT IN  " +
            "(SELECT song_id FROM t_list_song WHERE song_list_id = ${songListId})")*/
    List<SongDto> selectSongsByAlbum(@Param("albumId")Integer albumId, @Param("songListId")Integer songListId);

    @Select("SELECT COUNT(*) FROM t_user_collection_song_list WHERE user_id=${userId} AND song_list_id=${songListId} ")
    Integer selectCollectSongListId(@Param("userId")Integer userId, @Param("songListId")Integer songListId);

    @Delete("DELETE  FROM t_user_singer WHERE user_id=${userId} AND singer_id=${singerId}")
    Integer deldelCollectedSinger(@Param("userId") Integer userId, @Param("singerId") Integer singerId);

    @Update("UPDATE t_singer SET collection_count=collection_count-1 WHERE singer_id=${singerId}")
    Integer reduceCountOfCollectedSinger(@Param("singerId") Integer singerId);

    @Select("SELECT COUNT(*) FROM t_user_singer WHERE user_id=${userId} AND singer_id=${singerId}")
    Integer isCollectSingerId(@Param("userId")Integer userId, @Param("singerId")Integer singerId);
}
