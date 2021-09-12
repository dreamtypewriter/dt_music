package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.SongDto;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {

    int deleteByPrimaryKey(Integer songId);

    int insert(SongDto record);

    SongDto selectByPrimaryKey(Integer songId);

    List<SongDto> selectAll();

    int updateByPrimaryKey(SongDto record);

    SongDto selectBySongName(String songName);

    List<SongDto> selectSongsByName(@Param("songName") String songName);

    List<SongDto> selectSongsByPage(@Param("songName") String songName, @Param("singerName") String singerName);

    int updateSongStatus(Integer songId);

    List<SongDto> selectSongsBySongList(@Param("songListId")Integer songListId);


    List<SongDto> selectTopCollectedSongsBySinger(@Param("singerId")Integer singerId);

    @Update("UPDATE t_song SET collection_count=collection_count+1 WHERE song_id=${songId}")
    Integer updateCollectionCount(@Param("songId") Integer songId);

    @Select("SELECT COUNT(*) FROM t_song WHERE singer_id=${singerId}")
    Integer SelectCountBySinger(@Param("singerId") Integer singerId);

    List<SongDto> selectSongsByAlbum(@Param("albumId")Integer albumId);

    @Select("SELECT COUNT(*) FROM  t_user_collection_song_list ucs LEFT JOIN t_list_song ls" +
            " ON ls.`song_list_id`=ucs.song_list_id" +
            " WHERE ucs.user_id=${userId} AND ucs.song_list_id=${songListId} AND ls.song_id=${songId}")
    Integer selectCollectCountofSingleSingerByUser(@Param("userId") Integer userId, @Param("songId") Integer songId,@Param("songListId") Integer songListId);

    @Update("UPDATE t_song SET collection_count=collection_count-1 WHERE song_id=${songId}")
    Integer reduceCollectionCount( @Param("songId")Integer songId);

    List<SongDto> selectTopSong();

}
