package net.canway.demo.mvc.dao;

import net.canway.demo.mvc.entity.Classes;
import org.apache.ibatis.annotations.Param;

public interface ClassesDao {

    Classes findOne(@Param("id") Integer id);

    void add(Classes classes);
}
