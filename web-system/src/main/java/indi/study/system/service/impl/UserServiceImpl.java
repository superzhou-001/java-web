package indi.study.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.utils.PageFactory;
import indi.study.system.common.utils.ResultUtil;
import indi.study.system.dao.UserDao;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public List<Users> findUserList() {
        PageHelper.startPage(1, 10);
        List<Users> usersList = userDao.findUserList();
        PageInfo pageInfo = new PageInfo(usersList);
        return usersList;
    }

    @Override
    public JsonResult findPageUserList() {
        Map<String, String> map = new HashMap<>();
        map.put("offset", "0");
        map.put("limit", "3");
        Page<Users> page = PageFactory.getPage(map);
        List<Users> usersList = userDao.findPageUserList();
        return ResultUtil.success(usersList, page.getTotal(), page.getPageNum(), page.getPageSize());
    }

    @Override
    public JsonResult<Map<String, String>> getUserMap() {
        Map<String, String> map = new HashMap<>();
        map.put("xiaosan", "yyyy");
        map.put("xiaozhang", "dddd");
        map.put("xiaohong", "ccc");
        return ResultUtil.success(map);
    }

    @Override
    public JsonResult insertUsers() {
        List<Users> users = new ArrayList<>();
        Users user1 = new Users();
        user1.setName("xiaozhou");
        user1.setAge(27);
        Users user2 = new Users();
        user2.setName("xiaohong");
        user2.setAge(24);
        users.add(user1);
        users.add(user2);
        userDao.insertUsers(users);
        return ResultUtil.success(1);
    }
}
