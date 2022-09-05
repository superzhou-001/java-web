package indi.study.system.controller;

import indi.study.system.common.bean.APage;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/findPageUserList")
    public ResponseEntity<JsonResult> findPageUserList() {
        return ResponseEntity.ok(userService.findPageUserList());
    }

    @RequestMapping(value = "/findPageUserMap")
    public ResponseEntity<JsonResult> findPageUserMap() {
        return ResponseEntity.ok(userService.getUserMap());
    }

    @RequestMapping(value = "/findPageUserListTwo")
    @ResponseBody
    public JsonResult findPageUserListTwo() {
        return userService.findPageUserList();
    }

}
