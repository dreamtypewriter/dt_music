package cn.dtmusic.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ description:
 * @ date:      2020/10/8
 * @ time:      11:19
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("/user/")
public class MessageController {
    @RequestMapping("/message")
    public String jumpToUserMessage() {
        return "userMessage";
    }
}
