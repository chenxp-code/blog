package com.chenxianping.blog.controller;

import com.chenxianping.blog.entity.BlogCarousel;
import com.chenxianping.blog.service.CarouselService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 * @date 2022/5/7 10:24
 */
@RestController
@CrossOrigin
@RequestMapping("/carousel")
@Api(value = "提供轮播图相关的操作接口", tags = "轮播图管理")
public class CarouselController {

    @Resource
    private CarouselService carouselService;


    @PostMapping("/save")
    @ApiOperation("保存轮播图信息")
    public ResultVO save(@RequestBody BlogCarousel carousel){
        return  carouselService.save(carousel);
    }

    @GetMapping("/carousels-show")
    @ApiOperation("获取状态为展示轮播图列表")
    public ResultVO listIsShow(){
        return carouselService.selectAllIsShow();
    }

    @GetMapping("/carousels")
    @ApiOperation("根据条件获取轮播图列表")
    public ResultVO list(@RequestParam(value = "type", required = false)Integer type,
                         @RequestParam(value = "showStatus", required = false)Integer showStatus){
        return carouselService.selectAll(type, showStatus);
    }

    @GetMapping("/info/{carouselId}")
    @ApiOperation("获取轮播图信息")
    public ResultVO get(@PathVariable Integer carouselId){
        return carouselService.selectById(carouselId);
    }

    @PutMapping("/switchShow/{carouselId}")
    @ApiOperation("切换轮播图展示状态")
    public ResultVO switchShowById(@PathVariable Integer carouselId){
        return carouselService.switchShowById(carouselId);
    }

    @DeleteMapping("/delete/{carouselId}")
    @ApiOperation("删除轮播图")
    public ResultVO delete(@PathVariable Integer carouselId){
        return carouselService.deleteById(carouselId);
    }
}
