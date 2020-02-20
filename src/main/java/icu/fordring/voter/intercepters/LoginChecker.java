package icu.fordring.voter.intercepters;


import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.constant.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginChecker implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoginChecker.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean ans = true;
        HttpSession s = request.getSession();
        if(s.getAttribute(Constants.USER)==null||((User)s.getAttribute(Constants.USER)).getState()== State.UNAUTHORISED){
            s.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            ans=false;
        }
        logger.info("getRequest-"+request.getRemoteAddr()+" requestResult-"+ans);
        return ans;
    }
}
