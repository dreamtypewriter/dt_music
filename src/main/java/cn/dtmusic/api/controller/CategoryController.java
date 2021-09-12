package cn.dtmusic.api.controller;


import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Category;
import cn.dtmusic.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("deleteByPrimaryId/{categoryId}")
    @ResponseBody
    public int deleteByPrimaryId(@PathVariable Integer categoryId) {
        return categoryService.deleteByPrimaryKey(categoryId);
    }
    @RequestMapping("insert")
    @ResponseBody
    public int insert(@RequestBody Category record) {
        return categoryService.insert(record);
    }
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Category> selectAll() {
        return categoryService.selectAll();
    }
    @RequestMapping("updateByPrimaryId")
    @ResponseBody
    public int updateByPrimaryId(@RequestBody Category record) {
        return categoryService.updateByPrimaryKey(record);
    }

    @RequestMapping("categoryList")
    @ResponseBody
    public PageInfo<Category> queryDimCategoryPageList(@RequestParam(value = "categoryTypeName",defaultValue = "")String categoryTypeName,
                                                      @RequestParam(value = "categoryName",defaultValue = "")String categoryName,
                                                      @RequestParam(value = "offset",defaultValue = "0") Integer offset,
                                                      @RequestParam(value = "limit",defaultValue = "20") Integer limit) {
        PageInfo<Category> pageInfo = categoryService.queryDimCategoryPageList(categoryTypeName, categoryName, offset, limit);
        return pageInfo;
    }

}
