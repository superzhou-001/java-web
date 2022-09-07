package indi.study.system.controller;

import indi.study.system.common.bean.APage;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@RestController
@Api(value = "测试接口", tags = {"测试接口"})
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation("查询用户集合")
    @GetMapping(value = "/findUserList")
    public void findUserList() {
        List<Users> usersList = userService.findUserList();
        usersList.stream().forEach(user -> {
            System.out.println(user.getId() + "---" + user.getName() + "---" + user.getAge());
        });
    }

    /*
    * 备注：
    * JsonResult<APage<Users>> 泛型必须写全，不然swagger无法识别实体参数说明
    * */
    @ApiOperation("分页查询用户集合")
    @GetMapping(value = "/findPageUserList")
    public ResponseEntity<JsonResult<APage<Users>>> findPageUserList() {
        return ResponseEntity.ok(userService.findPageUserList());
    }

    @ApiOperation("查询map信息")
    @GetMapping(value = "/findPageUserMap")
    public ResponseEntity<JsonResult<Map<String,String>>> findPageUserMap() {
        return ResponseEntity.ok(userService.getUserMap());
    }

    @ApiOperation("分页查询用户数据2")
    @GetMapping(value = "/findPageUserListTwo")
    @ResponseBody
    public JsonResult<APage<Users>> findPageUserListTwo() {
        return userService.findPageUserList();
    }

    @ApiOperation("添加用户信息")
    @PostMapping(value = "/insert")
    public ResponseEntity insertUsers() {
        return ResponseEntity.ok(userService.insertUsers());
    }

}
