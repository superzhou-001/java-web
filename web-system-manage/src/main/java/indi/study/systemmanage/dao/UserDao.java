package indi.study.systemmanage.dao;

import indi.study.systemmanage.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> findUserList();
}
