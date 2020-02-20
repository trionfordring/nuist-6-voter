package icu.fordring.voter.dao;

import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class UserManager {
    @Resource
    private LoginDao loginDao;
    private Logger logger = LoggerFactory.getLogger(UserManager.class);
    public User login(@NotNull String password){
        User user;
        if(loginDao.existsUserByPassword(password)){
            user = loginDao.findUserByPassword(password);
        }else{
            user = new User();
        }
        logger.info("User Login - pwd:"+password+" state:"+user.getState());
        return user;
    }
    public boolean userExist(@NotNull String password){
        return loginDao.existsUserByPassword(password);
    }
    public User insertUser(@NotNull User user){
        if(user.getPassword()==null)return user;
        logger.info("User insert - pwd:"+user.getPassword()+" state:"+user.getState());
        return loginDao.saveAndFlush(user);
    }
    public List<User> findAllUser(){
        return loginDao.findAll();
    }
    public void saveAll(List<User> list){
        logger.info("save user list - size:"+list.size());
        loginDao.saveAll(list);
    }
    @Transactional
    public void deleteAllDefaultNameUser(){
        loginDao.deleteAllByNameStartsWith(Constants.USER_DEFAULT_NAME);
    }
    public User findById(Long id){
        return loginDao.getOne(id);
    }
    public User refresh(User user){
        return loginDao.findById(user.getId()).orElse(null);
    }
}
