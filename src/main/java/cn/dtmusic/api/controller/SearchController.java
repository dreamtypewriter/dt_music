package cn.dtmusic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.SearchService;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/13
 * @ time:      14:35
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/list")
    public String searchByKeyword(String searchKeyword, Model model) {
        List<SongDto> songDtos = searchService.getSongsByName(searchKeyword, 10);
        model.addAttribute("searchSongs", songDtos);
        model.addAttribute("searchKeyword", searchKeyword);
        return "searchList";
    }

    @GetMapping("/song")
    @ResponseBody
    public List<SongDto> searchSong(String searchKeyword,
                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
        return searchService.getSongsByName(searchKeyword, size);
    }

    @GetMapping("/songList")
    @ResponseBody
    public List<SongList> searchSongList(String searchKeyword,
                                         @RequestParam(required = false, defaultValue = "18") Integer size) {
        return searchService.getSongListsByName(searchKeyword, size);
    }

    @GetMapping("/singer")
    @ResponseBody
    public List<Singer> searchSinger(String searchKeyword,
                                     @RequestParam(required = false, defaultValue = "18") Integer size) {
        return searchService.getSingersByName(searchKeyword, size);
    }

    @GetMapping("/album")
    @ResponseBody
    public List<AlbumDto> searchAlbum(String searchKeyword,
                                      @RequestParam(required = false, defaultValue = "18") Integer size) {
        return searchService.getAlbumsByName(searchKeyword, size);
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> searchUser(String searchKeyword,
                                 @RequestParam(required = false, defaultValue = "18") Integer size) {
        return searchService.getUsersByName(searchKeyword, size);
    }
}
