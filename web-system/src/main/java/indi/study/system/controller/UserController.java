package indi.study.system.controller;

import indi.study.system.common.bean.APage;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.customize.SystemLog;
import indi.study.system.common.utils.ContextUtil;
import indi.study.system.common.utils.RedisClient;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 备注：
 * JsonResult<APage<Users>> 泛型必须写全，不然swagger2无法识别实体参数说明
 * */
@RequestMapping("/test")
@RestController
@Api(value = "测试接口", tags = {"测试接口"})
public class UserController {

    @Resource
    UserService userService;
    @Resource
    RedisClient redisClient;

    @SystemLog
    @ApiOperation("查询用户集合")
    @PostMapping(value = "/findUserList")
    public void findUserList() {
        List<Users> usersList = userService.findUserList();
        usersList.stream().forEach(user -> {
            System.out.println(user.getId() + "---" + user.getName() + "---" + user.getAge());
        });
    }


    @ApiOperation("分页查询用户集合")
    @PostMapping(value = "/findPageUserList")
    public ResponseEntity<JsonResult<APage<Users>>> findPageUserList(
            @ApiParam(name = "page", value = "当前页码", required = true) @RequestParam String page,
            @ApiParam(name = "pageSize", value = "每页条数", required = true) @RequestParam String pageSize) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        return ResponseEntity.ok(userService.findPageUserList(map));
    }

    @ApiOperation("查询map信息")
    @GetMapping(value = "/findPageUserMap")
    public ResponseEntity<JsonResult<Map<String,String>>> findPageUserMap() {
        return ResponseEntity.ok(userService.getUserMap());
    }


    /**
     * @ApiParam swagger 注解 required = true 通过swagger访问必传
     * @RequestParam
     * */
    @ApiOperation("分页查询用户数据2")
    @PostMapping(value = "/findPageUserListTwo")
    @ResponseBody
    public JsonResult<APage<Users>> findPageUserListTwo(
            @ApiParam(name = "page", value = "当前页码", required = true) @RequestParam String page,
            @ApiParam(name = "pageSize", value = "每页条数", required = true) @RequestParam String pageSize
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        return userService.findPageUserList(map);
    }

    @ApiOperation("批量添加用户信息")
    @PostMapping(value = "/insert")
    public ResponseEntity insert() {
        return ResponseEntity.ok(userService.insertUsers());
    }

    @ApiOperation("添加用户信息")
    @PostMapping(value = "/insertUsers")
    public ResponseEntity insertUsers(Users users) {
        RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ContextUtil.getBean("redisTemplate");
        redisClient.set(users.getId().toString(), users.getName());
        return ResponseEntity.ok(userService.insertUsers(users));
    }

}
