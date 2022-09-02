package indi.study.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.study.system.dao.UserDao;
import indi.study.system.dao.UserTwoDao;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    UserTwoDao userTwoDao;

    @Override
    public List<Users> findUserList() {
        List<Users> users = userTwoDao.selectList(new QueryWrapper<Users>());
        PageHelper.startPage(1, 3);
        List<Users> usersList = userDao.findUserList();
        PageInfo pageInfo = new PageInfo(usersList);
        return usersList;
    }
}
