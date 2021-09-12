package cn.dtmusic.api.service;

import java.util.List;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;

/**
 * @ description: @ date: 2020/10/13 @ time: 14:57 @ author: Zhang wei @ since: 1.0.0
 */
public interface SearchService {
	List<SongDto> getSongsByName(String keyword, int size);

	List<SongList> getSongListsByName(String searchKeyword, int size);

	List<Singer> getSingersByName(String searchKeyword, int size);

	List<AlbumDto> getAlbumsByName(String searchKeyword, int size);

	List<User> getUsersByName(String searchKeyword, int size);
}
