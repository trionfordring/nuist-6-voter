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
import java.lang.invoke.ConstantCallSite;

public class VoteChecker implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(VoteChecker.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.USER);
        logger.info("vote request - id:"+user.getId()+" state:"+user.getState().toString());
        if(user.getState()== State.VOTED){
            response.sendError(403);
            return false;
        }
        return true;
    }
}
