package indi.study.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.utils.ResultUtil;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


@Api(value = "kafka生产者测试接口", tags = {"kafka生产者测试接口"})
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Resource
    private KafkaTemplate kafkaTemplate;
    @Resource
    private UserService userService;

    @GetMapping(value = "/sendKafkaMagOne")
    @ResponseBody
    public JsonResult sendKafkaMagOne() {
        List<Users> list = userService.findUserList();
        kafkaTemplate.send("kafka_test", JSON.toJSON(list).toString());
        return ResultUtil.success(1,"kafka消息发送成功");
    }
}
