package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.SongDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {

    //根据SongId删除Song
    int removeSongPrimaryKey(Integer songId);

    //添加Song对象
    int addSong(SongDto songDto);

    //修改Song对象
    int modifySong(SongDto songDto);

    //修改Song对象状态
    int modifySongStatus(Integer songId);

    //查询单个Song对象
    SongDto findSingleSongByPrimaryKey(Integer songId);

    //查询出所有的Song对象
    List<SongDto> findAllSong();

    //分页查询
    PageInfo<SongDto> findSongByPage(Integer offset, Integer limit, String songName, String singerName);

    Boolean isCollectSingerByUser(Integer userId, Integer songId, Integer songListId);

    List<SongDto> findTopSong();
}
