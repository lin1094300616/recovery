package com.example.recovery.system.user.service;

import com.example.recovery.system.user.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Class {@code Object} is 用户业务层接口类.
 *
 * @author MSI
 * @version 1.0
 */
public interface UserService {

    boolean login(String userName, String password, HttpSession session);

    Map add(User user);

    Map update(User user);

    Map delete(Integer userId);

    User findUser(Integer userId);

    List<User> findUserList();

}
