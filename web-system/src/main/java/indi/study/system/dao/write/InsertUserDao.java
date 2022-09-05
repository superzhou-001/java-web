package indi.study.system.dao.write;

import indi.study.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsertUserDao {
    void insertUsers(Users users);
}
