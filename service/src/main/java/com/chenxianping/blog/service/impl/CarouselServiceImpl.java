package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogCarouselMapper;
import com.chenxianping.blog.dao.BlogCarouselMapperCustom;
import com.chenxianping.blog.entity.BlogCarousel;
import com.chenxianping.blog.service.CarouselService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author chenxp
 * @date 2022/5/7 10:23
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private BlogCarouselMapper blogCarouselMapper;

    @Resource
    private BlogCarouselMapperCustom blogCarouselMapperCustom;

    /**
     * 保存轮播图
     * id不为空，则更新轮播图；id为空，则新增轮播图
     * @param carousel
     * @return
     */
    @Override
    public ResultVO save(BlogCarousel carousel) {
        Date date = new Date();
        ResultVO result;
        if(null == carousel){
            result = new ResultVO(ResStatus.NO, "数据错误，请重试！", null);
        }else if(null == carousel.getId() || 0 == carousel.getId()) {
            //新增
            carousel.setShow((byte) 1);
            carousel.setCreateTime(date);
            carousel.setUpdateTime(date);
            if(blogCarouselMapper.insert(carousel) > 0) {
                result = new ResultVO(ResStatus.OK, "新增轮播图成功！", null);
            }else {
                result = new ResultVO(ResStatus.NO, "新增轮播图失败！", null);
            }
        }else {
            //更新
            carousel.setUpdateTime(date);
            if(blogCarouselMapper.updateByPrimaryKeySelective(carousel) > 0){
                result = new ResultVO(ResStatus.OK, "更新轮播图成功！", null);
            }else {
                result = new ResultVO(ResStatus.NO, "更新轮播图失败！", null);
            }
        }
        return result;
    }

    /**
     * 根据id获取轮播图信息
     * @param id 轮播图id
     * @return
     */
    @Override
    public ResultVO selectById(Integer id) {
        BlogCarousel carousel = blogCarouselMapper.selectByPrimaryKey(id);
        ResultVO result;
        if(null == carousel) {
            result = new ResultVO(ResStatus.NO, "非法轮播图Id", null);
        }else {
            result = new ResultVO(ResStatus.OK, "SUCCESS", carousel);
        }
        return result;
    }

    /**
     * 根据id删除轮播图
     * @param id 轮播图id
     * @return
     */
    @Override
    public ResultVO deleteById(Integer id) {
        ResultVO result;
        if(blogCarouselMapper.deleteByPrimaryKey(id) > 0) {
            result = new ResultVO(ResStatus.OK, "删除轮播图成功", null);
        }else {
            result = new ResultVO(ResStatus.NO, "删除轮播图失败", null);
        }
        return result;
    }

    /**
     * 根据id切换轮播图展示状态
     * @param id 轮播图id
     * @return
     */
    @Override
    public ResultVO switchShowById(Integer id) {
        ResultVO result;
        BlogCarousel carouselDb = blogCarouselMapper.selectByPrimaryKey(id);
        if(null == carouselDb) {
            result = new ResultVO(ResStatus.NO, "非法轮播图Id", null);
        }else {
            BlogCarousel carousel = new BlogCarousel();
            carousel.setId(id);
            // 切换show的值
            int isShow = (carouselDb.getShow() + 1) % 2;
            carousel.setShow((byte) isShow);

            int effectRows = blogCarouselMapper.updateByPrimaryKeySelective(carousel);
            if(effectRows > 0) {
                result = new ResultVO(ResStatus.OK, "切换展示状态成功", null);
            }else {
                result = new ResultVO(ResStatus.NO, "切换展示状态失败", null);
            }
        }
        return result;
    }

    /**
     * 获取状态为展示的轮播图
     * @return
     */
    @Override
    public ResultVO selectAllIsShow() {
        Example example = new Example(BlogCarousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("show",(byte) 1);
        example.orderBy("sort").desc();
        List<BlogCarousel> carousels = blogCarouselMapper.selectByExample(example);
        return new ResultVO(ResStatus.OK, "SUCCESS", carousels);
    }

    /**
     * 根据查询条件获取轮播图
     * @param type 轮播图类型 1-站内 2-推广
     * @param showStatus 展示状态 0-不展示 1-展示
     * @return
     */
    @Override
    public ResultVO selectAll(Integer type, Integer showStatus) {
        List<BlogCarousel> carousels = blogCarouselMapperCustom.selectWithCondition(type, showStatus);
        return new ResultVO(ResStatus.OK, "SUCCESS", carousels);
    }
}
