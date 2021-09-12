package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.AlbumDto;

import java.util.List;

@Mapper
@Repository
public interface AlbumMapper {

    int deleteByPrimaryKey(Integer albumId);

    int insert(AlbumDto record);

    AlbumDto selectByPrimaryKey(Integer albumId);

    List<AlbumDto> selectAll();

    int updateByPrimaryKey(AlbumDto record);

    List<AlbumDto> selectAlbumByName(String albumName);

    int updateAlbumStatus(Integer albumId);

    List<AlbumDto> selectAlbumBySinger(@Param("singerId") Integer singerId);

    List<AlbumDto> selectTopAlbum();

    @Select("SELECT COUNT(*) FROM t_album WHERE singer_id=${singerId}")
    Integer SelectCountBySinger(@Param("singerId")Integer singerId);

    @Update("UPDATE t_album SET collection_count=collection_count+1 WHERE album_id=${albumId}")
    Integer updateCollectionCount(@Param("albumId") Integer albumId);


}
