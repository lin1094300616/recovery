package com.example.recovery.system.user.service.impl;

import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.Md5Util;
import com.example.recovery.system.user.entity.User;
import com.example.recovery.system.user.mapper.UserMapper;
import com.example.recovery.system.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Class {@code Object} is 用户业务层实现类，实现业务处理.
 * @author MSI
 * @version 1.0
 */
@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        User user = userMapper.findByUserName(userName);
        if (user == null) {
            return  false;
        }
        if (!user.getPassword().equals(Md5Util.transMD5(userName + password))) {
            return false;
        }
        session.setAttribute("user",user);
        return true;
    }

    @Override
    public Map add(User user) {
        /**判断用户名是否存在**/
        if (userMapper.findByUserName(user.getUserName()) != null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        /**获得MD5加密密码   Epidemic   确诊  疑似  死亡  治愈**/
        String md5Password = Md5Util.transMD5(user.getUserName() + user.getPassword());
        user.setPassword(md5Password);
        if(userMapper.add(user) == 1) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Map update(User user) {
        User userByUserName = userMapper.findByUserName(user.getUserName());
        if ((userByUserName != null) && (!userByUserName.getUserId().equals(user.getUserId()))) {
            return  ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        if(userMapper.update(user) == 1) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
    }

    @Override
    public Map delete(Integer userId) {
        if (userMapper.findUser(userId) == null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(userMapper.delete(userId) == 1) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public User findUser(Integer userId) {
        return userMapper.findUser(userId);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }
}