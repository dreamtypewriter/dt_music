package cn.dtmusic.api.dto;

import java.io.Serializable;
import java.util.Date;

import cn.dtmusic.api.entity.Singer;

public class SongDto implements Serializable {

    private Integer songId;

    private String songImgPath;

    private String songPath;

    private String songName;

    private String songLength;

    private Singer singer;

    private AlbumDto albumDto;

    private String songDescription;

    private String songLyrics;

    private Integer commentCount;

    private Integer playCount;

    private Integer collectionCount;

    private Integer downloadCount;

    private Date createDate;

    private Date modifyDate;

    private Integer songStatus;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongImgPath() {
        return songImgPath;
    }

    public void setSongImgPath(String songImgPath) {
        this.songImgPath = songImgPath;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public AlbumDto getAlbumDto() {
        return albumDto;
    }

    public void setAlbumDto(AlbumDto albumDto) {
        this.albumDto = albumDto;
    }

    public String getSongDescription() {
        return songDescription;
    }

    public void setSongDescription(String songDescription) {
        this.songDescription = songDescription;
    }

    public String getSongLyrics() {
        return songLyrics;
    }

    public void setSongLyrics(String songLyrics) {
        this.songLyrics = songLyrics;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(Integer songStatus) {
        this.songStatus = songStatus;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
