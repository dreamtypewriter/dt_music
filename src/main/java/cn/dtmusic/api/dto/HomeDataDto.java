package cn.dtmusic.api.dto;

import java.util.List;

/**
 * @ description:
 * @ date:      2020/10/10
 * @ time:      14:46
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public class HomeDataDto {
    private Integer songCount;
    private Integer songListCount;
    private Integer albumCount;
    private Integer userCount;
    private Integer shareCount;
    private Integer commentCount;
    private Integer replyCount;

    public HomeDataDto(List<Integer> list) {
        if (list == null || list.size() != 7) {
            return;
        }
        this.songCount = list.get(0);
        this.songListCount = list.get(1);
        this.albumCount = list.get(2);
        this.userCount = list.get(3);
        this.shareCount = list.get(4);
        this.commentCount = list.get(5);
        this.replyCount = list.get(6);
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public Integer getSongListCount() {
        return songListCount;
    }

    public void setSongListCount(Integer songListCount) {
        this.songListCount = songListCount;
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer albumCount) {
        this.albumCount = albumCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
