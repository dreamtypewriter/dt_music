package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.AlbumDto;
import cn.dtmusic.api.entity.Singer;
import cn.dtmusic.api.service.AlbumService;
import cn.dtmusic.api.service.FileService;
import cn.dtmusic.api.service.SingerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Description: 专辑相关服务
 * 
 * @author dreamtypewriter github
 * @date 2021年9月12日下午1:23:41
 * @since 0.0.1
 */
@Controller
@RequestMapping("album")
public class AlbumController {

	private final AlbumService albumService;
	private final SingerService singerService;
	private final FileService fileSerivce;

	/**
	 * Constructor
	 * 
	 * @param albumService
	 * @param fileSerivce
	 * @param singerService
	 */
	@Autowired
	public AlbumController(AlbumService albumService, FileService fileSerivce, SingerService singerService) {
		this.albumService = albumService;
		this.singerService = singerService;
		this.fileSerivce = fileSerivce;
	}

	@ResponseBody
	@RequestMapping("add")
	public int addAlbum(@RequestParam("albumName") String albumName, @RequestParam("albumLanguage") String albumLanguage,
			@RequestParam("singerId") Integer singerId, @RequestParam("releaseCompany") String releaseCompany,
			@RequestParam("releaseDate") Date releaseDate, @RequestParam("albumDescription") String albumDescription,
			@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String pictureUrl = fileSerivce.uploadPicture(multipartFile);
		Singer singer = singerService.getSingerById(singerId);
		AlbumDto album = new AlbumDto();
		album.setSinger(singer);
		album.setAlbumImgPath(pictureUrl);
		album.setAlbumName(albumName);
		album.setAlbumLanguage(albumLanguage);
		album.setReleaseCompany(releaseCompany);
		album.setReleaseDate(releaseDate);
		album.setAlbumDescription(albumDescription);
		return albumService.addAlbum(album);
	}

	@ResponseBody
	@RequestMapping("addNoImg")
	public int addAlbumNoImg(@RequestParam("albumName") String albumName, @RequestParam("albumLanguage") String albumLanguage,
			@RequestParam("singerId") Integer singerId, @RequestParam("releaseCompany") String releaseCompany,
			@RequestParam("releaseDate") Date releaseDate, @RequestParam("albumDescription") String albumDescription) throws IOException {
		Singer singer = singerService.getSingerById(singerId);
		AlbumDto album = new AlbumDto();
		album.setSinger(singer);
		album.setAlbumImgPath(
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602703915479&di=056884faa3f610f21eab832814848b4b&imgtype=0&src=http%3A%2F%2Fphoto.16pic.com%2F00%2F57%2F81%2F16pic_5781847_b.jpg");
		album.setAlbumName(albumName);
		album.setAlbumLanguage(albumLanguage);
		album.setReleaseCompany(releaseCompany);
		album.setReleaseDate(releaseDate);
		album.setAlbumDescription(albumDescription);
		return albumService.addAlbum(album);
	}

	@ResponseBody
	@RequestMapping("updateStatus")
	public int delAlbumByPrimaryKey(Integer albumId) {
		return albumService.modifyAlbumStatus(albumId);
	}

	@ResponseBody
	@RequestMapping("update")
	public int updateAlbum(@RequestParam("albumId") Integer albumId, @RequestParam("albumName") String albumName,
			@RequestParam("singerId") Integer singerId, @RequestParam("albumLanguage") String albumLanguage,
			@RequestParam("releaseCompany") String releaseCompany, @RequestParam("releaseDate") Date releaseDate,
			@RequestParam("albumDescription") String albumDescription, @RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		AlbumDto album = albumService.findSingleAlbumByPrimaryKey(albumId);
		String pictureUrl = fileSerivce.uploadPicture(multipartFile);
		Singer singer = singerService.getSingerById(singerId);
		album.setSinger(singer);
		album.setAlbumImgPath(pictureUrl);
		album.setAlbumName(albumName);
		album.setAlbumLanguage(albumLanguage);
		album.setReleaseCompany(releaseCompany);
		album.setReleaseDate(releaseDate);
		album.setAlbumDescription(albumDescription);
		return albumService.modifyAlbum(album);
	}

	@ResponseBody
	@RequestMapping("updateNoImg")
	public int updateAlbum(@RequestParam("albumId") Integer albumId, @RequestParam("albumName") String albumName,
			@RequestParam("singerId") Integer singerId, @RequestParam("albumLanguage") String albumLanguage,
			@RequestParam("releaseCompany") String releaseCompany, @RequestParam("releaseDate") Date releaseDate,
			@RequestParam("albumDescription") String albumDescription) throws IOException {
		AlbumDto album = albumService.findSingleAlbumByPrimaryKey(albumId);
		Singer singer = singerService.getSingerById(singerId);
		album.setSinger(singer);
		album.setAlbumName(albumName);
		album.setAlbumLanguage(albumLanguage);
		album.setReleaseCompany(releaseCompany);
		album.setReleaseDate(releaseDate);
		album.setAlbumDescription(albumDescription);
		return albumService.modifyAlbum(album);
	}

	@GetMapping("/content")
	public String showSingleAlbum(Integer id, Model model) {
		id = id == null ? 1 : id;
		AlbumDto album = albumService.findSingleAlbumByPrimaryKey(id);
		if (album == null) {
			id = 1;
			album = albumService.findSingleAlbumByPrimaryKey(id);
		}
		model.addAttribute("album", album);
		return "albumContent";
	}

	@ResponseBody
	@RequestMapping("showSingleAlbum")
	public AlbumDto showSingleAlbum(Integer albumId) {
		return albumService.findSingleAlbumByPrimaryKey(albumId);
	}

	@ResponseBody
	@RequestMapping("listSomeAlbum")
	public PageInfo<AlbumDto> listSomeAlbum(Integer offset, Integer limit, String albumName) {
		return albumService.findAlbumByPage(offset, limit, albumName);
	}

	@ResponseBody
	@RequestMapping("topAlbum")
	public List<AlbumDto> topAlbum() {
		return albumService.findTopAlbum();
	}
}
