package indi.study.system.service;

import org.springframework.scheduling.Trigger;

public interface CronService {

    String getCron(Integer id);
    Trigger getTrigger(Integer cron);
}
