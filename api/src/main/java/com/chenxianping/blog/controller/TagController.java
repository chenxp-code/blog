package com.chenxianping.blog.controller;

import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.service.TagService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 * @date 2022/5/2 14:04
 */

@CrossOrigin
@RestController
@RequestMapping("/tag")
@Api(value = "提供标签相关的操作接口", tags = "标签管理")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/save")
    @ApiOperation("保存标签")
    public ResultVO save(@RequestBody BlogTag tag){
        return tagService.save(tag);
    }

    @GetMapping("/info/{tagId}")
    @ApiOperation("获取标签信息")
    public ResultVO get(@PathVariable Integer tagId){
        return tagService.selectById(tagId);
    }

    @GetMapping("/tags")
    @ApiOperation("分页获取标签列表")
    public ResultVO tagsForPage(@RequestParam("page") Integer page,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "keywords", required = false) String keywords){

        return tagService.selectAll(page, pageSize, keywords);
    }

    @DeleteMapping("/delete/{tagId}")
    @ApiOperation("删除标签")
    public ResultVO delete(@PathVariable Integer tagId){
        return tagService.deleteById(tagId);
    }
}
