package cn.dtmusic.api.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dtmusic.api.entity.Category;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.MyMusicService;
import cn.dtmusic.api.service.SingerService;
import cn.dtmusic.api.service.SongListService;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/13
 * @since 1.0.0
 */
@Controller
@RequestMapping("/myMusic")
public class MyMusicController {
    @Autowired
    SingerService singerService;

    @Autowired
    SongListService songListService;

    @Autowired
    MyMusicService myMusicService;

    @RequestMapping("/mySingerList")
    public String showSongList(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<SongList> createdSonglists = songListService.findSongListsByUser(user.getUserId());
        List<SongList> collectedSonglists = songListService.findCollectedSongListsByUser(user.getUserId());
        List<Singer> singers = singerService.showCollectedSingerByUser(user.getUserId());
        List<Category> categories=myMusicService.showCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("singers",singers);
        model.addAttribute("collectedSongLists",collectedSonglists);
        model.addAttribute("createdSongLists",createdSonglists);
        return "myMusic_singerList";
    }


    @GetMapping("/mySongList")
    public String showMySongLists(Integer id, Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Integer Oid=songListService.getIdOfOriginalSongList(user.getUserId());
        id = id == null ? Oid : id;
        SongList songList = songListService.findSingleSongList(id);
        if(songList==null){
            id=Oid;
            songList = songListService.findSingleSongListById(id,user.getUserId());
        }
        List<SongList> createdSonglists = songListService.findSongListsByUser(user.getUserId());
        List<SongList> collectedSonglists = songListService.findCollectedSongListsByUser(user.getUserId());
        List<Category> categories=myMusicService.showCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("collectedSongLists",collectedSonglists);
        model.addAttribute("createdSongLists",createdSonglists);
        model.addAttribute("songList",songList);
        return "myMusic";
    }



}
