package net.canway.demo.mvc.controller;

import net.canway.demo.mvc.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;


    @GetMapping("/{id}")
    public Result findOne(@Min(1) @PathVariable Integer id) {
        return new Result(classesService.findOne(id));
    }

}
