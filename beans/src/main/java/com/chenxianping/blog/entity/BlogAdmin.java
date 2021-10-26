package com.chenxianping.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_admin")
public class BlogAdmin {
    /**
     * 管理员id 主键
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 管理员账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 管理员密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 管理员头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 管理员昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 管理员邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 管理员类型：1-超管2-普管
     */
    @Column(name = "user_type")
    private Byte userType;

    /**
     * 管理员状态：1-正常2-弃用
     */
    @Column(name = "user_status")
    private Byte userStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取管理员id 主键
     *
     * @return Id - 管理员id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员id 主键
     *
     * @param id 管理员id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取管理员账号
     *
     * @return user_name - 管理员账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置管理员账号
     *
     * @param userName 管理员账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取管理员密码
     *
     * @return user_password - 管理员密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置管理员密码
     *
     * @param userPassword 管理员密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取管理员头像
     *
     * @return user_avatar - 管理员头像
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置管理员头像
     *
     * @param userAvatar 管理员头像
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * 获取管理员昵称
     *
     * @return user_nickname - 管理员昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置管理员昵称
     *
     * @param userNickname 管理员昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * 获取管理员邮箱
     *
     * @return user_email - 管理员邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置管理员邮箱
     *
     * @param userEmail 管理员邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取管理员类型：1-超管2-普管
     *
     * @return user_type - 管理员类型：1-超管2-普管
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * 设置管理员类型：1-超管2-普管
     *
     * @param userType 管理员类型：1-超管2-普管
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * 获取管理员状态：1-正常2-弃用
     *
     * @return user_status - 管理员状态：1-正常2-弃用
     */
    public Byte getUserStatus() {
        return userStatus;
    }

    /**
     * 设置管理员状态：1-正常2-弃用
     *
     * @param userStatus 管理员状态：1-正常2-弃用
     */
    public void setUserStatus(Byte userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}