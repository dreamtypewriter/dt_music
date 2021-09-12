package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.mapper.AlbumMapper;
import cn.dtmusic.api.mapper.CollectionMapper;
import cn.dtmusic.api.mapper.SongListMapper;
import cn.dtmusic.api.mapper.SongMapper;
import cn.dtmusic.api.service.CollectionService;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/13
 * @since 1.0.0
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    SongListMapper songListMapper;

    @Autowired
    SongMapper songMapper;
    @Autowired
    AlbumMapper albumMapper;

    @Override
    public Boolean isCollectSongByUser(Integer userId, Integer songId, Integer songListId) {
        return collectionMapper.selectCountofCollectedSongByUser(userId,songId,songListId)==0;
    }

    @Override
    public Boolean isCollectAlbumByUser(Integer userId, Integer albumId, Integer songListId) {
        //专辑歌总数
        Integer songCountByAlbum = collectionMapper.selectSongCountByAlbum(albumId);
        //在歌单上的专辑歌总数
        Integer songCountByList = collectionMapper.selectSongCountByList(albumId, songListId);
        //歌单的歌和专辑的歌数相等,则判断已被收藏
        if(songCountByAlbum!=songCountByList){
            return  true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public Integer collectAlbumByUser(Integer userId, Integer albumId, Integer songListId) {
        List<SongDto> songs=collectionMapper.selectSongsByAlbum(albumId,songListId);
        Integer IfCol=0;
        if (albumMapper.updateCollectionCount(albumId)>0){
            for (SongDto song:songs){
                songListMapper.updateSongCount(songListId);
                if(songListMapper.insertSongToSongListByUser(song.getSongId(),songListId)>0){
                    IfCol=1;
                }
            }
        }
        return IfCol;
    }

    @Override
    public Boolean isCollectSongListId(Integer userId, Integer songListId) {
        Integer id=collectionMapper.selectCollectSongListId(userId,songListId);
        return id!=0;
    }

    @Override
    @Transactional
    public Integer delCollectedSinger(Integer userId, Integer singerId) {
        Integer delCollectedSinger = collectionMapper.deldelCollectedSinger(userId, singerId);
        Integer delCollectedCount=collectionMapper.reduceCountOfCollectedSinger(singerId);
        if(delCollectedSinger==1&&delCollectedCount==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Boolean isCollectSingerId(Integer userId, Integer singerId) {
        Integer id=collectionMapper.isCollectSingerId(userId,singerId);
        return id!=0;
    }
}
