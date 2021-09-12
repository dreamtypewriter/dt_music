package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dtmusic.api.entity.Category;
import cn.dtmusic.api.mapper.CategoryMapper;
import cn.dtmusic.api.service.MyMusicService;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/14
 * @since 1.0.0
 */
@Service
public class MyMusicServiceImpl implements MyMusicService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> showCategories() {
        List<Category> categories = categoryMapper.selectAll();
        return categories;
    }
}
