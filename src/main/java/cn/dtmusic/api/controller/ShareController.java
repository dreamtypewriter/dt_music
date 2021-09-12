package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.ShareDto;
import cn.dtmusic.api.entity.Share;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.ShareService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ description:
 * @ date:      2020/10/4
 * @ time:      16:43
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    private final ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping("/add")
    public String addShare(Share share) {
        Subject subject = SecurityUtils.getSubject();
        User userOnline = (User) subject.getPrincipal();
        share.setUserId(userOnline.getUserId());
        share.setUserAvatar(userOnline.getUserAvatar());
        share.setUserNickname(userOnline.getUserNickname());
        share.setShareText(share.getShareText().trim());
        Integer addResult = shareService.addShare(share);
        return "redirect:/friend";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageInfo<ShareDto> getSharePage(Integer pageSize, Integer pageNum) {
        User userOnline = (User) SecurityUtils.getSubject().getPrincipal();
        return shareService.getSharePage(pageSize, pageNum, userOnline.getUserId());
    }

}
