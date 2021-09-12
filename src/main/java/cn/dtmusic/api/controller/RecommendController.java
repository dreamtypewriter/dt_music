package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.*;
import cn.dtmusic.api.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/12
 * @ time:      10:47
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("/index")
public class RecommendController {
    private final RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @Autowired
    SingerService singerService;

    @Autowired
    SongListService songListService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BannerService bannerService;


    @RequestMapping("")
    public String jumpToIndex(Model model) {
        List<Banner> banners = bannerService.findAllBanner();
        List<Song> songs = recommendService.getRecommendedSongs(12);
        List<SongList> songLists = songListService.getRecommendedSongList(12);
        model.addAttribute("recommendedSongs", songs);
        model.addAttribute("banners",banners);
        model.addAttribute("songLists",songLists);
        return "index";
    }

    @ResponseBody
    @RequestMapping("singerList")
    public PageInfo<Singer> singerList(Integer offset, Integer limit, String singerName, String singerType, String singerLanguage) {
        PageInfo<Singer> singers = singerService.getSingerByPage(offset, limit, singerName, singerType, singerLanguage);
        return singers;
    }

    @RequestMapping("singerLists")
    public String singerLists(Model model, String singerName, String singerType, String singerLanguage) {
        PageInfo<Singer> singers = singerService.getSingerByPage(0, 2, singerName, singerType, singerLanguage);
        model.addAttribute("singers", singers);
        return "singerList";
    }

    @ResponseBody
    @RequestMapping("songList")
    public PageInfo<SongList> songList(Integer pageNum, Integer pageSize, Integer categoryId) {
        return songListService.selectSongListsByCon(pageNum, pageSize, categoryId);
    }

    @RequestMapping("songLists")
    public String songLists(Model model) {
        PageInfo<SongList> songLists = songListService.selectSongListsByCon(1, 6, 0);
        model.addAttribute("songLists", songLists);

        List<Category> categories1 = categoryService.findByType("语种");
        model.addAttribute("categories1",categories1);

        List<Category> categories2 = categoryService.findByType("风格");
        model.addAttribute("categories2",categories2);

        List<Category> categories3 = categoryService.findByType("场景");
        model.addAttribute("categories3",categories3);

        List<Category> categories4 = categoryService.findByType("情感");
        model.addAttribute("categories4",categories4);

        return "songLists";
    }


    @ResponseBody
    @RequestMapping("categoryList")
    public List<Category> categoryList() {
        return categoryService.selectAll();
    }
}
