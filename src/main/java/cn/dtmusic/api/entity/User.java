package cn.dtmusic.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = -3321479737895257587L;

	private Integer userId;

	private String userNickname;

	private String userPhone;

	private String userPassword;

	private String userAvatar;

	private String userGender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date userBirthday;

	private String userIntroduce;

	private String userProvince;

	private String userCity;

	private Integer userLevel;

	private Integer followedNumber;

	private Integer fansNumber;

	private Integer messageNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;

	private Date modifyDate;

	private Byte userStatus;

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
		this.userNickname = userNickname == null ? null : userNickname.trim();
	}

	public Byte getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Byte userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserIntroduce() {
		return userIntroduce;
	}

	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar == null ? null : userAvatar.trim();
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender == null ? null : userGender.trim();
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince == null ? null : userProvince.trim();
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity == null ? null : userCity.trim();
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getFollowedNumber() {
		return followedNumber;
	}

	public void setFollowedNumber(Integer followedNumber) {
		this.followedNumber = followedNumber;
	}

	public Integer getFansNumber() {
		return fansNumber;
	}

	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}

	public Integer getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(Integer messageNumber) {
		this.messageNumber = messageNumber;
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

	public User() {
	}

	public User(Integer userId, String userNickname, String userGender, Date userBirthday, String userIntroduce, String userProvince,
			String userCity, String userPhone) {
		this.userId = userId;
		this.userNickname = userNickname;
		this.userGender = userGender;
		this.userBirthday = userBirthday;
		this.userIntroduce = userIntroduce;
		this.userProvince = userProvince;
		this.userCity = userCity;
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", userNickname='" + userNickname + '\'' + ", userPhone='" + userPhone + '\''
				+ ", userPassword='" + userPassword + '\'' + ", userAvatar='" + userAvatar + '\'' + ", userGender='" + userGender + '\''
				+ ", userBirthday=" + userBirthday + ", userIntroduce='" + userIntroduce + '\'' + ", userProvince='" + userProvince + '\''
				+ ", userCity='" + userCity + '\'' + ", userLevel=" + userLevel + ", followedNumber=" + followedNumber + ", fansNumber="
				+ fansNumber + ", messageNumber=" + messageNumber + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", userStatus=" + userStatus + '}';
	}
}
