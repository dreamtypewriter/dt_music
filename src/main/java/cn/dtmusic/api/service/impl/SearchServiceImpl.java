package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.mapper.AlbumMapper;
import cn.dtmusic.api.mapper.SearchMapper;
import cn.dtmusic.api.mapper.SingerMapper;
import cn.dtmusic.api.mapper.UserMapper;
import cn.dtmusic.api.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/13
 * @ time:      14:58
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchMapper searchMapper;
    private final SingerMapper singerMapper;
    private final UserMapper userMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public SearchServiceImpl(SearchMapper searchMapper, SingerMapper singerMapper, UserMapper userMapper, AlbumMapper albumMapper) {
        this.searchMapper = searchMapper;
        this.singerMapper = singerMapper;
        this.userMapper = userMapper;
        this.albumMapper = albumMapper;
    }

    @Override
    public List<SongDto> getSongsByName(String keyword, int size) {
        PageHelper.startPage(1, size);
        return searchMapper.selectSongsByName(keyword);
    }

    @Override
    public List<SongList> getSongListsByName(String searchKeyword, int size) {
        PageHelper.startPage(1, size);
        return searchMapper.selectSongListsByName(searchKeyword);
    }

    @Override
    public List<Singer> getSingersByName(String searchKeyword, int size) {
        PageHelper.startPage(1, size);
        return singerMapper.selectByPage(searchKeyword, null, null);
    }

    @Override
    public List<AlbumDto> getAlbumsByName(String searchKeyword, int size) {
        PageHelper.startPage(1, size);
        return albumMapper.selectAlbumByName(searchKeyword);
    }

    @Override
    public List<User> getUsersByName(String searchKeyword, int size) {
        PageHelper.startPage(1, size);
        List<User> users = userMapper.selectByConditions(searchKeyword, (byte) 1);
        for (User user : users) {
            user.setUserPassword(null);
        }
        return users;
    }
}
