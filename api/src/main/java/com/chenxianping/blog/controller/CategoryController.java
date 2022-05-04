package com.chenxianping.blog.controller;

import com.chenxianping.blog.entity.BlogCategory;
import com.chenxianping.blog.service.CategoryService;
import com.chenxianping.blog.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenxp
 * @date 2022/5/4 14:45
 */

@CrossOrigin
@RestController
@RequestMapping("/category")
@Api(value = "提供分类相关的操作接口", tags = "分类管理")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/save")
    @ApiOperation("保存标签")
    public ResultVO save(@RequestBody BlogCategory category){
        return categoryService.save(category);
    }

    @GetMapping("/info/{categoryId}")
    @ApiOperation("获取分类信息")
    public ResultVO get(@PathVariable Integer categoryId){
        return categoryService.selectById(categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    @ApiOperation("删除分类")
    public ResultVO delete(@PathVariable Integer categoryId){
        return categoryService.deleteById(categoryId);
    }

    @GetMapping("/categories")
    @ApiOperation("分页获取分类列表")
    public ResultVO categoriesForPage(@RequestParam Integer page,
                                      @RequestParam Integer pageSize){

        return categoryService.selectAll(page, pageSize);
    }
}
