package cn.dtmusic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dtmusic.api.entity.Banner;
import cn.dtmusic.api.mapper.BannerMapper;
import cn.dtmusic.api.service.BannerService;

import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author 王金
 * @create 2020-09-27
 * @since 1.0.0
 */

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerMapper bannerMapper;

    @Autowired
    public BannerServiceImpl(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    //根据bannerId删除banner
    public int removeBannerByPrimaryKey(Integer bannerId) {
        return bannerMapper.deleteByPrimaryKey(bannerId);
    }

    //添加banner对象
    public int addBanner(Banner banner) {
        return bannerMapper.insert(banner);
    }

    //修改banner对象
    public int modifyBanner(Banner banner) {
        return bannerMapper.updateByPrimaryKey(banner);
    }

    //查询单个banner对象
    public Banner findSingleBannerByPrimaryKey(Integer bannerId) {
        return bannerMapper.selectByPrimaryKey(bannerId);
    }

    //查询出所有的banner对象
    public List<Banner> findAllBanner() {
        return bannerMapper.selectAll();
    }

    //修改banner状态
    public int modifyBannerStatus(Integer bannerId) {
        return bannerMapper.updateBannerStatus(bannerId);
    }
}
