package cn.dtmusic.api.entity;

import java.util.Date;

public class Like {
    private Integer likeId;

    private Integer userId;

    private Integer userLikedId;

    private Byte resourceType;

    private Integer resourceId;

    private Byte likeStatus;

    private Date createTime;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserLikedId() {
        return userLikedId;
    }

    public void setUserLikedId(Integer userLikedId) {
        this.userLikedId = userLikedId;
    }

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Byte getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Byte likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
