package icu.fordring.voter.intercepters;

import icu.fordring.voter.beans.User;
import icu.fordring.voter.constant.Constants;
import icu.fordring.voter.constant.State;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VotedChecker implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        if(user.getState()== State.AUTHORISED){
            response.sendError(403);
            return false;
        }
        return true;
    }
}
