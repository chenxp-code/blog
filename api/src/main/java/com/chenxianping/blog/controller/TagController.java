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
    @ApiOperation("新增标签")
    public ResultVO save(@RequestBody BlogTag tag){
        return tagService.save(tag);
    }

    @GetMapping("/info/{tagId}")
    @ApiOperation("获取标签信息")
    public ResultVO tag(@PathVariable Integer tagId){
        return tagService.selectById(tagId);
    }

    @GetMapping("/tags")
    @ApiOperation("分页获取标签列表")
    public ResultVO tagsForPage(@RequestParam Integer page,
                                 @RequestParam Integer pageSize){
        if(page == null || page < 1){
            page = 1;
        }
        if(pageSize == null || pageSize < 10){
            pageSize = 10;
        }
        if(pageSize > 50 ){
            pageSize = 50;
        }
        return tagService.selectAll(page, pageSize);
    }

    @DeleteMapping("/delete/{tagId}")
    @ApiOperation("删除标签")
    public ResultVO delete(@PathVariable Integer tagId){
        return tagService.deleteById(tagId);
    }
}
