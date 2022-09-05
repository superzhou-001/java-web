package indi.study.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.study.system.common.bean.APage;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.common.utils.PageFactory;
import indi.study.system.common.utils.ResultUtil;
import indi.study.system.dao.UserDao;
import indi.study.system.dao.UserTwoDao;
import indi.study.system.entity.Users;
import indi.study.system.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public JsonResult findPageUserList() {
        Map<String, String> map = new HashMap<>();
        map.put("offset", "0");
        map.put("limit", "2");
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
}
