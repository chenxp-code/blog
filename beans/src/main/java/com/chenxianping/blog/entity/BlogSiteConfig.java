package com.chenxianping.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_site_config")
public class BlogSiteConfig {
    /**
     * 配置项名称 主键
     */
    @Id
    @Column(name = "config_name")
    private String configName;

    /**
     * 配置项值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 配置项说明
     */
    @Column(name = "config_explain")
    private String configExplain;

    /**
     * 排序号
     */
    @Column(name = "config_sort")
    private Integer configSort;

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
     * 获取配置项名称 主键
     *
     * @return config_name - 配置项名称 主键
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * 设置配置项名称 主键
     *
     * @param configName 配置项名称 主键
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * 获取配置项值
     *
     * @return config_value - 配置项值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 设置配置项值
     *
     * @param configValue 配置项值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * 获取配置项说明
     *
     * @return config_explain - 配置项说明
     */
    public String getConfigExplain() {
        return configExplain;
    }

    /**
     * 设置配置项说明
     *
     * @param configExplain 配置项说明
     */
    public void setConfigExplain(String configExplain) {
        this.configExplain = configExplain;
    }

    /**
     * 获取排序号
     *
     * @return config_sort - 排序号
     */
    public Integer getConfigSort() {
        return configSort;
    }

    /**
     * 设置排序号
     *
     * @param configSort 排序号
     */
    public void setConfigSort(Integer configSort) {
        this.configSort = configSort;
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