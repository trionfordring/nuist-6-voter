package icu.fordring.voter.controller;

import icu.fordring.voter.beans.ImageBean;
import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.constant.State;
import icu.fordring.voter.dao.ImageManager;
import icu.fordring.voter.dao.UserManager;
import icu.fordring.voter.intercepters.VoteChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/vote")
public class VoteController {
    @Resource
    private ImageManager imageManager;
    @Resource
    private UserManager userManager;
    private Logger logger = LoggerFactory.getLogger(VoteController.class);
    @RequestMapping("")
    public String votePage(Map map){
        map.put("ImageList",imageManager.getList());
        return "vote";
    }
    @RequestMapping("/submit")
    public String vote(HttpSession session,String[] voteList){
        User user = userManager.refresh((User) session.getAttribute(Constants.USER));
        if(voteList==null){
            return "redirect:/";
        }
        for(String s:voteList){
            ImageBean imageBean = imageManager.findById(Long.valueOf(s));
            if(imageBean!=null){
                user.getImages().add(imageBean);
            }
        }
        logger.info("id:"+user.getId()+" voted!");
        user.setState(State.VOTED);
        session.setAttribute(Constants.USER,userManager.insertUser(user));
        return "redirect:/";
    }
}
