package indi.study.system.dao.read;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.study.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserReadTwoDao extends BaseMapper<Users> {
}
