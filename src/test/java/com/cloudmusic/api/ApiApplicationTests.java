package com.cloudmusic.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.dtmusic.api.entity.Banner;
import cn.dtmusic.api.mapper.BannerMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApiApplicationTests {
	@Autowired
	private BannerMapper bannerMapper;

    @Test
    void testInsert() {
    	Banner banner = new Banner();
    	banner.setBannerId(1);
    	banner.setBannerNo(3);
    	banner.setBannerPath("http://qz4egjoi4.hn-bkt.clouddn.com/39706558-e309-4jpg");
    	int updateByPrimaryKey = bannerMapper.updateByPrimaryKey(banner);
    	assertEquals(updateByPrimaryKey, 1);
    }

}
