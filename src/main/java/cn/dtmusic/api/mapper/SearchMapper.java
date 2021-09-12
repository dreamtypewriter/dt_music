package cn.dtmusic.api.mapper;

import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Album;
import cn.dtmusic.api.entity.SongList;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/13
 * @ time:      14:59
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Repository
public interface SearchMapper {

    List<SongDto> selectSongsByName(String keyword);

    List<SongList> selectSongListsByName(String searchKeyword);

}
