package indi.study.system.controller;

import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import org.springframework.http.ResponseEntity;
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

        List<Users> usersList = userService.findUserList();
        usersList.stream().forEach(user -> {
            System.out.println(user.getId() + "---" + user.getName() + "---" + user.getAge());
        });
    }

}
