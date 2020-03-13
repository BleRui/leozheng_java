package net.canway.ap.demo.mybatis;

import net.canway.ap.demo.mybatis.dao.ClassesDao;
import net.canway.ap.demo.mybatis.dao.UserDao;
import net.canway.ap.demo.mybatis.entity.Classes;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);

        /*ClassesDao classesDao = session.getMapper(ClassesDao.class);

        Classes classes = new Classes();
        classes.setId(10);
        classes.setName("10班");
        classesDao.add(classes);*/

        //System.out.println(classesDao.findOne(1));

        UserDao userDao = session.getMapper(UserDao.class);
        userDao.findAll1().forEach(System.out::println);

        //System.out.println(userDao.findOne(1, "1"));

        //userDao.findAll().forEach(System.out::println);
    }
}
