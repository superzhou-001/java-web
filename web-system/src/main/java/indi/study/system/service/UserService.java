package indi.study.system.service;
import indi.study.system.common.bean.APage;
import indi.study.system.common.bean.JsonResult;
import indi.study.system.entity.Users;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Users> findUserList();
    JsonResult<APage<Users>> findPageUserList();
    JsonResult<Map<String, String>> getUserMap();
}
