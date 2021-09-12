package cn.dtmusic.api.service;

import cn.dtmusic.api.entity.Like;

/**
 * @ description:
 * @ date:      2020/10/7
 * @ time:      15:11
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface LikeService {
    int changeLikeCount(Like like);

    int getLikeCount(byte resourceType,int resourceId);
}
