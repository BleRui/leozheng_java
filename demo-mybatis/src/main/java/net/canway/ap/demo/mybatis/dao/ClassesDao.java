package net.canway.ap.demo.mybatis.dao;

import net.canway.ap.demo.mybatis.entity.Classes;
import org.apache.ibatis.annotations.Param;

public interface ClassesDao {

    Classes findOne(@Param("id") Integer id);

    void add(Classes classes);
}
