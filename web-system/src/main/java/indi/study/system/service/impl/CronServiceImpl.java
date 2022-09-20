package indi.study.system.service.impl;

import indi.study.system.dao.CronDao;
import indi.study.system.service.CronService;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CronServiceImpl implements CronService {

    @Resource
    CronDao cronDao;

    @Override
    public String getCron(Integer id) {
        return cronDao.getCron(id);
    }

    @Override
    public Trigger getTrigger(Integer id) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 触发器
                CronTrigger trigger = new CronTrigger(getCron(id));
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }
}
