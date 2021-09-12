package cn.dtmusic.api.service;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.ShareDto;
import cn.dtmusic.api.entity.Share;

/**
 * @ description:
 * @ date:      2020/10/4
 * @ time:      16:29
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public interface ShareService {
    public Integer addShare(Share share);

    public Integer removeShare(Integer shareId);

    PageInfo<ShareDto> getSharePage(Integer pageSize, Integer pageNum, int userId);
}
