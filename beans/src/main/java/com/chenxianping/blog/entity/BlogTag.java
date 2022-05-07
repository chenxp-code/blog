package com.chenxianping.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_tag")
public class BlogTag {
    /**
     * 标签id 主键 自增
     */
    @Id
    @Column(name = "tag_id")
    private Integer tagId;

    /**
     * 标签名
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 标签的文章数
     */
    @Column(name = "tag_amount")
    private Integer tagAmount;

    /**
     * 排序号
     */
    @Column(name = "tag_sort")
    private Integer tagSort;

    /**
     * 是否删除：0-否1-是
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
     * 获取标签id 主键 自增
     *
     * @return tag_id - 标签id 主键 自增
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * 设置标签id 主键 自增
     *
     * @param tagId 标签id 主键 自增
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取标签名
     *
     * @return tag_name - 标签名
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名
     *
     * @param tagName 标签名
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 获取标签的文章数
     *
     * @return tag_amount - 标签的文章数
     */
    public Integer getTagAmount() {
        return tagAmount;
    }

    /**
     * 设置标签的文章数
     *
     * @param tagAmount 标签的文章数
     */
    public void setTagAmount(Integer tagAmount) {
        this.tagAmount = tagAmount;
    }

    /**
     * 获取排序号
     *
     * @return tag_sort - 排序号
     */
    public Integer getTagSort() {
        return tagSort;
    }

    /**
     * 设置排序号
     *
     * @param tagSort 排序号
     */
    public void setTagSort(Integer tagSort) {
        this.tagSort = tagSort;
    }

    /**
     * 获取是否删除：0-否1-是
     *
     * @return is_deleted - 是否删除：0-否1-是
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除：0-否1-是
     *
     * @param deleted 是否删除：0-否1-是
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