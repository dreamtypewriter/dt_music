package cn.dtmusic.api.service;

import java.util.List;

import cn.dtmusic.api.entity.Song;

/**
 * @ description: @ date: 2020/10/12 @ time: 10:51 @ author: Zhang wei @ since: 1.0.0
 */
public interface RecommendService {
	List<Song> getRecommendedSongs(int size);

}
