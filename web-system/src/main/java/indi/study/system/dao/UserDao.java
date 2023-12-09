package indi.study.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.study.system.entity.Users;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * extends BaseMapper
 * 添加CRUD方法
 * */
@Mapper
public interface UserDao extends BaseMapper<User> {
    List<Users> findUserList();

    List<Users> findPageUserList();

    void insertUsers(List<Users> users);

    void upUser(long id);

}
