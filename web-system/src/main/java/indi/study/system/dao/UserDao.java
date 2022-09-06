package indi.study.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import indi.study.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DS("select")
public interface UserDao {
    List<Users> findUserList();

    List<Users> findPageUserList();

    void insertUsers(List<Users> users);
}
