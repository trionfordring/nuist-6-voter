package icu.fordring.voter.controller;

import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.dao.ImageManager;
import icu.fordring.voter.dao.UserManager;
import icu.fordring.voter.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Resource
    private UserManager userManager;
    @Resource
    private ImageManager imageManager;
    @GetMapping("/addUser")
    public String addUser(){
        return "manageTools/addUser";
    }
    @PostMapping("/addUser")
    public String addUserHandle(String name,String password,String state,Map map){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setState(Utils.string2State(state));
        user = userManager.insertUser(user);
        map.put("show",user);
        return "showUser";
    }
    @RequestMapping("")
    public String manage(Map map){
        map.put("list",userManager.findAllUser());
        map.put("imageList",imageManager.getList());
        return "manage";
    }
    @RequestMapping("/createUsers")
    public String createUsers(int userNumber,int pwdLength){
        if(userNumber>0&&pwdLength>0&&userNumber<1001&&pwdLength<1025){
            List<User> list = new ArrayList<>();
            for(int i=0;i<userNumber;i++){
                User user = new User();
                user.setPassword(Utils.createRandomString(pwdLength));
                user.setState(Constants.USER_DEFAULT_STATE);
                user.setName(Constants.USER_DEFAULT_NAME);
                list.add(user);
            }
            userManager.saveAll(list);
        }
        return "redirect:/manage/";
    }
    @RequestMapping("/deleteAllUsers")
    public String deleteAllDefaultUsers(){
        userManager.deleteAllDefaultNameUser();
        return "redirect:/manage/";
    }
    @RequestMapping("/user/{id}")
    public String setUser(@PathVariable Long id,Map map){
        map.put("show",userManager.findById(id));
        return "showUser";
    }

    @RequestMapping("/delete/image/{id}")
    public String deleteImage(@PathVariable Long id){
        imageManager.deleteById(id);
        return "redirect:/manage/";
    }
}
