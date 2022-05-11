package com.chenxianping.blog.dto;

import com.chenxianping.blog.validatedGroup.AddDraftArticle;
import com.chenxianping.blog.validatedGroup.AddPublishArticle;
import com.chenxianping.blog.validatedGroup.EditArticleToDraft;
import com.chenxianping.blog.validatedGroup.EditArticleToPublish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chenxp
 * @date 2022/5/11 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSaveDTO {

    @NotNull(message = "文章id不为空", groups = {EditArticleToDraft.class, EditArticleToPublish.class})
    private Integer articleId;

    @NotBlank(message = "文章标题不能为空", groups = {AddDraftArticle.class, AddPublishArticle.class, EditArticleToDraft.class, EditArticleToPublish.class})
    @Size(min = 5, max = 30 ,message = "文章标题需为5-30个字", groups = {AddDraftArticle.class, AddPublishArticle.class, EditArticleToDraft.class, EditArticleToPublish.class})
    private String articleTitle;

    @NotEmpty(message = "文章内容不能为空", groups = {AddPublishArticle.class, EditArticleToPublish.class})
    private String articleContent;

    private String articleCoverImage;

    private String articleSubUrl;

    private Byte articleStatus;

    private Integer userId;

    @NotNull(message = "请选择文章分类", groups = {AddPublishArticle.class, EditArticleToPublish.class})
    private Integer categoryId;

    @NotBlank(message = "至少选择一个标签", groups = {AddPublishArticle.class, EditArticleToPublish.class})
    private String articleTags;

    private Byte original;

    private Byte enableComment;
}
