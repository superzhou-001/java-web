package indi.study.system.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * kafka消费者
 * */
@Configuration
@Slf4j
public class KafkaCustomer {

    //不指定group，默认取spring boot里配置的
    @KafkaListener(topics = "kafka_test")
    public void listen(String mes) {
        log.info("我收到的数据是-------------"+mes);
    }

}
