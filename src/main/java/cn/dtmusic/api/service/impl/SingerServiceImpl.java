package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.mapper.AlbumMapper;
import cn.dtmusic.api.mapper.SingerMapper;
import cn.dtmusic.api.mapper.SongMapper;
import cn.dtmusic.api.service.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/27
 * @ time:      20:17
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapper singerMapper;
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    SongMapper songMapper;


    @Override
    public Singer getSingerById(Integer singerId) {
        List<AlbumDto> albums = albumMapper.selectAlbumBySinger(singerId);
        List<SongDto> songs = songMapper.selectTopCollectedSongsBySinger(singerId);
        Singer singer = singerMapper.selectByPrimaryKey(singerId);
        singer.setAlbums(albums);
        singer.setSongs(songs);
        return singer;
    }

    @Override
    public Integer addSinger(Singer singer) {
        return singerMapper.insert(singer);
    }

    @Override
    public Integer updateSinger(Singer singer) {
        return singerMapper.updateByPrimaryKey(singer);
    }

    @Override
    public Integer removeSinger(Integer singerId) {
        return singerMapper.disableSinger(singerId);
    }

    @Override
    public PageInfo<Singer> getSingerByPage(Integer offset, Integer limit,
                                            String singerName, String singerType, String singerLanguage) {
        PageHelper.offsetPage(offset, limit);
        List<Singer> singers = singerMapper.selectByPage(singerName, singerType, singerLanguage);
        return new PageInfo<>(singers);
    }

    @Override
    public List<Singer> showCollectedSingerByUser(Integer userId) {
        List<Singer> singers = singerMapper.selectCollectedSingerByUser(userId);
        for (Singer singer : singers) {
            singer.setSongCount(songMapper.SelectCountBySinger(singer.getSingerId()));
            singer.setAlbumCount(albumMapper.SelectCountBySinger(singer.getSingerId()));
        }
        return singers;
    }

    @Override
    public Integer collectSingerByUser(Integer userId, Integer singerId) {
        if (singerMapper.insertSingerColllection(userId, singerId) == 1) {
            return singerMapper.updateCollectionCount(singerId);
        } else {
            return 0;
        }

    }

    @Override
    public Boolean isCollectSingerByUser(Integer userId, Integer singerId) {

        return singerMapper.selectCollectCountofSingleSingerByUser(userId, singerId) == 0;
    }

    @Override
    public List<Singer> findTopSingers() {
        return singerMapper.selectTopSinger();
    }

}
