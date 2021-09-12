package cn.dtmusic.api.service;

import org.springframework.stereotype.Service;

import cn.dtmusic.api.entity.Banner;

import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author 王金
 * @create 2020-09-27
 * @since 1.0.0
 */
@Service
public interface BannerService {

    //根据bannerId删除banner
    int removeBannerByPrimaryKey(Integer bannerId);

    //添加banner对象
    int addBanner(Banner banner);

    //修改banner对象
    int modifyBanner(Banner banner);

    //查询单个banner对象
    Banner findSingleBannerByPrimaryKey(Integer bannerId);

    //查询出所有的banner对象
    List<Banner> findAllBanner();

    //改变banner状态
    int modifyBannerStatus(Integer bannerId);

}
