package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.mapper.SongMapper;
import cn.dtmusic.api.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 猪九戒
 * @create 2020-10-06
 * @since 1.0.0
 */
@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;

    @Autowired
    public SongServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    @Override
    public int removeSongPrimaryKey(Integer songId) {
        return songMapper.deleteByPrimaryKey(songId);
    }

    @Override
    public int addSong(SongDto songDto) {
        return songMapper.insert(songDto);
    }

    @Override
    public int modifySong(SongDto songDto) {
        return songMapper.updateByPrimaryKey(songDto);
    }

    @Override
    public int modifySongStatus(Integer songId) {
        return songMapper.updateSongStatus(songId);
    }

    @Override
    public SongDto findSingleSongByPrimaryKey(Integer songId) {

        return songMapper.selectByPrimaryKey(songId);
    }

    @Override
    public List<SongDto> findAllSong() {
        return songMapper.selectAll();
    }

    @Override
    public PageInfo<SongDto> findSongByPage(Integer offset, Integer limit, String songName, String singerName) {
        PageHelper.offsetPage(offset, limit);
        List<SongDto> songs = songMapper.selectSongsByPage(songName,singerName);
        return new PageInfo<SongDto>(songs);
    }

    @Override
    public Boolean isCollectSingerByUser(Integer userId, Integer songId, Integer songListId) {
        return  songMapper.selectCollectCountofSingleSingerByUser(userId, songId,songListId)==0;
    }

    @Override
    public List<SongDto> findTopSong() {
        return songMapper.selectTopSong();
    }
}
