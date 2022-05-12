package com.chenxianping.blog.controller;

import com.chenxianping.blog.dto.ArticleSaveDTO;
import com.chenxianping.blog.entity.BlogArticle;
import com.chenxianping.blog.service.ArticleService;
import com.chenxianping.blog.utils.CopyUtil;
import com.chenxianping.blog.validatedGroup.AddDraftArticle;
import com.chenxianping.blog.validatedGroup.AddPublishArticle;
import com.chenxianping.blog.validatedGroup.EditArticleToDraft;
import com.chenxianping.blog.validatedGroup.EditArticleToPublish;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
@Api(value = "提供文章相关的操作接口",tags = "文章管理")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 保存文章分以下几种情况：
     * 1.新增文章，保存为草稿 -无文章Id
     * 2.新增文章，发布文章 -无文章Id
     * 3.编辑状态为草稿的文章，保存为草稿 -有文章Id
     * 4.编辑状态为草稿的文章，发布文章 -有文章Id
     * 5.编辑已发布的文章，保存为草稿 -有文章Id
     * 6.编辑已发布的文章，继续发布 -有文章Id
     * 【无id 新增 直接发布】 2
     * 【无id 新增 保存草稿】 1
     * 【有id 编辑 直接发布】 4 6
     * 【有id 编辑 保存为草稿】3 5
     */
    @PostMapping("/addDraftArticle")
    @ApiOperation("保存新文章草稿")
    public ResultVO addDraftArticle(@RequestBody @Validated({AddDraftArticle.class}) ArticleSaveDTO articleSaveDTO, BindingResult bindingResult){
        BlogArticle article = CopyUtil.copy(articleSaveDTO, BlogArticle.class);
        return articleService.saveArticle(article);
    }

    @PostMapping("/addPublishArticle")
    @ApiOperation("发布新文章")
    public ResultVO addPublishArticle(@RequestBody @Validated({AddPublishArticle.class}) ArticleSaveDTO articleSaveDTO, BindingResult bindingResult){
        BlogArticle article = CopyUtil.copy(articleSaveDTO, BlogArticle.class);
        return articleService.saveArticle(article);
    }

    @PostMapping("/updateArticleToDraft")
    @ApiOperation("将文章(草稿)存为草稿")
    public ResultVO updateArticleToDraft(@RequestBody @Validated({EditArticleToDraft.class}) ArticleSaveDTO articleSaveDTO, BindingResult bindingResult){
        BlogArticle article = CopyUtil.copy(articleSaveDTO, BlogArticle.class);
        return articleService.updateArticle(article);
    }

    @PostMapping("/updateArticleToPublish")
    @ApiOperation("将文章(草稿)发布")
    public ResultVO updateArticleToPublish(@RequestBody @Validated({EditArticleToPublish.class}) ArticleSaveDTO articleSaveDTO, BindingResult bindingResult){
        BlogArticle article = CopyUtil.copy(articleSaveDTO, BlogArticle.class);
        return articleService.updateArticle(article);
    }
    @GetMapping("/{articleId}")
    @ApiOperation("获取文章")
    public ResultVO getById(@PathVariable("articleId") Integer articleId){
        return articleService.getArticleById(articleId);
    }

}
