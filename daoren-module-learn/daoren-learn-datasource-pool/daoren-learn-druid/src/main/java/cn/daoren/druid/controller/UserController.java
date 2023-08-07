package cn.daoren.druid.controller;

import cn.daoren.druid.dal.dataobject.User;
import cn.daoren.druid.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/query")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }
}
