package cn.dtmusic.api.mapper;

import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.SongList;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/14
 * @ time:      22:34
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Repository
public interface PlayMusicMapper {
    void addPlayCount(SongDto songDto);

    void addSongsPlayCount(List<Integer> integers);

    void addSongListPlayCount(SongList songList);
}
