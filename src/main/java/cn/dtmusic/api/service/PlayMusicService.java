package cn.dtmusic.api.service;

import java.util.List;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.SongList;

/**
 * @ description:
 * @ date:      2020/10/14
 * @ time:      22:31
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface PlayMusicService {
    int addSongPlayCount(SongDto songDto);

    void addSongsPlayCount(List<SongDto> songDtos);

    int addSongListPlayCount(SongList songList);
}
