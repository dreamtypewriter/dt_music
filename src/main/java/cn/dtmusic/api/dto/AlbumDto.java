package cn.dtmusic.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.dtmusic.api.entity.Singer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AlbumDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5808333589385872013L;

	private Integer albumId;

	private String albumName;

	private String albumImgPath;

	private String albumLanguage;

	private Integer collectionCount;

	private Singer singer;

	private SongDto song;

	private List<SongDto> songs;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date releaseDate;

	private String releaseCompany;

	private String albumDescription;

	private Integer commentCount;

	private Date createDate;

	private Date modifyDate;

	private Byte albumStatus;

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumImgPath() {
		return albumImgPath;
	}

	public void setAlbumImgPath(String albumImgPath) {
		this.albumImgPath = albumImgPath;
	}

	public String getAlbumLanguage() {
		return albumLanguage;
	}

	public void setAlbumLanguage(String albumLanguage) {
		this.albumLanguage = albumLanguage;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public SongDto getSong() {
		return song;
	}

	public void setSong(SongDto song) {
		this.song = song;
	}

	public List<SongDto> getSongs() {
		return songs;
	}

	public void setSongs(List<SongDto> songs) {
		this.songs = songs;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
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

	public Byte getAlbumStatus() {
		return albumStatus;
	}

	public void setAlbumStatus(Byte albumStatus) {
		this.albumStatus = albumStatus;
	}

	public AlbumDto() {
	}

	public AlbumDto(Integer albumId, String albumName, String albumImgPath, String albumLanguage, Integer collectionCount, Singer singer,
			Date releaseDate, String releaseCompany, String albumDescription, Integer commentCount, Date createDate, Date modifyDate,
			Byte albumStatus) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.albumImgPath = albumImgPath;
		this.albumLanguage = albumLanguage;
		this.collectionCount = collectionCount;
		this.singer = singer;
		this.releaseDate = releaseDate;
		this.releaseCompany = releaseCompany;
		this.albumDescription = albumDescription;
		this.commentCount = commentCount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.albumStatus = albumStatus;
	}

	@Override
	public String toString() {
		return "AlbumDto{" + "albumId=" + albumId + ", albumName='" + albumName + '\'' + ", albumImgPath='" + albumImgPath + '\''
				+ ", albumLanguage='" + albumLanguage + '\'' + ", collectionCount=" + collectionCount + ", singer=" + singer
				+ ", releaseDate=" + releaseDate + ", releaseCompany='" + releaseCompany + '\'' + ", albumDescription='" + albumDescription
				+ '\'' + ", commentCount=" + commentCount + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", albumStatus="
				+ albumStatus + '}';
	}
}
