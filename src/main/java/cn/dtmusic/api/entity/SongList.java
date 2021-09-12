package cn.dtmusic.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.dtmusic.api.dto.SongDto;

import java.util.Date;
import java.util.List;

public class SongList {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.song_list_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private Integer songListStatus;

    public Integer getSongListStatus() {
        return songListStatus;
    }

    public void setSongListStatus(Integer songListStatus) {
        this.songListStatus = songListStatus;
    }

    private Integer commentCount;

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    private String songlistImg;

    public String getSonglistImg() {
        return songlistImg;
    }

    public void setSonglistImg(String songlistImg) {
        this.songlistImg = songlistImg;
    }

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private List<SongDto> songs;

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    private  Song song;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    private Integer songCount;

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    private Integer songListId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.song_list_name
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private String songListName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.user_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.user_avatar
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private String userAvatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.user_nickname
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private String userNickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.play_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private Integer playCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.collection_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private Integer collectionCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.song_list_description
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    private String songListDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.create_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_song_list.modify_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.song_list_id
     *
     * @return the value of t_song_list.song_list_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Integer getSongListId() {
        return songListId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.song_list_id
     *
     * @param songListId the value for t_song_list.song_list_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.song_list_name
     *
     * @return the value of t_song_list.song_list_name
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public String getSongListName() {
        return songListName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.song_list_name
     *
     * @param songListName the value for t_song_list.song_list_name
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setSongListName(String songListName) {
        this.songListName = songListName == null ? null : songListName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.user_id
     *
     * @return the value of t_song_list.user_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.user_id
     *
     * @param userId the value for t_song_list.user_id
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.user_avatar
     *
     * @return the value of t_song_list.user_avatar
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.user_avatar
     *
     * @param userAvatar the value for t_song_list.user_avatar
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.user_nickname
     *
     * @return the value of t_song_list.user_nickname
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.user_nickname
     *
     * @param userNickname the value for t_song_list.user_nickname
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.play_count
     *
     * @return the value of t_song_list.play_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Integer getPlayCount() {
        return playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.play_count
     *
     * @param playCount the value for t_song_list.play_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.collection_count
     *
     * @return the value of t_song_list.collection_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Integer getCollectionCount() {
        return collectionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.collection_count
     *
     * @param collectionCount the value for t_song_list.collection_count
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.song_list_description
     *
     * @return the value of t_song_list.song_list_description
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public String getSongListDescription() {
        return songListDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.song_list_description
     *
     * @param songListDescription the value for t_song_list.song_list_description
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setSongListDescription(String songListDescription) {
        this.songListDescription = songListDescription == null ? null : songListDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.create_date
     *
     * @return the value of t_song_list.create_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.create_date
     *
     * @param createDate the value for t_song_list.create_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_song_list.modify_date
     *
     * @return the value of t_song_list.modify_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_song_list.modify_date
     *
     * @param modifyDate the value for t_song_list.modify_date
     *
     * @mbg.generated Tue Oct 06 10:55:52 CST 2020
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}