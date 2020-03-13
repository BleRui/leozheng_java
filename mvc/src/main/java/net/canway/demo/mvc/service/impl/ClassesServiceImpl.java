package net.canway.demo.mvc.service.impl;

import net.canway.demo.mvc.dao.ClassesDao;
import net.canway.demo.mvc.entity.Classes;
import net.canway.demo.mvc.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    public Classes findOne(Integer id) {
        return classesDao.findOne(id);
    }
}
