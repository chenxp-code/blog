package com.chenxianping.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_comment")
public class BlogComment {
    /**
     * 评论id 主键 自增
     */
    @Id
    @Column(name = "commen_id")
    private Integer commenId;

    /**
     * 评论来源：0-留言板 其他数-文章id
     */
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 父级评论id 默认为0 一级评论
     */
    @Column(name = "comment_parent_id")
    private Integer commentParentId;

    /**
     * 评论者id
     */
    @Column(name = "commentator_id")
    private Integer commentatorId;

    /**
     * 收到回复的评论者id
     */
    @Column(name = "reply_commentator_id")
    private Integer replyCommentatorId;

    /**
     * 评论时的ip
     */
    @Column(name = "commentator_ip")
    private String commentatorIp;

    /**
     * 是否删除：0-否 1-是
     */
    @Column(name = "is_deleted")
    private Byte deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 获取评论id 主键 自增
     *
     * @return commen_id - 评论id 主键 自增
     */
    public Integer getCommenId() {
        return commenId;
    }

    /**
     * 设置评论id 主键 自增
     *
     * @param commenId 评论id 主键 自增
     */
    public void setCommenId(Integer commenId) {
        this.commenId = commenId;
    }

    /**
     * 获取评论来源：0-留言板 其他数-文章id
     *
     * @return source_id - 评论来源：0-留言板 其他数-文章id
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置评论来源：0-留言板 其他数-文章id
     *
     * @param sourceId 评论来源：0-留言板 其他数-文章id
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取评论内容
     *
     * @return comment_content - 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取父级评论id 默认为0 一级评论
     *
     * @return comment_parent_id - 父级评论id 默认为0 一级评论
     */
    public Integer getCommentParentId() {
        return commentParentId;
    }

    /**
     * 设置父级评论id 默认为0 一级评论
     *
     * @param commentParentId 父级评论id 默认为0 一级评论
     */
    public void setCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
    }

    /**
     * 获取评论者id
     *
     * @return commentator_id - 评论者id
     */
    public Integer getCommentatorId() {
        return commentatorId;
    }

    /**
     * 设置评论者id
     *
     * @param commentatorId 评论者id
     */
    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    /**
     * 获取收到回复的评论者id
     *
     * @return reply_commentator_id - 收到回复的评论者id
     */
    public Integer getReplyCommentatorId() {
        return replyCommentatorId;
    }

    /**
     * 设置收到回复的评论者id
     *
     * @param replyCommentatorId 收到回复的评论者id
     */
    public void setReplyCommentatorId(Integer replyCommentatorId) {
        this.replyCommentatorId = replyCommentatorId;
    }

    /**
     * 获取评论时的ip
     *
     * @return commentator_ip - 评论时的ip
     */
    public String getCommentatorIp() {
        return commentatorIp;
    }

    /**
     * 设置评论时的ip
     *
     * @param commentatorIp 评论时的ip
     */
    public void setCommentatorIp(String commentatorIp) {
        this.commentatorIp = commentatorIp;
    }

    /**
     * 获取是否删除：0-否 1-是
     *
     * @return is_deleted - 是否删除：0-否 1-是
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除：0-否 1-是
     *
     * @param deleted 是否删除：0-否 1-是
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
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