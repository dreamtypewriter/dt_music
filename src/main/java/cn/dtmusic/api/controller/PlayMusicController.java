package cn.dtmusic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dtmusic.api.dto.SongDto;
import cn.dtmusic.api.entity.SongList;
import cn.dtmusic.api.service.*;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/11
 * @ time:      19:51
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Controller
@RequestMapping("/playMusic")
public class PlayMusicController {
    private final SongService songService;
    private final SongListService songListService;
    private final AlbumService albumService;
    private final SingerService singerService;
    private final PlayMusicService playMusicService;

    @Autowired
    public PlayMusicController(SongService songService, SongListService songListService, AlbumService albumService, SingerService singerService, PlayMusicService playMusicService) {
        this.songService = songService;
        this.songListService = songListService;
        this.albumService = albumService;
        this.singerService = singerService;
        this.playMusicService = playMusicService;
    }

    @RequestMapping("/newPlayer")
    public String newPlayer(Model model, Integer songId, Integer songListId, Integer albumId, Integer singerId) {
        if (songId != null) {
            SongDto songDto = songService.findSingleSongByPrimaryKey(songId);
            playMusicService.addSongPlayCount(songDto);
            model.addAttribute("newSong", songDto);
            return "player";
        }
        if (songListId != null) {
            SongList singleSongList = songListService.findSingleSongList(songListId);
            List<SongDto> songs = singleSongList.getSongs();
            playMusicService.addSongsPlayCount(songs);
            playMusicService.addSongListPlayCount(singleSongList);
            model.addAttribute("newSongList", songs);
            return "player";
        }
        if (albumId != null) {
            List<SongDto> songs = albumService.findSingleAlbumByPrimaryKey(albumId).getSongs();
            playMusicService.addSongsPlayCount(songs);
            model.addAttribute("newSongList", songs);
            return "player";
        }
        if (singerId != null) {
            List<SongDto> songs = singerService.getSingerById(singerId).getSongs();
            playMusicService.addSongsPlayCount(songs);
            model.addAttribute("newSongList", songs);
            return "player";
        }
        return "index";
    }

    @GetMapping("/getSongs")
    @ResponseBody
    public List<SongDto> getSongs(Integer songListId, Integer albumId, Integer singerId) {
        if (songListId != null) {
            SongList singleSongList = songListService.findSingleSongList(songListId);
            List<SongDto> songs = singleSongList.getSongs();
            playMusicService.addSongListPlayCount(singleSongList);
            playMusicService.addSongsPlayCount(songs);
            return songs;
        }
        if (albumId != null) {
            List<SongDto> songs = albumService.findSingleAlbumByPrimaryKey(albumId).getSongs();
            playMusicService.addSongsPlayCount(songs);
            return songs;
        }
        if (singerId != null) {
            List<SongDto> songs = singerService.getSingerById(singerId).getSongs();
            playMusicService.addSongsPlayCount(songs);
            return songs;
        }
        return null;
    }

    @GetMapping("/getSong")
    @ResponseBody
    public SongDto getSong(Integer songId) {
        SongDto songDto = songService.findSingleSongByPrimaryKey(songId);
        playMusicService.addSongPlayCount(songDto);
        return songDto;
    }
}
