package icu.fordring.voter.intercepters;

import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.constant.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerChecker implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(ManagerChecker.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        boolean ans = true;
        if(user==null||user.getState()!= State.ROOT){
            response.sendError(403);
            ans=false;
        }
        logger.info("manage - "+ans);
        return ans;
    }
}
