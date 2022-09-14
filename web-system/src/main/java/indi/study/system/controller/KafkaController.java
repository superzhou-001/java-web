package indi.study.system.controller;

import com.alibaba.fastjson.JSON;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.utils.ResultUtil;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Resource(name = "kafkaOneTemplate")
    private KafkaTemplate kafkaTemplate;

    @Resource(name = "kafkaTwoTemplate")
    private KafkaTemplate kafkaTwoTemplate;

    @Resource
    private UserService userService;

    @GetMapping(value = "/sendKafkaMsg")
    @ResponseBody
    public JsonResult sendKafkaMsg() {
        List<Users> list = userService.findUserList();
        kafkaTemplate.send("kafka_test", JSON.toJSON(list).toString());
        kafkaTwoTemplate.send("kafka_test", JSON.toJSON(list).toString());
        return ResultUtil.success(1,"kafka消息发送成功");
    }
}
