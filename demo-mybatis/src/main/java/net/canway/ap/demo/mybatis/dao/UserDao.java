package net.canway.ap.demo.mybatis.dao;

import net.canway.ap.demo.mybatis.entity.User;
import net.canway.ap.demo.mybatis.entity.User1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    List<User1> findAll1();

    User findOne(@Param("id") Integer key, @Param("name") String name);

}
