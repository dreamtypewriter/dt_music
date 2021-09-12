package cn.dtmusic.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.dtmusic.api.config.ResourceTypeConfig;

import java.util.Date;

/**
 * @ description:
 * @ date:      2020/10/4
 * @ time:      20:02
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public class ShareDto {
    private Integer shareId;

    private Integer userId;

    private String userNickname;

    private String userAvatar;

    private String shareText;

    private Byte typeId;

    private String resourceTypeName;

    private Integer resourceId;

    private String resourceUrl;

    private String resourceImgPath;

    private String resourceName;

    private String resourceDescription;

    private Integer likeCount;

    private Integer forwardCount;

    private Integer commentCount;

    @JsonFormat(pattern = "MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    public Byte getTypeId() {
        return typeId;
    }

    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceImgPath() {
        return resourceImgPath;
    }

    public void setResourceImgPath(String resourceImgPath) {
        this.resourceImgPath = resourceImgPath;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void createResourceUrl() {
        this.resourceUrl = ResourceTypeConfig.getResourceUrl(typeId, resourceId);
    }

    public void createResourceTypeName() {
        this.resourceTypeName = ResourceTypeConfig.getResourceTypeName(typeId);
    }
}
