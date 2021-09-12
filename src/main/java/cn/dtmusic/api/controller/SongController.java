package cn.dtmusic.api.controller;

import com.github.pagehelper.PageInfo;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.service.AlbumService;
import cn.dtmusic.api.service.FileService;
import cn.dtmusic.api.service.SingerService;
import cn.dtmusic.api.service.SongService;
import cn.dtmusic.api.utils.MusicUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 猪九戒
 * @create 2020-10-06
 * @since 1.0.0
 */
@Controller
@RequestMapping("song")
public class SongController {

	private final SongService songService;
	private final FileService fileService;
	private final SingerService singerService;
	private final AlbumService albumService;

	@Autowired
	public SongController(SongService songService, SingerService singerService, AlbumService albumService, FileService qiniuyunUtil) {
		this.songService = songService;
		this.fileService = qiniuyunUtil;
		this.singerService = singerService;
		this.albumService = albumService;
	}

	@ResponseBody
	@RequestMapping("add")
	public int addSong(@RequestParam("songName") String songName, @RequestParam("singerId") Integer singerId,
			@RequestParam("albumId") Integer albumId, @RequestParam("songDescription") String songDescription,
			@RequestParam("songLyrics") String songLyrics, @RequestParam("file") MultipartFile multipartFile,
			@RequestParam("music") MultipartFile music) throws Exception {
		SongDto song = new SongDto();

		String pictureUrl = fileService.uploadPicture(multipartFile);
		song.setSongImgPath(pictureUrl);

		String musicUrl = fileService.uploadMusic(music);
		song.setSongPath(musicUrl);

		int musicLength = MusicUtil.getMusicLength(musicUrl);
		String songLength = (musicLength / 60 + "分") + (musicLength % 60 + "秒");
		song.setSongLength(songLength);

		song.setSongName(songName);
		song.setSinger(singerService.getSingerById(singerId));
		song.setAlbumDto(albumService.findSingleAlbumByPrimaryKey(albumId));
		song.setSongDescription(songDescription);
		song.setSongLyrics(songLyrics);
		return songService.addSong(song);
	}

	@ResponseBody
	@RequestMapping("updateStatus")
	public int delSongByPrimaryKey(Integer songId) {
		return songService.modifySongStatus(songId);
	}

	@ResponseBody
	@RequestMapping("update")
	public int updateSong(@RequestParam("songId") Integer songId, @RequestParam("songName") String songName,
			@RequestParam("singerId") Integer singerId, @RequestParam("albumId") Integer albumId,
			@RequestParam("songDescription") String songDescription, @RequestParam("songLyrics") String songLyrics,
			@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String pictureUrl = fileService.uploadPicture(multipartFile);
		SongDto song = songService.findSingleSongByPrimaryKey(songId);
		song.setSongImgPath(pictureUrl);
		song.setSongName(songName);
		song.setSinger(singerService.getSingerById(singerId));
		song.setAlbumDto(albumService.findSingleAlbumByPrimaryKey(albumId));
		song.setSongDescription(songDescription);
		song.setSongLyrics(songLyrics);
		return songService.modifySong(song);
	}

	@ResponseBody
	@RequestMapping("updateNoImg")
	public int updateSong(@RequestParam("songId") Integer songId, @RequestParam("songName") String songName,
			@RequestParam("singerId") Integer singerId, @RequestParam("albumId") Integer albumId,
			@RequestParam("songDescription") String songDescription, @RequestParam("songLyrics") String songLyrics) throws IOException {
		SongDto song = songService.findSingleSongByPrimaryKey(songId);
		song.setSongName(songName);
		song.setSinger(singerService.getSingerById(singerId));
		song.setAlbumDto(albumService.findSingleAlbumByPrimaryKey(albumId));
		song.setSongDescription(songDescription);
		song.setSongLyrics(songLyrics);
		return songService.modifySong(song);
	}

	@ResponseBody
	@RequestMapping("modify")
	public int modifySong(SongDto song) {
		return songService.modifySong(song);
	}

	@ResponseBody
	@RequestMapping("showSingleSong")
	public SongDto showSingleSong(Integer songId) {
		return songService.findSingleSongByPrimaryKey(songId);
	}

	@ResponseBody
	@RequestMapping("listSomeSong")
	public PageInfo<SongDto> listSomeSong(Integer offset, Integer limit, String songName, String singerName) {
		return songService.findSongByPage(offset, limit, songName, singerName);
	}

	@ResponseBody
	@RequestMapping("listAllSong")
	public List<SongDto> listAllSong() {
		return songService.findAllSong();
	}

	@ResponseBody
	@RequestMapping("listTopSong")
	public List<SongDto> listTopSong() {
		return songService.findTopSong();
	}

	@GetMapping("/content")
	public String jumpToSongContent(Integer id, Model model) {
		// 确保页面存在
		id = id == null ? 1 : id;
		SongDto songById = songService.findSingleSongByPrimaryKey(id);
		if (songById == null) {
			id = 1;
			songById = songService.findSingleSongByPrimaryKey(id);
		}
		//            ObjectMapper objectMapper = new ObjectMapper();
		//            String songString = objectMapper.writeValueAsString(songById);
		model.addAttribute("song", songById);
		return "songContent";
	}

}
