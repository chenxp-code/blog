package com.chenxianping.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_link")
public class BlogLink {
    /**
     * 友链id 主键 自增
     */
    @Id
    @Column(name = "link_id")
    private Integer linkId;

    /**
     * 网站名称
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 网站地址
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 网站描述
     */
    @Column(name = "link_description")
    private String linkDescription;

    /**
     * 网站图标
     */
    @Column(name = "link_icon")
    private String linkIcon;

    /**
     * 排序号
     */
    @Column(name = "link_sort")
    private Integer linkSort;

    /**
     * 友链状态：0-审核中1-通过2-下线
     */
    @Column(name = "link_status")
    private Byte linkStatus;

    /**
     * 友链来源：1-自主申请2-管理员添加
     */
    @Column(name = "link_source")
    private Byte linkSource;

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
     * 获取友链id 主键 自增
     *
     * @return link_id - 友链id 主键 自增
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * 设置友链id 主键 自增
     *
     * @param linkId 友链id 主键 自增
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * 获取网站名称
     *
     * @return link_name - 网站名称
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * 设置网站名称
     *
     * @param linkName 网站名称
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * 获取网站地址
     *
     * @return link_url - 网站地址
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置网站地址
     *
     * @param linkUrl 网站地址
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取网站描述
     *
     * @return link_description - 网站描述
     */
    public String getLinkDescription() {
        return linkDescription;
    }

    /**
     * 设置网站描述
     *
     * @param linkDescription 网站描述
     */
    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    /**
     * 获取网站图标
     *
     * @return link_icon - 网站图标
     */
    public String getLinkIcon() {
        return linkIcon;
    }

    /**
     * 设置网站图标
     *
     * @param linkIcon 网站图标
     */
    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
    }

    /**
     * 获取排序号
     *
     * @return link_sort - 排序号
     */
    public Integer getLinkSort() {
        return linkSort;
    }

    /**
     * 设置排序号
     *
     * @param linkSort 排序号
     */
    public void setLinkSort(Integer linkSort) {
        this.linkSort = linkSort;
    }

    /**
     * 获取友链状态：0-审核中1-通过2-下线
     *
     * @return link_status - 友链状态：0-审核中1-通过2-下线
     */
    public Byte getLinkStatus() {
        return linkStatus;
    }

    /**
     * 设置友链状态：0-审核中1-通过2-下线
     *
     * @param linkStatus 友链状态：0-审核中1-通过2-下线
     */
    public void setLinkStatus(Byte linkStatus) {
        this.linkStatus = linkStatus;
    }

    /**
     * 获取友链来源：1-自主申请2-管理员添加
     *
     * @return link_source - 友链来源：1-自主申请2-管理员添加
     */
    public Byte getLinkSource() {
        return linkSource;
    }

    /**
     * 设置友链来源：1-自主申请2-管理员添加
     *
     * @param linkSource 友链来源：1-自主申请2-管理员添加
     */
    public void setLinkSource(Byte linkSource) {
        this.linkSource = linkSource;
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