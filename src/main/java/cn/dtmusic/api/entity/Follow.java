package cn.dtmusic.api.entity;

import java.io.Serializable;

public class Follow implements Serializable {
	private static final long serialVersionUID = -8193991206826647505L;

	private Integer userId;

	private Integer followUserId;

	private Byte followStatus;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Integer followUserId) {
		this.followUserId = followUserId;
	}

	public Byte getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Byte followStatus) {
		this.followStatus = followStatus;
	}

	public Follow() {
	}

	public Follow(Integer userId, Integer followUserId, Byte followStatus) {
		this.userId = userId;
		this.followUserId = followUserId;
		this.followStatus = followStatus;
	}

	@Override
	public String toString() {
		return "Follow{" + "userId=" + userId + ", followUserId=" + followUserId + ", followStatus=" + followStatus + '}';
	}
}
