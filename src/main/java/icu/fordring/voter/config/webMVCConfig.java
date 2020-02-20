package icu.fordring.voter.config;

import icu.fordring.voter.dao.ImageManager;
import icu.fordring.voter.intercepters.LoginChecker;
import icu.fordring.voter.intercepters.ManagerChecker;
import icu.fordring.voter.intercepters.VoteChecker;
import icu.fordring.voter.intercepters.VotedChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class webMVCConfig implements WebMvcConfigurer {
    @Resource
    private ImageManager imageManager;
    private Logger logger = LoggerFactory.getLogger(webMVCConfig.class);
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginChecker())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/images/**");
        registry.addInterceptor(new ManagerChecker())
                .addPathPatterns("/manage/**");
        registry.addInterceptor(new VoteChecker())
                .addPathPatterns("/vote/**");
        registry.addInterceptor(new VotedChecker())
                .addPathPatterns("/voted/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+imageManager.getLocation());
        logger.info("static path:"+imageManager.getLocation());
    }
}
