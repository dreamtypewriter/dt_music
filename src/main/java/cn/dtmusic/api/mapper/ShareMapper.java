package cn.dtmusic.api.mapper;

import org.springframework.stereotype.Repository;

import cn.dtmusic.api.dto.ShareDto;
import cn.dtmusic.api.entity.Share;

import java.util.List;

@Repository
public interface ShareMapper {
    int deleteByPrimaryKey(Integer shareId);

    int insert(Share record);

    Share selectByPrimaryKey(Integer shareId);

    List<Share> selectAll();

    int updateByPrimaryKey(Share record);

    Integer disableById(Integer shareId);

    List<ShareDto> selectShareDtoList(List<Integer> userIds);

    int updateShareCount(Integer userId);
}
