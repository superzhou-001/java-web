package indi.study.systemmanage.service;
import indi.study.systemmanage.entity.User;
import java.util.List;

public interface UserService {
    List<User> findUserList();
}
