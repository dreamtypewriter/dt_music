package cn.dtmusic.api.entity;

import java.util.Date;

public class Album {

    private Integer albumId;

    private Byte resourceType;

    private String albumName;

    private String albumImgPath;

    private String albumLanguage;

    private Integer singerId;

    private Date releaseDate;

    private String releaseCompany;

    private String albumDescription;

    private Integer collectionCount;

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

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
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

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
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

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
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
}
