package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Category;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.SongListCategory;
import cn.dtmusic.api.mapper.CategoryMapper;
import cn.dtmusic.api.mapper.SongListMapper;
import cn.dtmusic.api.mapper.SongMapper;
import cn.dtmusic.api.service.SongListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/26
 * @ time:      21:25
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    SongListMapper songListMapper;
    @Autowired
    SongMapper songMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<SongList> findSongListsByUser(Integer userId) {
        List<SongList> songLists = songListMapper.selectSongListsByUser(userId);
        return songLists;
    }

    @Override
    public List<SongList> findCollectedSongListsByUser(Integer userId) {
        List<SongList> songLists = songListMapper.selectCollectedSongListsByUser(userId);
        return songLists;
    }


    @Override
    public SongList findSingleSongListById(Integer songListId, Integer userId) {
        List<SongDto> songs = songMapper.selectSongsBySongList(songListId);
        List<Category> categories = categoryMapper.selectCategoriesBySongList(songListId);
        SongList songList = songListMapper.selectSongListsById(songListId, userId);
        songList.setSongs(songs);
        songList.setCategories(categories);
        return songList;
    }

    @Override
    public SongList findCollectedSingleSongListById(Integer songListId, Integer userId) {
        List<SongDto> songs = songMapper.selectSongsBySongList(songListId);
        List<Category> categories = categoryMapper.selectCategoriesBySongList(songListId);
        SongList songList = songListMapper.selectCollectedSingleSongListById(songListId, userId);
        songList.setSongs(songs);
        songList.setCategories(categories);
        return songList;
    }

    @Override
    public SongList findSingleSongList(Integer songListId) {
        List<SongDto> songs = songMapper.selectSongsBySongList(songListId);
        List<Category> categories = categoryMapper.selectCategoriesBySongList(songListId);
        SongList songList = songListMapper.selectByPrimaryKey(songListId);
        songList.setSongs(songs);
        songList.setCategories(categories);
        return songList;
    }


    @Override
    public Integer isCollectSongListByUser(Integer userId, Integer songListId) {
        //确认是否在用户创建的歌单上
        if (songListMapper.selectSingleSongListByUser(userId, songListId) == 0) {
            //确认是否已被用户收藏
            Integer integer = songListMapper.selectCollectCountofSingleSongListByUser(userId, songListId);
            if (songListMapper.selectCollectCountofSingleSongListByUser(userId, songListId) == 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer delCollectedSingleSongList(Integer userId, Integer songListId) {
        return songListMapper.delCollectedSingleSongListByUser(userId, songListId);
    }

    @Override
    @Transactional
    public Integer delSongFromSongList(Integer songId, Integer songListId) {
        Integer delSong = songListMapper.delSongFromSongList(songId, songListId);
        Integer reduce = songMapper.reduceCollectionCount(songId);
        Integer reduceSongCount = songListMapper.reduceSongCount(songListId);
        if (delSong == 1 && reduce == 1 && reduceSongCount == 1) {
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    @Transactional
    public Integer addSongToSongList(Integer userId, Integer songId, Integer songListId) {
        Integer insert = songListMapper.insertSongToSongListByUser(songId, songListId);
        Integer update = songListMapper.updateSongCount(songListId);
        if (insert == 1 && update == 1) {
            return songMapper.updateCollectionCount(songId);
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer collectSongListByUser(Integer userId, Integer songListId) {
        if (songListMapper.insertSongListToCollection(userId, songListId) == 1) {
            return songListMapper.updateCollectionCount(songListId);
        } else {
            return 0;
        }
    }

    @Override
    public SongList findSingleSongListByCollection(Integer userId, Integer songListId) {
        return songListMapper.selectSingleSongListByCollection(userId, songListId);
    }


    @Override
    @Transactional
    public Integer addSongList(SongList songList) {
        if (songListMapper.insert(songList) == 1) {
            songListMapper.insertSongListAndUser(songList.getUserId(), songList.getSongListId());
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer delSingleSongList(Integer id) {
        if (songListMapper.deleteByPrimaryKey(id) == 1) {
            songListMapper.deleteSongListAndUser(id);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer modifySongList(SongList songList, String cateIds) {
        String[] split = null;
        if (cateIds != null && !cateIds.isEmpty()) {
            split = cateIds.split(",");
        }
        List<SongListCategory> songListCategories = new ArrayList<>();
        if (cateIds != null && !cateIds.isEmpty()) {
            for (String s : split) {
                songListCategories.add(new SongListCategory(songList.getSongListId(), Integer.parseInt(s)));
            }
        }

        if (songListMapper.updateByPrimaryKey(songList) == 1) {
            songListMapper.deleteSongListCates(songList.getSongListId());
            return songListMapper.insertSongListCates(songListCategories);
        } else {
            return 0;
        }
    }

    @Override
    public List<SongList> getRecommendedSongList(int size) {
        return songListMapper.selectSongList(size);
    }

    @Override
    public Integer getIdOfOriginalSongList(Integer userId) {
        return songListMapper.getIdOfOriginalSongList(userId);
    }

    @Override
    public PageInfo<SongList> selectSongListsByCon(Integer pageNum, Integer pageSize, Integer categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        List<SongList> songLists = songListMapper.selectSongListByPage(categoryId);
        return new PageInfo<>(songLists);
    }
}
