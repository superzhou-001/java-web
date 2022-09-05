package indi.study.system.dao.read;

import indi.study.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<Users> findUserList();

    List<Users> findPageUserList();
}
