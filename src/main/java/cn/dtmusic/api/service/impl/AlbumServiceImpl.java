package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.mapper.AlbumMapper;
import cn.dtmusic.api.mapper.SingerMapper;
import cn.dtmusic.api.mapper.SongListMapper;
import cn.dtmusic.api.mapper.SongMapper;
import cn.dtmusic.api.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 83979
 * @create 2020-09-28
 * @since 1.0.0
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    SongMapper songMapper;

    @Autowired
    SongListMapper songListMapper;

    @Override
    public int removeAlbumByPrimaryKey(Integer albumId) {
        return albumMapper.deleteByPrimaryKey(albumId);
    }

    @Override
    public int addAlbum(AlbumDto album) {
        return albumMapper.insert(album);
    }

    @Override
    public int modifyAlbumStatus(Integer albumId) {
        return albumMapper.updateAlbumStatus(albumId);
    }

    @Override
    public int modifyAlbum(AlbumDto album) {
        return albumMapper.updateByPrimaryKey(album);
    }

    @Override
    public AlbumDto findSingleAlbumByPrimaryKey(Integer albumId) {
        AlbumDto album = albumMapper.selectByPrimaryKey(albumId);
        List<SongDto> songs=songMapper.selectSongsByAlbum(albumId);
        album.setSongs(songs);
        return album;
    }

    @Override
    public List<AlbumDto> findAllAlbum() {
        return albumMapper.selectAll();
    }

    @Override
    public PageInfo<AlbumDto> findAlbumByPage(Integer offset, Integer limit, String albumName) {
        PageHelper.offsetPage(offset, limit);
        List<AlbumDto> albums = albumMapper.selectAlbumByName(albumName);
        return new PageInfo<AlbumDto>(albums);
    }

    @Override
    public Integer collectAlbumByUser(Integer userId, Integer albumId, Integer songListId) {
        List<SongDto> songs = songMapper.selectSongsByAlbum(albumId);
        Integer IfCol=0;
        if (albumMapper.updateCollectionCount(albumId)>0){
            for (SongDto song:songs){
                if(songListMapper.insertSongToSongListByUser(song.getSongId(),songListId)>0){
                    IfCol=1;
                }
            }
        }
        return IfCol;
    }

    @Override
    public List<AlbumDto> findTopAlbum() {
        return albumMapper.selectTopAlbum();
    }
}
