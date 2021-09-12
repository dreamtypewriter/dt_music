package cn.dtmusic.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.dtmusic.api.entity.Song;
import cn.dtmusic.api.entity.SongList;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/12
 * @ time:      10:53
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Repository
public interface RecommendMapper {

    List<Song> selectSongs(int size);

}
