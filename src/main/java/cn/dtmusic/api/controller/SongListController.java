package cn.dtmusic.api.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.FileService;
import cn.dtmusic.api.service.SongListService;

import java.io.IOException;
import java.util.List;

/**
 * @ description: @ date: 2020/9/26 @ time: 21:30 @ author: Zhang wei @ since: 1.0.0
 */
@Controller
@RequestMapping("songList")
public class SongListController {

	private final SongListService songListService;
	private final FileService fileService;

	/**
	 * Constructor
	 * 
	 * @param songListService
	 * @param fileService
	 */
	@Autowired
	public SongListController(SongListService songListService, FileService fileService) {
		this.songListService = songListService;
		this.fileService = fileService;
	}

	@RequestMapping("/showSongLists")
	@ResponseBody
	public List<SongList> showSongList() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<SongList> lists = songListService.findSongListsByUser(user.getUserId());
		return lists;
	}

	@RequestMapping("/showCollectedSongLists")
	@ResponseBody
	public List<SongList> showCollectedSongLists() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<SongList> lists = songListService.findCollectedSongListsByUser(user.getUserId());
		return lists;
	}

	@GetMapping("/content")
	public String showSingleSongList(Integer id, Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		id = id == null ? 1 : id;
		SongList songList = songListService.findSingleSongList(id);
		if (songList == null) {
			id = 1;
			songList = songListService.findSingleSongListById(id, user.getUserId());
		}
		if (user != null) {
			List<SongList> songlists = songListService.findSongListsByUser(user.getUserId());
			model.addAttribute("songLists", songlists);
		}
		model.addAttribute("songList", songList);
		return "playListContent";
	}

	@RequestMapping("/showSingleSongList")
	@ResponseBody
	public SongList showSingleSongListByUser(@RequestParam(value = "songListId", defaultValue = "1", required = false) Integer songListId) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		SongList songList = songListService.findSingleSongListById(songListId, user.getUserId());
		return songList;
	}

	@RequestMapping("/showCollectedSingleSonglist")
	@ResponseBody
	public SongList showCollectedSingleSonglist(
			@RequestParam(value = "songListId", defaultValue = "1", required = false) Integer songListId) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		SongList songList = songListService.findCollectedSingleSongListById(songListId, user.getUserId());
		return songList;
	}

	@RequestMapping("/addSongList")
	public String addSongList(@RequestParam("songListName") String songListName) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		SongList songList = new SongList();
		songList.setUserId(user.getUserId());
		songList.setUserNickname(user.getUserNickname());
		songList.setSongListName(songListName);
		songListService.addSongList(songList);
		return "redirect:/myMusic/mySongList";
	}

	@RequestMapping("/modifySongList")
	public String modifySongList(Integer songListId, String songListName, String categoriesId, String songListDescription,
			@RequestParam("songListImg") MultipartFile songListImg) throws IOException {
		SongList songList = new SongList();
		songList.setSongListId(songListId);
		songList.setSongListName(songListName);
		songList.setSongListDescription(songListDescription);
		songList.setSonglistImg(fileService.uploadPicture(songListImg));
		songListService.modifySongList(songList, categoriesId);
		return "redirect:/myMusic/mySongList";
	}

	@RequestMapping("/delSingleSongList")
	@ResponseBody
	public Integer delSingleSongList(@RequestParam("songListId") Integer Id) {
		return songListService.delSingleSongList(Id);
	}

	@RequestMapping("/delCollectedSingleSongList")
	@ResponseBody
	public Integer delCollectedSingleSongList(@RequestParam("songListId") Integer songListId) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return songListService.delCollectedSingleSongList(user.getUserId(), songListId);
	}

	@RequestMapping("/delSongFromSongList")
	@ResponseBody
	public Integer delSongFromSongList(@RequestParam("songId") Integer songId, @RequestParam("songListId") Integer songListId) {
		return songListService.delSongFromSongList(songId, songListId);
	}

	@RequestMapping("/findSomeSongList")
	@ResponseBody
	public List<SongList> findSomeSongList() {
		return songListService.getRecommendedSongList(12);
	}

	@RequestMapping("/findTopSongList")
	@ResponseBody
	public List<SongList> findTopSongList() {
		return songListService.getRecommendedSongList(6);
	}

}
