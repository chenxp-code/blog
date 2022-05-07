package com.chenxianping.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_carousel")
public class BlogCarousel {
    /**
     * 主键，自增
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图片地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 目标页面地址
     */
    @Column(name = "target_url")
    private String targetUrl;

    /**
     * 轮播图展示顺序
     */
    private Integer sort;

    /**
     * 轮播图类型：1-站内 2-推广
     */
    private Byte type;

    /**
     * 是否展示：0-否 1-是
     */
    @Column(name = "is_show")
    private Byte show;

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
     * 获取主键，自增
     *
     * @return Id - 主键，自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键，自增
     *
     * @param id 主键，自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取图片地址
     *
     * @return image_url - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取目标页面地址
     *
     * @return target_url - 目标页面地址
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * 设置目标页面地址
     *
     * @param targetUrl 目标页面地址
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * 获取轮播图展示顺序
     *
     * @return sort - 轮播图展示顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置轮播图展示顺序
     *
     * @param sort 轮播图展示顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取轮播图类型：1-站内 2-推广
     *
     * @return type - 轮播图类型：1-站内 2-推广
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置轮播图类型：1-站内 2-推广
     *
     * @param type 轮播图类型：1-站内 2-推广
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取是否展示：0-否 1-是
     *
     * @return is_show - 是否展示：0-否 1-是
     */
    public Byte getShow() {
        return show;
    }

    /**
     * 设置是否展示：0-否 1-是
     *
     * @param show 是否展示：0-否 1-是
     */
    public void setShow(Byte show) {
        this.show = show;
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