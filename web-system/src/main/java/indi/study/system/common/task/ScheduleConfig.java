package indi.study.system.common.task;

import indi.study.system.service.CronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.concurrent.Executors;

/**
 * 用于实现从数据库获取指定时间来动态执行定时任务---可多线程
 * 通过实现 SchedulingConfigurer
 * */
@Slf4j
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Resource
    CronService cronService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度为5的定时任务线程池---尽量不要使用Executors创建线程池
        //taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
        taskRegistrar.addCronTask(configTask(), cronService.getCron(1));
    }

    private Runnable configTask() {
        return new Runnable() {
            @Override
            public void run() {
                log.info("基于数据库查询Cron定时任务-------");
            }
        };
    }
}
