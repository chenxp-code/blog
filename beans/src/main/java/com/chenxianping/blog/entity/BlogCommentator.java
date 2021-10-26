package com.chenxianping.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_commentator")
public class BlogCommentator {
    /**
     * 评论者id 主键 自增
     */
    @Id
    @Column(name = "commentator_id")
    private Integer commentatorId;

    /**
     * 评论者名称
     */
    @Column(name = "commentator_name")
    private String commentatorName;

    /**
     * 评论者邮箱
     */
    @Column(name = "commentator_email")
    private String commentatorEmail;

    /**
     * 评论者头像 默认随机给
     */
    @Column(name = "commentator_avatar")
    private String commentatorAvatar;

    /**
     * 评论者的ip 取第一次留言的
     */
    @Column(name = "commentator_ip")
    private String commentatorIp;

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
     * 获取评论者id 主键 自增
     *
     * @return commentator_id - 评论者id 主键 自增
     */
    public Integer getCommentatorId() {
        return commentatorId;
    }

    /**
     * 设置评论者id 主键 自增
     *
     * @param commentatorId 评论者id 主键 自增
     */
    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    /**
     * 获取评论者名称
     *
     * @return commentator_name - 评论者名称
     */
    public String getCommentatorName() {
        return commentatorName;
    }

    /**
     * 设置评论者名称
     *
     * @param commentatorName 评论者名称
     */
    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    /**
     * 获取评论者邮箱
     *
     * @return commentator_email - 评论者邮箱
     */
    public String getCommentatorEmail() {
        return commentatorEmail;
    }

    /**
     * 设置评论者邮箱
     *
     * @param commentatorEmail 评论者邮箱
     */
    public void setCommentatorEmail(String commentatorEmail) {
        this.commentatorEmail = commentatorEmail;
    }

    /**
     * 获取评论者头像 默认随机给
     *
     * @return commentator_avatar - 评论者头像 默认随机给
     */
    public String getCommentatorAvatar() {
        return commentatorAvatar;
    }

    /**
     * 设置评论者头像 默认随机给
     *
     * @param commentatorAvatar 评论者头像 默认随机给
     */
    public void setCommentatorAvatar(String commentatorAvatar) {
        this.commentatorAvatar = commentatorAvatar;
    }

    /**
     * 获取评论者的ip 取第一次留言的
     *
     * @return commentator_ip - 评论者的ip 取第一次留言的
     */
    public String getCommentatorIp() {
        return commentatorIp;
    }

    /**
     * 设置评论者的ip 取第一次留言的
     *
     * @param commentatorIp 评论者的ip 取第一次留言的
     */
    public void setCommentatorIp(String commentatorIp) {
        this.commentatorIp = commentatorIp;
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