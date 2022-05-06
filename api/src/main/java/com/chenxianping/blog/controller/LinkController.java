package com.chenxianping.blog.controller;

import com.chenxianping.blog.entity.BlogLink;
import com.chenxianping.blog.service.LinkService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 * @date 2022/5/6 8:47
 */
@RestController
@CrossOrigin
@RequestMapping("/link")
@Api(value = "提供友链相关的操作接口", tags = "友链管理")
public class LinkController {

    @Resource
    private LinkService linkService;

    @PostMapping("/save")
    @ApiOperation("保存友链")
    public ResultVO save(@RequestBody BlogLink link){
        return  linkService.save(link);
    }

    @GetMapping("/info/{linkId}")
    @ApiOperation("获取友链信息")
    public ResultVO get(@PathVariable Integer linkId){
        return linkService.selectById(linkId);
    }

    @GetMapping("/links")
    @ApiOperation("分页获取友链列表")
    public ResultVO linksForPage(@RequestParam("page") Integer page,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam(value = "linkSource" ,required = false) Integer linkSource,
                                 @RequestParam(value = "status", required = false) Integer status,
                                 @RequestParam(value = "keywords", required = false) String keywords){

        return linkService.selectAll(page, pageSize, linkSource, status, keywords);
    }

    @PutMapping("/audit/{linkId}/{status}")
    @ApiOperation("审核友链")
    public ResultVO delete(@PathVariable("linkId") Integer linkId, @PathVariable("status") Integer status){
        return linkService.auditLink(linkId, status);
    }

}
