package com.example.recovery.system.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.user.service.UserService;
import com.example.recovery.system.user.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Class {@code Object} is 视图层，数据接收、返回与校验.
 *
 * @author MSI
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final String LOGIN_OK = "登录成功！";

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpSession session) {
        if (CommUtil.isNullString(user.getUserName(),user.getPassword())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return userService.login(user.getUserName(), user.getPassword(), session);
    }

    @PostMapping("/loginOut")
    public Map loginOut(HttpSession session) {
        //查看session是否存在用户数据，有就清空
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
    }

    @PostMapping
    public Map postUser(@RequestBody User user) {
        if (CommUtil.isNullString(user.getName(),user.getPassword(),user.getPhone())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return userService.add(user);
    }

    @PutMapping()
    public Map updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/{userId}")
    public Map delete(@PathVariable(value = "userId")Integer userId) {
        return userService.delete(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Map getUserById(@PathVariable("userId") Integer userId) {
        User user = userService.findUser(userId);
        if (user != null) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), user);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map getUsers() {
        List<User> userList = userService.findUserList();
        if ((userList == null) || userList.isEmpty()) {
            return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), userList);
    }

    @PostMapping("/page")
    public Map findByPage(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<User> pageInfo = PageHelper.startPage(page,size);
        List<User> epidemics = userService.findWrapper(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), epidemics, result);


    }


}
