package indi.study.systemmanage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.study.systemmanage.dao.UserDao;
import indi.study.systemmanage.dao.UserTwoDao;
import indi.study.systemmanage.entity.User;
import indi.study.systemmanage.service.UserService;
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
    public List<User> findUserList() {
        Page<User> page = new Page<>();
        List<User> userList = userDao.findUserList();
        return userList;
    }
}
