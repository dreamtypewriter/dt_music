package cn.dtmusic.api.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Category;
import cn.dtmusic.api.mapper.CategoryMapper;
import cn.dtmusic.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer categoryId) {
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Category> queryDimCategoryPageList(String categoryTypeName, String categoryName,Integer offset,Integer limit) {
        /*PageHelper.startPage(pageNum,pageSize);*/
        //offset偏移量，limit每页条数
        PageHelper.offsetPage(offset, limit);
        List<Category> categories = categoryMapper.queryDimCategoryList(categoryTypeName, categoryName);
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        return pageInfo;
    }

    @Override
    public List<Category> findByType(String typeName) {
        return categoryMapper.selectByType(typeName);
    }

}
