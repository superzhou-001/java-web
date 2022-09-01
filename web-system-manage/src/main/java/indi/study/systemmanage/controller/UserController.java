package indi.study.systemmanage.controller;

import indi.study.systemmanage.entity.User;
import indi.study.systemmanage.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/test")
@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/findUserList")
    public void findUserList() {
        List<User> userList = userService.findUserList();
        userList.stream().forEach(user -> {
            System.out.println(user.getId() + "---" + user.getName() + "---" + user.getAge());
        });
    }

}
