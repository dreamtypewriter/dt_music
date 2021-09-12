package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dtmusic.api.entity.Song;
import cn.dtmusic.api.mapper.RecommendMapper;
import cn.dtmusic.api.service.RecommendService;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/12
 * @ time:      10:52
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    private final RecommendMapper recommendMapper;

    @Autowired
    public RecommendServiceImpl(RecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }

    @Override
    public List<Song> getRecommendedSongs(int size) {
        return recommendMapper.selectSongs(size);
    }


}
