package indi.study.system.dao.write;

import indi.study.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsertUserDao {
    void insertUsers(List<Users> users);
}
