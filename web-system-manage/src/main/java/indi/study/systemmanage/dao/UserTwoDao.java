package indi.study.systemmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.study.systemmanage.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTwoDao extends BaseMapper<User> {
}
