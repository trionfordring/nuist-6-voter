package icu.fordring.voter.controller;

import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.dao.ImageManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/voted")
public class VotedController {
    @Resource
    private ImageManager imageManager;
    @RequestMapping("/list")
    public String listPage(Map map, HttpSession session){
        map.put("imageList",imageManager.getList());
        map.put("user",(User)session.getAttribute(Constants.USER));
        return "list";
    }
}
