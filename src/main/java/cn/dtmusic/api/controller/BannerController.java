package cn.dtmusic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dtmusic.api.entity.Banner;
import cn.dtmusic.api.service.BannerService;
import cn.dtmusic.api.service.FileService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Description: 首页banner相关服务
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日下午1:26:34
 * @since 0.0.1
 */
@Controller
@RequestMapping("banner")
public class BannerController {

	@Autowired
	private final BannerService bannerService;
	private final FileService fileService;

	/**
	 * Constructor
	 * 
	 * @param fileService
	 * @param bannerService
	 */
	public BannerController(FileService fileService, BannerService bannerService) {
		this.bannerService = bannerService;
		this.fileService = fileService;
	}

	@ResponseBody
	@RequestMapping("add")
	public int addBanner(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String pictureUrl = fileService.uploadPicture(multipartFile);
		Banner banner = new Banner();
		banner.setBannerPath(pictureUrl);
		banner.setBannerNo((int) new Date().getTime());
		return bannerService.addBanner(banner);
	}

	@ResponseBody
	@RequestMapping("del")
	public int delBannerByPrimaryKey(Integer bannerId) {
		return bannerService.modifyBannerStatus(bannerId);
	}

	@ResponseBody
	@RequestMapping("update")
	public int updateBanner(@RequestParam("file") MultipartFile multipartFile, @RequestParam("bannerId") Integer bannerId)
			throws IOException {
		Banner banner = bannerService.findSingleBannerByPrimaryKey(bannerId);
		banner.setBannerPath(fileService.uploadPicture(multipartFile));
		return bannerService.modifyBanner(banner);
	}

	@ResponseBody
	@RequestMapping("showSingleBanner")
	public Banner showSingleBanner(Integer bannerId) {
		return bannerService.findSingleBannerByPrimaryKey(bannerId);
	}

	@ResponseBody
	@RequestMapping("listBanner")
	public List<Banner> listBanner() {
		return bannerService.findAllBanner();
	}

}
