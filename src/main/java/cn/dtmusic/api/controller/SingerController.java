package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.service.FileService;
import cn.dtmusic.api.service.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/27
 * @ time:      20:30
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("singer")
public class SingerController {
    private final SingerService singerService;
    private final FileService fileService;

    @Autowired
    public SingerController(SingerService singerService, FileService qiniuyunUtil) {
        this.singerService = singerService;
        this.fileService = qiniuyunUtil;
    }


    @GetMapping("/content")
    public String showSingleSinger(Integer id, Model model) {
        id = id == null ? 1 : id;
        Singer singer = singerService.getSingerById(id);
        if (singer == null) {
            id = 1;
            singer = singerService.getSingerById(id);
        }
        model.addAttribute("singer", singer);
        return "singerContent";
    }


    @RequestMapping("add")
    @ResponseBody
    public Integer addSinger(@RequestParam("singerName") String singerName,
                             @RequestParam("singerType") String singerType,
                             @RequestParam("singerLanguage") String singerLanguage,
                             @RequestParam("singerDescription") String singerDescription,
                             @RequestParam("singerImgFile") MultipartFile singerImgFile) throws IOException {
        String pictureUrl = fileService.uploadPicture(singerImgFile);
        Singer singer = new Singer();
        singer.setSingerName(singerName);
        singer.setSingerType(singerType);
        singer.setSingerLanguage(singerLanguage);
        singer.setSingerDescription(singerDescription);
        singer.setSingerImg(pictureUrl);
        return singerService.addSinger(singer);
    }

    @PostMapping("update")
    @ResponseBody
    public Integer updateSinger(@RequestParam("singerId") Integer singerId,
                                String singerName,
                                @RequestParam("singerType") String singerType,
                                @RequestParam("singerLanguage") String singerLanguage,
                                @RequestParam("singerDescription") String singerDescription,
                                @RequestParam("singerImgFile") MultipartFile singerImgFile) throws IOException {
        String pictureUrl = fileService.uploadPicture(singerImgFile);
        Singer singer = new Singer();
        singer.setSingerId(singerId);
        singer.setSingerName(singerName);
        singer.setSingerType(singerType);
        singer.setSingerLanguage(singerLanguage);
        singer.setSingerDescription(singerDescription);
        singer.setSingerImg(pictureUrl);
        return singerService.updateSinger(singer);
    }

    @PostMapping("updateNoImg")
    @ResponseBody
    public Integer updateSingerNoImg(Singer singer) {
        return singerService.updateSinger(singer);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Integer removeSingerById(@PathVariable Integer id) {
        return singerService.removeSinger(id);
    }

    @PostMapping("")
    @ResponseBody
    public Integer updateSinger(@RequestBody Singer singer) {
        singer.setCollectionCount(0);
        return singerService.updateSinger(singer);
    }

    @GetMapping("")
    @ResponseBody
    public PageInfo<Singer> getSingerByPage(Integer offset, Integer limit, String singerName,
                                            String singerType, String singerLanguage) {
        return singerService.getSingerByPage(offset, limit, singerName, singerType, singerLanguage);
    }

    @ResponseBody
    @RequestMapping("listTopSingers")
    public List<Singer> listTopSingers() {
        return singerService.findTopSingers();
    }
}
