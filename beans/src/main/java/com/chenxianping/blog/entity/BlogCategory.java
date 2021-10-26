package com.chenxianping.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_category")
public class BlogCategory {
    /**
     * 分类id 主键 自增
     */
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 分类名
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 分类图标
     */
    @Column(name = "category_icon")
    private String categoryIcon;

    /**
     * 分类的文章数
     */
    @Column(name = "category_amount")
    private Integer categoryAmount;

    /**
     * 分类所属栏目：1-文章2-资源库
     */
    @Column(name = "category_column")
    private Byte categoryColumn;

    /**
     * 排序号
     */
    @Column(name = "category_sort")
    private Integer categorySort;

    /**
     * 是否删除：0-否1-是
     */
    @Column(name = "is_deleted")
    private Byte deleted;

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
     * 获取分类id 主键 自增
     *
     * @return category_id - 分类id 主键 自增
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id 主键 自增
     *
     * @param categoryId 分类id 主键 自增
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取分类名
     *
     * @return category_name - 分类名
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置分类名
     *
     * @param categoryName 分类名
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取分类图标
     *
     * @return category_icon - 分类图标
     */
    public String getCategoryIcon() {
        return categoryIcon;
    }

    /**
     * 设置分类图标
     *
     * @param categoryIcon 分类图标
     */
    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    /**
     * 获取分类的文章数
     *
     * @return category_amount - 分类的文章数
     */
    public Integer getCategoryAmount() {
        return categoryAmount;
    }

    /**
     * 设置分类的文章数
     *
     * @param categoryAmount 分类的文章数
     */
    public void setCategoryAmount(Integer categoryAmount) {
        this.categoryAmount = categoryAmount;
    }

    /**
     * 获取分类所属栏目：1-文章2-资源库
     *
     * @return category_column - 分类所属栏目：1-文章2-资源库
     */
    public Byte getCategoryColumn() {
        return categoryColumn;
    }

    /**
     * 设置分类所属栏目：1-文章2-资源库
     *
     * @param categoryColumn 分类所属栏目：1-文章2-资源库
     */
    public void setCategoryColumn(Byte categoryColumn) {
        this.categoryColumn = categoryColumn;
    }

    /**
     * 获取排序号
     *
     * @return category_sort - 排序号
     */
    public Integer getCategorySort() {
        return categorySort;
    }

    /**
     * 设置排序号
     *
     * @param categorySort 排序号
     */
    public void setCategorySort(Integer categorySort) {
        this.categorySort = categorySort;
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