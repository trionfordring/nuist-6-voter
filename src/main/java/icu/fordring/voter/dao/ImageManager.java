package icu.fordring.voter.dao;

import icu.fordring.voter.beans.ImageBean;
import icu.fordring.voter.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@ConfigurationProperties(prefix = "voter.image")
public class ImageManager {
    @Resource
    private ImageDao imageDao;
    private Logger logger = LoggerFactory.getLogger(ImageManager.class);
    private String location;
    public List<ImageBean> scanLocation(){
        List<ImageBean> ans = new ArrayList<>();
        File dir = new File(location);
        if(dir.exists()&&dir.isDirectory()){
            for(File file:dir.listFiles()){
                String[] name = file.getName().split(".");
                if(name.length==2){
                    ImageBean imageBean = new ImageBean();
                    imageBean.setPath(file.getAbsolutePath());
                    imageBean.setType(name[1]);
                    ans.add(imageBean);
                }
            }
        }
        logger.warn("scan direction-"+location+"  ,new list size:"+ans.size());
        return ans;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public ImageBean save(ImageBean imageBean){
        logger.info("save image - "+imageBean.getPath());
        return imageDao.saveAndFlush(imageBean);
    }

    public List<ImageBean> getList(){
        return imageDao.findAll();
    }
    public ImageBean findById(Long id){
        Optional<ImageBean> optional = imageDao.findById(id);
        logger.info("find image by id-"+id+"  isPresent:"+optional.isPresent());
        return optional.orElse(null);
    }
    public void deleteById(Long id){
        imageDao.deleteById(id);
    }
}
