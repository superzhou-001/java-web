package indi.study.system.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 基于注解
 * @EnableScheduling
 * @Scheduled
 * @EnableAsync 开启多线程---TheadPoolConfig已配置
 * @Async---TheadPoolConfig线程池配置---什么方法需要线程处理加上@Async即可
 * 注解很方便，但缺点是当我们调整了执行周期的时候，需要重启应用才能生效，
 * 这多少有些不方便。为了达到实时生效的效果，可以使用接口来完成定时任务(SchedulingConfigurer)。
 * 第三方定时任务 xxl-job
 * */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleTask {
    //添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    @Async("testExecutor")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    void configureTasks() {
        log.info("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
