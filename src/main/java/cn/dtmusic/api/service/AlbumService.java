package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.AlbumDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {


    //根据AlbumId删除Album
    int removeAlbumByPrimaryKey(Integer albumId);

    //添加Album对象
    int addAlbum(AlbumDto albumDto);

    //修改Album对象
    int modifyAlbum(AlbumDto albumDto);

    //修改Album对象状态
    int modifyAlbumStatus(Integer albumId);

    //查询单个Album对象
    AlbumDto findSingleAlbumByPrimaryKey(Integer albumId);

    //查询出所有的Album对象
    List<AlbumDto> findAllAlbum();

    //分页查询
    PageInfo<AlbumDto> findAlbumByPage(Integer offset, Integer limit, String albumName);

    Integer collectAlbumByUser(Integer userId, Integer albumId, Integer songListId);

    List<AlbumDto> findTopAlbum();
}

