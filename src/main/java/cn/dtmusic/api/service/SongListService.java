package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.SongList;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/26
 * @ time:      21:24
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */

public interface SongListService {


    public Integer addSongList(SongList songList);

    public Integer delSingleSongList(Integer id);

    public Integer modifySongList(SongList songList, String cateIds);

    List<SongList> findSongListsByUser(Integer userId);

    SongList findSingleSongListById(Integer songListId, Integer userId);

    Integer addSongToSongList(Integer userId, Integer songId, Integer songListId);

    Integer collectSongListByUser(Integer userId, Integer songListId);

    SongList findSingleSongListByCollection(Integer userId, Integer songListId);

    List<SongList> findCollectedSongListsByUser(Integer userId);

    SongList findCollectedSingleSongListById(Integer songListId, Integer userId);

    SongList findSingleSongList(Integer id);


    Integer delCollectedSingleSongList(Integer userId, Integer songListId);

    Integer delSongFromSongList(Integer songId, Integer songListId);

    Integer isCollectSongListByUser(Integer userId, Integer songListId);

    List<SongList> getRecommendedSongList(int size);

    Integer getIdOfOriginalSongList(Integer userId);

    PageInfo<SongList> selectSongListsByCon(Integer pageNum, Integer pageSize, Integer categoryId);
}
