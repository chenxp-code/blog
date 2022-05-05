package com.chenxianping.blog.controller;

import com.chenxianping.blog.entity.BlogResource;
import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.service.ResourceService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 * @date 2022/5/5 21:27
 */
@RestController
@CrossOrigin
@RequestMapping("/resource")
@Api(value = "提供资源库相关的操作接口", tags = "资源库管理")
public class ResourceController {

    @Resource
    private  ResourceService resourceService;

    @PostMapping("/save")
    @ApiOperation("保存资源")
    public ResultVO save(@RequestBody BlogResource resource){
        return resourceService.save(resource);
    }

    @GetMapping("/info/{resourceId}")
    @ApiOperation("获取资源信息")
    public ResultVO get(@PathVariable Integer resourceId){
        return resourceService.selectById(resourceId);
    }

    @GetMapping("/resources")
    @ApiOperation("分页获取资源列表")
    public ResultVO tagsForPage(@RequestParam("page") Integer page,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "categoryId" ,required = false) Integer categoryId,
                                @RequestParam(value = "keywords", required = false) String keywords){

        return resourceService.selectAll(page, pageSize, categoryId, keywords);
    }

    @DeleteMapping("/delete/{resourceId}")
    @ApiOperation("删除资源")
    public ResultVO delete(@PathVariable Integer resourceId){
        return resourceService.deleteById(resourceId);
    }
}
