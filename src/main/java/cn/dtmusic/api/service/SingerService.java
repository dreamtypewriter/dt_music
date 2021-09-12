package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.entity.Singer;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/9/27
 * @ time:      20:06
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface SingerService {
    Singer getSingerById(Integer singerId);

    Integer addSinger(Singer singer);

    Integer updateSinger(Singer singer);

    Integer removeSinger(Integer singerId);

    PageInfo<Singer> getSingerByPage(Integer offset, Integer limit, String singerName, String singerType, String singerLanguage);

    List<Singer> showCollectedSingerByUser(Integer userId);

    Integer collectSingerByUser( Integer userId,Integer singerId);

    Boolean isCollectSingerByUser(Integer userId, Integer singerId);

    List<Singer> findTopSingers();
}
