package indi.study.system.common.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果配置了多个不同kafka
 * 则配置不能走springboot自动加载
 * 需要手动配置
 * */
@Configuration
public class KafkaConfig {
     /**
      *  配置信息
      */
    @Value("${spring.kafka_one.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka_one.producer.retries}")
    private Integer retries;
    @Value("${spring.kafka_one.producer.batch-size}")
    private Integer batchSize;
    @Value("${spring.kafka_one.producer.buffer-memory}")
    private Integer bufferMemory;
    @Value("${spring.kafka_one.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka_one.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;
    @Value("${spring.kafka_one.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka_one.consumer.auto-commit-interval}")
    private String autoCommitInterval;

    @Bean(name = "kafkaOneTemplate")
    public KafkaTemplate<String, String> kafkaLenovoTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean(name = "kafkaOneContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
    * 生产者配置
    * */
    Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
//        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * 消费者配置
     * */
    Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
