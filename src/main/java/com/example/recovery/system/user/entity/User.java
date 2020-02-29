package com.example.recovery.system.user.entity;

import com.example.recovery.framework.entity.PermissionConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * Class {User} is 用户实体类.
 * @author MSI
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User {
    /**用户编号**/
    private Long userId;
    /**用户名**/
    private String userName;
    /**密码**/
    private String password;
    /**姓名**/
    private String name ;
    /**手机号**/
    private String phone;
    /**邮箱**/
    private String email;
//    /**是否可用**/
//    private Integer isEnabled = ConstantsEnum.ACCOUNT_STATUS_VALID.getValue();
//    /**是否在线**/
//    private Integer isOnline = ConstantsEnum.ACCOUNT_IS_OFFLINE.getValue();
    /**角色名**/
    private String roleName = PermissionConstants.USER_ROLE_USER;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
