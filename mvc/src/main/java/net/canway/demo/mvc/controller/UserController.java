package net.canway.demo.mvc.controller;

import net.canway.demo.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    private static List<User> users = new ArrayList<>();

    static {
        //模仿数据查询
        User xiaoming = new User();
        xiaoming.setId("1");
        xiaoming.setFullname("小明");
        xiaoming.setUsername("xiaoming");
        xiaoming.setPassword("123456");
        xiaoming.setCreateTime(new Date());


        User zhangsan = new User();
        zhangsan.setId("2");
        zhangsan.setFullname("张三");
        zhangsan.setUsername("zhangsan");
        zhangsan.setCreateTime(new Date());


        users.add(xiaoming);
        users.add(zhangsan);

    }


    @ResponseBody
    @RequestMapping
    public List<User> findAll() {
        return users;
    }

    /**
     * PathVariable  从路径上获取参数
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/{id}")
    public User findOne(@PathVariable String id) {
        for (int i = 0, k = users.size(); i < k; i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * 从？后面获取查询参数
     *
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/by_username")
    public Result findByUsername(@NotNull(message = "用户名不能为空") @RequestParam(required = false) String username) {
        Result result = new Result();
        for (int i = 0, k = users.size(); i < k; i++) {
            if (users.get(i).getUsername().equals(username)) {
                result.setCode("200");
                result.setResult(users.get(i));
            }
        }
        if (!"200".equals(result.getCode())) {
            result.setCode("400");
            result.setMessage("未查询到结果");
        }
        return result;
    }

    @ResponseBody
    @GetMapping("/by_create_time")
    public User findByCreateTime(@RequestParam("create_time") User createTime) {
        System.out.println(createTime);
        return users.get(0);
    }


    @ResponseBody
    @PostMapping
    public Result save(@Validated(User.SaveValidated.class) @RequestBody User user) {
        users.add(user);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("保存成功");
        return result;
    }

    @ResponseBody
    @PutMapping
    public Result update(@Validated(User.UpdateValidated.class) @RequestBody User user) {
        users.removeIf(u -> StringUtils.hasLength(u.getId()) && u.getId().equals(user.getId()));

        users.add(user);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("修改成功");
        return result;
    }

}
