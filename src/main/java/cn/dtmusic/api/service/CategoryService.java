package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Category;

import java.util.List;

public interface CategoryService{

    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    PageInfo<Category> queryDimCategoryPageList(String categoryTypeName, String categoryName, Integer offset, Integer limit);


    List<Category> findByType(String typeName);
}
