package icu.fordring.voter.dao;

import icu.fordring.voter.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<User,Long> {
    User findUserByPassword(String password);
    boolean existsUserByPassword(String password);
    void deleteAllByNameStartsWith(String begin);
}
