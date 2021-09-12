package cn.dtmusic.api.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.*;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/12
 * @since 1.0.0
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    SingerService singerService;
    @Autowired
    AlbumService albumService;

    @Autowired
    SongListService songListService;

    @Autowired
    SongService songService;

    @Autowired
    CollectionService collectionService;

    //收藏歌手
    @RequestMapping("/isCollectSinger")
    @ResponseBody
    public Integer isCollectSinger(@RequestParam("singerId")Integer singerId ){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (singerService.isCollectSingerByUser(user.getUserId(),singerId)){
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("/collectSinger")
    @ResponseBody
    public Integer collectSingerByUser(@RequestParam("singerId")Integer singerId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return singerService.collectSingerByUser(user.getUserId(),singerId);
    }

    //收藏歌单
    @RequestMapping("/isCollectSongList")
    @ResponseBody
    public Integer isCollectSongList(@RequestParam("songListId")Integer songListId ){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (songListService.isCollectSongListByUser(user.getUserId(),songListId)==1){
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("/collectSongList")
    @ResponseBody
    public Integer collectSongListByUser(@RequestParam("songListId")Integer SongListId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return songListService.collectSongListByUser(user.getUserId(),SongListId);
    }

    //收藏歌曲
    @RequestMapping("/isCollectSong")
    @ResponseBody
    public Integer isCollectSong(@RequestParam("songId")Integer songId,@RequestParam("songListId") Integer songListId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Boolean collectSongByUser = collectionService.isCollectSongByUser(user.getUserId(), songId, songListId);
        if (collectionService.isCollectSongByUser(user.getUserId(),songId,songListId)){
            return 1;
        }else {
            return 0;
        }
    }
    @RequestMapping("/collectSong")
    @ResponseBody
    public Integer addSongToSongList(@RequestParam("songId")Integer SongId,@RequestParam("songListId")Integer SongListId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return songListService.addSongToSongList(user.getUserId(),SongId,SongListId);
    }


    //收藏专辑
    @RequestMapping("/isCollectAlbum")
    @ResponseBody
    public Integer isCollectAlbum(@RequestParam("albumId")Integer albumId, @RequestParam("songListId")Integer SongListId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (collectionService.isCollectAlbumByUser(user.getUserId(),albumId,SongListId)){
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("/collectAlbum")
    @ResponseBody
    public Integer collectAlbumByUser(@RequestParam("albumId")Integer albumId,
                                      @RequestParam("songListId")Integer songListId ){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return collectionService.collectAlbumByUser(user.getUserId(),albumId,songListId);
    }

    @RequestMapping("/isCollectSongListId")
    @ResponseBody
    public Integer isCollectSongListId( @RequestParam("songListId")Integer songListId ){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(collectionService.isCollectSongListId(user.getUserId(),songListId)){
            return 1;
        }else {
            return 0;
        }

    }

    //取消收藏歌手
    @RequestMapping("/delCollectedSinger")
    @ResponseBody
    public Integer delCollectedSinger(@RequestParam("singerId")Integer singerId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
       return collectionService.delCollectedSinger(user.getUserId(),singerId);

    }

    @RequestMapping("/isCollectSingerId")
    @ResponseBody
    public Integer isCollectSingerId(@RequestParam("singerId")Integer singerId){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(collectionService.isCollectSingerId(user.getUserId(),singerId)){
            return 1;
        }else {
            return 0;
        }
    }



}
