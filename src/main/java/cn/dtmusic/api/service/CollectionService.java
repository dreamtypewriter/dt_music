package cn.dtmusic.api.service;

public interface CollectionService {

    Boolean isCollectSongByUser(Integer userId, Integer songId, Integer songListId);

    Boolean isCollectAlbumByUser(Integer userId, Integer albumId, Integer songListId);

    Integer collectAlbumByUser(Integer userId, Integer albumId, Integer songListId);

    Boolean isCollectSongListId(Integer userId, Integer songListId);

    Integer delCollectedSinger(Integer userId, Integer singerId);

    Boolean isCollectSingerId(Integer userId, Integer singerId);
}
