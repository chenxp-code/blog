package com.chenxianping.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_article")
public class BlogArticle {
    /**
     * 文章id 主键 自增
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 文章封面
     */
    @Column(name = "article_cover_image")
    private String articleCoverImage;

    /**
     * 文章自定义路径
     */
    @Column(name = "article_sub_url")
    private String articleSubUrl;

    /**
     * 文章状态：0-草稿1-已发布
     */
    @Column(name = "article_status")
    private Byte articleStatus;

    /**
     * 文章浏览量
     */
    @Column(name = "article_page_view")
    private Integer articlePageView;

    /**
     * 作者id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 文章分类id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 文章标签ids
     */
    @Column(name = "tag_ids")
    private String tagIds;

    /**
     * 是否原创：0-否1-是
     */
    @Column(name = "is_original")
    private Byte isOriginal;

    /**
     * 是否开启评论：0-否 1-是
     */
    @Column(name = "is_enable_comment")
    private Byte isEnableComment;

    /**
     * 文章评论数
     */
    @Column(name = "article_comment_amount")
    private Integer articleCommentAmount;

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
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

    /**
     * 获取文章id 主键 自增
     *
     * @return article_id - 文章id 主键 自增
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id 主键 自增
     *
     * @param articleId 文章id 主键 自增
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取文章标题
     *
     * @return article_title - 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articleTitle 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 获取文章封面
     *
     * @return article_cover_image - 文章封面
     */
    public String getArticleCoverImage() {
        return articleCoverImage;
    }

    /**
     * 设置文章封面
     *
     * @param articleCoverImage 文章封面
     */
    public void setArticleCoverImage(String articleCoverImage) {
        this.articleCoverImage = articleCoverImage;
    }

    /**
     * 获取文章自定义路径
     *
     * @return article_sub_url - 文章自定义路径
     */
    public String getArticleSubUrl() {
        return articleSubUrl;
    }

    /**
     * 设置文章自定义路径
     *
     * @param articleSubUrl 文章自定义路径
     */
    public void setArticleSubUrl(String articleSubUrl) {
        this.articleSubUrl = articleSubUrl;
    }

    /**
     * 获取文章状态：0-草稿1-已发布
     *
     * @return article_status - 文章状态：0-草稿1-已发布
     */
    public Byte getArticleStatus() {
        return articleStatus;
    }

    /**
     * 设置文章状态：0-草稿1-已发布
     *
     * @param articleStatus 文章状态：0-草稿1-已发布
     */
    public void setArticleStatus(Byte articleStatus) {
        this.articleStatus = articleStatus;
    }

    /**
     * 获取文章浏览量
     *
     * @return article_page_view - 文章浏览量
     */
    public Integer getArticlePageView() {
        return articlePageView;
    }

    /**
     * 设置文章浏览量
     *
     * @param articlePageView 文章浏览量
     */
    public void setArticlePageView(Integer articlePageView) {
        this.articlePageView = articlePageView;
    }

    /**
     * 获取作者id
     *
     * @return user_id - 作者id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置作者id
     *
     * @param userId 作者id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取文章分类id
     *
     * @return category_id - 文章分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置文章分类id
     *
     * @param categoryId 文章分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取文章标签ids
     *
     * @return tag_ids - 文章标签ids
     */
    public String getTagIds() {
        return tagIds;
    }

    /**
     * 设置文章标签ids
     *
     * @param tagIds 文章标签ids
     */
    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    /**
     * 获取是否原创：0-否1-是
     *
     * @return is_original - 是否原创：0-否1-是
     */
    public Byte getIsOriginal() {
        return isOriginal;
    }

    /**
     * 设置是否原创：0-否1-是
     *
     * @param isOriginal 是否原创：0-否1-是
     */
    public void setIsOriginal(Byte isOriginal) {
        this.isOriginal = isOriginal;
    }

    /**
     * 获取是否开启评论：0-否 1-是
     *
     * @return is_enable_comment - 是否开启评论：0-否 1-是
     */
    public Byte getIsEnableComment() {
        return isEnableComment;
    }

    /**
     * 设置是否开启评论：0-否 1-是
     *
     * @param isEnableComment 是否开启评论：0-否 1-是
     */
    public void setIsEnableComment(Byte isEnableComment) {
        this.isEnableComment = isEnableComment;
    }

    /**
     * 获取文章评论数
     *
     * @return article_comment_amount - 文章评论数
     */
    public Integer getArticleCommentAmount() {
        return articleCommentAmount;
    }

    /**
     * 设置文章评论数
     *
     * @param articleCommentAmount 文章评论数
     */
    public void setArticleCommentAmount(Integer articleCommentAmount) {
        this.articleCommentAmount = articleCommentAmount;
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

    /**
     * 获取文章内容
     *
     * @return article_content - 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容
     *
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}