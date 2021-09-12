package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.mapper.PlayMusicMapper;
import cn.dtmusic.api.service.PlayMusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/14
 * @ time:      22:31
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class PlayMusicServiceImpl implements PlayMusicService {
    private final PlayMusicMapper playMusicMapper;

    @Autowired
    public PlayMusicServiceImpl(PlayMusicMapper playMusicMapper) {
        this.playMusicMapper = playMusicMapper;
    }

    @Override
    @Transactional
    public int addSongPlayCount(SongDto songDto) {
        playMusicMapper.addPlayCount(songDto);
        return songDto.getPlayCount();
    }

    @Override
    @Transactional
    public void addSongsPlayCount(List<SongDto> songDtos) {
        List<Integer> integers = new ArrayList<>();
        for (SongDto dto : songDtos) {
            integers.add(dto.getSongId());
        }
        if (integers.size() > 0) {
            playMusicMapper.addSongsPlayCount(integers);
        }
    }

    @Override
    @Transactional
    public int addSongListPlayCount(SongList songList) {
        playMusicMapper.addSongListPlayCount(songList);
        return songList.getPlayCount();
    }
}
