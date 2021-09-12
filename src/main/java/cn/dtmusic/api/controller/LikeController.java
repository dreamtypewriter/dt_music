package cn.dtmusic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.dtmusic.api.entity.Like;
import cn.dtmusic.api.service.LikeService;

/**
 * @ description:
 * @ date:      2020/10/7
 * @ time:      15:10
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PutMapping("")
    public Integer changeLikeCount(@RequestBody Like like) {
        return likeService.changeLikeCount(like);
    }
}
