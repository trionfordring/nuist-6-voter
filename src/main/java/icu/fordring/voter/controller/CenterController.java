package icu.fordring.voter.controller;

import icu.fordring.voter.beans.ImageBean;
import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.constant.State;
import icu.fordring.voter.dao.ImageManager;
import icu.fordring.voter.dao.UserManager;
import icu.fordring.voter.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;


@Controller
public class CenterController {
    @Resource
    private UserManager userManager;
    @Resource
    private ImageManager imageManager;
    private Logger logger = LoggerFactory.getLogger(CenterController.class);
    @RequestMapping({"/","/index"})
    public String index(HttpSession session){
        User user = (User)session.getAttribute(Constants.USER);
        if(user.getState()== State.AUTHORISED){
            return "redirect:/vote/";
        }else if(user.getState()==State.VOTED){
            return "redirect:/voted/list";
        }else if(user.getState()==State.ROOT){
            return "redirect:/manage/";
        }else{
            return "redirect:/login";
        }
    }
    @GetMapping("/login")
    public String login(HttpSession session){
        if(session.getAttribute(Constants.USER)==null){
            session.setAttribute(Constants.USER,new User());
        }
        return "login";
    }
    @PostMapping("/login")
    public String loginHandle(HttpSession session, Map map,String password){
        User u = userManager.login(password);
        if(u.getState()==State.UNAUTHORISED){
            map.put("msg","登陆失败");
            return "login";
        }
        session.setAttribute(Constants.USER,u);
        return "forward:/index";
    }
    @RequestMapping("/logout")
    public String logoutHandle(HttpSession session){
        session.setAttribute(Constants.USER,new User());
        return "redirect:/";
    }
    @RequestMapping("/update")
    public String update(MultipartFile[] images,HttpSession session) throws IOException {
        logger.info("updating location-"+imageManager.getLocation());
        User user = (User) session.getAttribute(Constants.USER);
        if(images!=null){
            for(MultipartFile image:images){
                logger.info("image-"+image.getName());
                ImageBean imageBean = new ImageBean();
                imageBean.setName(System.currentTimeMillis() +image.getOriginalFilename());
                imageBean.setType(image.getContentType());
                imageBean.setAuthor(user);
                image.transferTo(new File(new File(imageManager.getLocation()).getAbsolutePath()+"/"+imageBean.getName()));
                imageManager.save(imageBean);
            }
        }
        return "redirect:/";
    }
}
