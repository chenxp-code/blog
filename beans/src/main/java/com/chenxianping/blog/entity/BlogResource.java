package com.chenxianping.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_resource")
public class BlogResource {
    /**
     * 资源id 主键 自增
     */
    @Id
    @Column(name = "resource_id")
    private Integer resourceId;

    /**
     * 资源名/网站名
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源描述
     */
    @Column(name = "resource_description")
    private String resourceDescription;

    /**
     * 网站地址/资源下载地址
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 资源图标
     */
    @Column(name = "resource_icon")
    private String resourceIcon;

    /**
     * 资源所属分类
     */
    @Column(name = "resource_category")
    private Integer resourceCategory;

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
     * 获取资源id 主键 自增
     *
     * @return resource_id - 资源id 主键 自增
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id 主键 自增
     *
     * @param resourceId 资源id 主键 自增
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取资源名/网站名
     *
     * @return resource_name - 资源名/网站名
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名/网站名
     *
     * @param resourceName 资源名/网站名
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取资源描述
     *
     * @return resource_description - 资源描述
     */
    public String getResourceDescription() {
        return resourceDescription;
    }

    /**
     * 设置资源描述
     *
     * @param resourceDescription 资源描述
     */
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    /**
     * 获取网站地址/资源下载地址
     *
     * @return resource_url - 网站地址/资源下载地址
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置网站地址/资源下载地址
     *
     * @param resourceUrl 网站地址/资源下载地址
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    /**
     * 获取资源图标
     *
     * @return resource_icon - 资源图标
     */
    public String getResourceIcon() {
        return resourceIcon;
    }

    /**
     * 设置资源图标
     *
     * @param resourceIcon 资源图标
     */
    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    /**
     * 获取资源所属分类
     *
     * @return resource_category - 资源所属分类
     */
    public Integer getResourceCategory() {
        return resourceCategory;
    }

    /**
     * 设置资源所属分类
     *
     * @param resourceCategory 资源所属分类
     */
    public void setResourceCategory(Integer resourceCategory) {
        this.resourceCategory = resourceCategory;
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