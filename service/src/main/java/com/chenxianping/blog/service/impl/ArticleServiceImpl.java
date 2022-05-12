package com.chenxianping.blog.service.impl;

import com.chenxianping.blog.dao.BlogArticleMapper;
import com.chenxianping.blog.dao.BlogCategoryMapper;
import com.chenxianping.blog.dao.BlogTagMapper;
import com.chenxianping.blog.dao.BlogTagMapperCustom;
import com.chenxianping.blog.entity.BlogArticle;
import com.chenxianping.blog.entity.BlogCategory;
import com.chenxianping.blog.entity.BlogTag;
import com.chenxianping.blog.service.ArticleService;
import com.chenxianping.blog.vo.ResStatus;
import com.chenxianping.blog.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author chenxp
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private BlogTagMapperCustom blogTagMapperCustom;

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    /**
     * 新增文章
     * 只有在发布文章时，才同步更新分类和标签的文章数
     * 文章保存为草稿时，不计入统计
     * @param blogArticle
     * @return
     */
    @Transactional
    @Override
    public ResultVO saveArticle(BlogArticle blogArticle) {
        //判断有无文章id
        if (null != blogArticle.getArticleId()) {
            return new ResultVO(ResStatus.NO, "文章id异常，请重试", null);
        }

        //处理分类
        Integer cid = blogArticle.getCategoryId();
        if (null != cid && 2 == blogArticle.getArticleStatus()) {
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(cid);
            if (null != blogCategory) {
                blogCategory.setCategoryAmount(blogCategory.getCategoryAmount() + 1);
                blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
            } else {
                return new ResultVO(ResStatus.NO, "分类不存在", null);
            }
        }

        //处理标签
        String tagsName = blogArticle.getArticleTags();
        if (2 == blogArticle.getArticleStatus() && null != tagsName && tagsName.trim().length() > 0) {
            handleArticleTags(tagsName);
        }

        //设置文章默认值
        Date date = new Date();
        blogArticle.setCreateTime(date);
        blogArticle.setUpdateTime(date);
        blogArticle.setArticlePageView(0);
        blogArticle.setDeleted((byte) 0);
        blogArticle.setUserId(1);
        blogArticle.setEnableComment((byte) 0);
        blogArticle.setTop((byte) 0);
        int effectLine = blogArticleMapper.insert(blogArticle);
        String message = blogArticle.getArticleStatus() == 1 ? "保存草稿" : "发布文章";
        if (effectLine > 0) {
            return new ResultVO(ResStatus.OK, message + "成功", null);
        } else {
            return new ResultVO(ResStatus.NO, message + "失败", null);
        }
    }

    /**
     * 更新文章
     *
     * @param blogArticle
     * @return
     */
    @Override
    public ResultVO updateArticle(BlogArticle blogArticle) {
        BlogArticle articleDb = blogArticleMapper.selectByPrimaryKey(blogArticle.getArticleId());
        //无效文章id
        if (null == articleDb) {
            return new ResultVO(ResStatus.NO, "文章id异常，请重试", null);
        }

        //文章状态
        if (1 != blogArticle.getArticleStatus() && 2 != blogArticle.getArticleStatus()) {
            return new ResultVO(ResStatus.NO, "文章状态异常，请重试", null);
        }
        if (2 == blogArticle.getArticleStatus() && 1 == articleDb.getArticleStatus()) {
            //处理分类
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(blogArticle.getCategoryId());
            if (null != blogCategory) {
                blogCategory.setCategoryAmount(blogCategory.getCategoryAmount() + 1);
                blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
            } else {
                return new ResultVO(ResStatus.NO, "分类不存在", null);
            }

            //处理标签
            String tagsName = blogArticle.getArticleTags();
            if (null != tagsName && tagsName.trim().length() > 0) {
                handleArticleTags(tagsName);
            }
        }
        if (2 == articleDb.getArticleStatus() && 2 == blogArticle.getArticleStatus()) {
            //处理分类
            if (!articleDb.getCategoryId().equals(blogArticle.getCategoryId())) {
                handleArticleCategory(blogArticle.getCategoryId(), articleDb.getCategoryId());
            }

            //处理标签
            if (!articleDb.getArticleTags().equals(blogArticle.getArticleTags())) {
                //找出被取消的标签
                String[] oldTags = articleDb.getArticleTags().split(",");
                String[] newTags = blogArticle.getArticleTags().split(",");
                //将标签数组转换成List集合
                List<String> oldTagList = new ArrayList<>(oldTags.length);
                Collections.addAll(oldTagList, oldTags);
                List<String> newTagList = new ArrayList<>(newTags.length);
                Collections.addAll(newTagList, newTags);

                //遍历旧标签集合找出被取消的标签
                for (String tag : oldTagList) {
                    if (!newTagList.contains(tag)) {
                        blogTagMapperCustom.decreaseTagAmountByName(tag.trim());
                    } else {
                        //去除现存的旧标签，最后剩下的标签即为需要新增的标签
                        newTagList.remove(tag);
                    }
                }

                //新增标签
                List<BlogTag> addTags = new ArrayList<>();
                for (String tag : newTagList) {
                    if(tag.trim().length()>0 &&
                            Boolean.FALSE.equals(isExist(tag.trim()))) {
                        addTags.add(createPrimaryTag(tag));
                    }
                    if(tag.trim().length()>0 && Boolean.TRUE.equals(isExist(tag.trim()))) {
                        blogTagMapperCustom.increaseTagAmountByName(tag.trim());
                    }
                }
                if (!addTags.isEmpty()){
                    blogTagMapper.insertList(addTags);
                }
            }
        }

        //保存文章
        blogArticle.setUpdateTime(new Date());
        int effectLine = blogArticleMapper.updateByPrimaryKeySelective(blogArticle);
        if (effectLine > 0) {
            return new ResultVO(ResStatus.OK, "修改成功", null);
        } else {
            return new ResultVO(ResStatus.NO, "系统错误，稍后再试", null);
        }
    }

    /**
     * 逻辑删除文章，进入回收站
     * @param articleId 文章id
     * @return
     */
    @Override
    public ResultVO deleteArticle(Integer articleId) {
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryKey(articleId);
        if (blogArticle == null) {
            return new ResultVO(ResStatus.NO, "非法文章id", null);
        } else {
            blogArticle.setDeleted((byte) 1);
            blogArticleMapper.updateByPrimaryKeySelective(blogArticle);
            return new ResultVO(ResStatus.OK, "删除文章成功", null);
        }
    }

    @Override
    public ResultVO deleteArticles(Integer[] articleIds) {
        //blogArticleMapper.deleteBatch(articleIds);
        return null;
    }

    @Override
    public ResultVO getArticleById(Integer articleId) {
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryKey(articleId);
        if (blogArticle == null) {
            return new ResultVO(ResStatus.NO, "不存在id：" + articleId + "的文章", null);
        } else {
            return new ResultVO(ResStatus.OK, "", blogArticle);
        }
    }

    @Override
    public ResultVO getArticlesByKeyword(String keyword) {
        Example example = new Example(BlogArticle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("articleTitle", keyword);
        List<BlogArticle> blogArticles = blogArticleMapper.selectByExample(example);
        return new ResultVO(ResStatus.OK, "", blogArticles);
    }

    /**
     * 统一处理文章的标签
     *
     * @param tagsName 将标签名数组转为字符串的结果
     */
    private void handleArticleTags(String tagsName) {
        //文章当前新的标签数组
        String[] tags = tagsName.split(",");

        //存放新增的标签
        List<BlogTag> newTags = new ArrayList<>();
        for (String tag : tags) {
            //检查标签是否存在
            if(tag.trim().length()>0 && Boolean.TRUE.equals(isExist(tag.trim()))){
                blogTagMapperCustom.increaseTagAmountByName(tag.trim());
            }
            if(tag.trim().length()>0 && Boolean.FALSE.equals(isExist(tag.trim()))){
                newTags.add(createPrimaryTag(tag));
            }
        }

        //批量新增标签
        if (!newTags.isEmpty()) {
            blogTagMapper.insertList(newTags);
        }
    }

    /**
     * 统一处理文章分类变更
     * @param newCategoryId
     * @param oldCategoryId
     */
    private void handleArticleCategory(Integer newCategoryId, Integer oldCategoryId){
        BlogCategory oldCategory = blogCategoryMapper.selectByPrimaryKey(oldCategoryId);
        BlogCategory newCategory = blogCategoryMapper.selectByPrimaryKey(newCategoryId);

        //还原旧分类的amount-1
        oldCategory.setCategoryAmount(oldCategory.getCategoryAmount() - 1);
        blogCategoryMapper.updateByPrimaryKeySelective(oldCategory);

        //给选择的分类amount+1
        newCategory.setCategoryAmount(newCategory.getCategoryAmount() + 1);
        blogCategoryMapper.updateByPrimaryKeySelective(newCategory);
    }

    /**
     * 统一创建新标签
     * @param tagName 标签名
     * @return
     */
    private BlogTag createPrimaryTag(String tagName){
        BlogTag blogTag = new BlogTag();
        blogTag.setTagSort(0);
        blogTag.setTagName(tagName);
        blogTag.setTagAmount(1);
        blogTag.setUpdateTime(new Date());
        blogTag.setCreateTime(new Date());
        blogTag.setDeleted((byte) 0);

        return blogTag;
    }

    /**
     * 判断tagName是否存在
     *
     * @param tagName
     * @return
     */
    private Boolean isExist(String tagName) {
        //tagName唯一校验
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tagName", tagName);
        List<BlogTag> tags = blogTagMapper.selectByExample(example);
        return !tags.isEmpty();
    }
}
