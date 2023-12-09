package indi.study.system.service;

import indi.study.system.common.bean.JsonResult;
import org.springframework.scheduling.Trigger;

public interface CronService {

    String getCron(Integer id);
    Trigger getTrigger(Integer cron);

    JsonResult upCron();
}
