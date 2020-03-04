package com.example.recovery.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recovery.system.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Class {@code Object} is 用户持久层.
 * @author MSI
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select user_id, user_name, password, name, phone, email, role_name " +
            "from user " +
            "where  user_name = #{userName}")
    User findByUserName(@Param("userName") String userName);

    @Select("select user_id,user_name, name as name, phone, email, role_name " +
            "from user" +
            "where  user_name = #{userName} and password = #{password}")
    List<User> login(User user);

    @Insert("INSERT INTO `user` VALUES (#{userId}, #{userName}, #{password}, #{name}, #{phone}, #{email},  #{roleName})")
    @Options(useGeneratedKeys = true, keyColumn = "userId", keyProperty = "userId")
    int add(User user);

    @Update("update user set user_name = #{userName},password = #{password}," +
            "name = #{name}, phone = #{phone}, email = #{email} ,role_name = #{roleName} " +
            "where user_id = #{userId}")
    int update(User user);

    @Delete("delete from user where user_id = #{userId}")
    int delete(Integer userId);

    @Select("select user_id,user_name, name as name, phone, email, role_name " +
            "from user " +
            "where user_id = #{userId}")
    User findUser(@Param("userId") Integer userId);

    @Select("select user_id,user_name, name as name, phone, email, role_name " +
            "from user")
    List<User> findUserList();

}
