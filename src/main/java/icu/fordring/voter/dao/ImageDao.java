package icu.fordring.voter.dao;

import icu.fordring.voter.beans.ImageBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<ImageBean,Long> {
    public boolean existsByPath(String path);
}
