package cn.dtmusic.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.ShareDto;
import cn.dtmusic.api.entity.Share;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.mapper.FollowMapper;
import cn.dtmusic.api.mapper.ShareMapper;
import cn.dtmusic.api.service.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/4
 * @ time:      16:31
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Service
public class ShareServiceImpl implements ShareService {
    private final FollowMapper followMapper;
    private final ShareMapper shareMapper;

    @Autowired
    public ShareServiceImpl(FollowMapper followMapper, ShareMapper shareMapper) {
        this.followMapper = followMapper;
        this.shareMapper = shareMapper;
    }

    @Override
    @Transactional
    public Integer addShare(Share share) {
        int update = shareMapper.updateShareCount(share.getUserId());
        return update * shareMapper.insert(share);
    }

    @Override
    @Transactional
    public Integer removeShare(Integer shareId) {
        return shareMapper.disableById(shareId);
    }

    @Override
    public PageInfo<ShareDto> getSharePage(Integer pageSize, Integer pageNum, int userId) {
        List<User> users = followMapper.queryFollowList(userId);
        List<Integer> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getUserId());
        }
        userIds.add(userId);
        PageHelper.startPage(pageNum, pageSize);
        List<ShareDto> shareDtoList = shareMapper.selectShareDtoList(userIds);
        for (ShareDto shareDto : shareDtoList) {
            shareDto.createResourceTypeName();
            shareDto.createResourceUrl();
        }
        return new PageInfo<>(shareDtoList);
    }


}
