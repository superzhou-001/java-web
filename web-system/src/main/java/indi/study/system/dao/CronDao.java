package indi.study.system.dao;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CronDao {
    String getCron(Integer id);
    void upCron(String cron);
}
