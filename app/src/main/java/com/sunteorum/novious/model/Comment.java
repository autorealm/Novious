package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KYO on 2015/5/19.
 */
public class Comment implements Parcelable {
	private long id;
	private long targetId;
	private long parentId;
	private int userId;
	private String userName;
	private String content;
	private String pubTime;
	private int likes;
	private int status;
	private String advancedInfo; //JSONObject {"x","y","width","height","color","font"...}
	private String accessInfo; //JSONOBject {"ip","device","ver"...}

	public Comment() {

	}

	public Comment(Parcel source) {
		Comment ce = new Comment();
		ce.id = source.readLong();
		ce.targetId = source.readLong();
		ce.parentId = source.readLong();
		ce.userId = source.readInt();
		ce.userName = source.readString();
		ce.content = source.readString();
		ce.pubTime = source.readString();
		ce.likes = source.readInt();
		ce.status = source.readInt();
		ce.advancedInfo = source.readString();
		ce.accessInfo = source.readString();

	}

	public static final Creator<Comment> CREATOR = new Creator<Comment>() {

		@Override
		public Comment createFromParcel(Parcel parcel) {
			return new Comment(parcel);
		}

		@Override
		public Comment[] newArray(int i) {
			return new Comment[i];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel desc, int flags) {
		desc.writeLong(id);
		desc.writeLong(targetId);
		desc.writeLong(parentId);
		desc.writeInt(userId);
		desc.writeString(userName);
		desc.writeString(content);
		desc.writeString(pubTime);
		desc.writeInt(likes);
		desc.writeInt(status);
		desc.writeString(advancedInfo);
		desc.writeString(accessInfo);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String posTime) {
		this.pubTime = posTime;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likeCount) {
		this.likes = likeCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAdvancedInfo() {
		return advancedInfo;
	}

	public void setAdvancedInfo(String advancedInfo) {
		this.advancedInfo = advancedInfo;
	}

	public String getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(String accessInfo) {
		this.accessInfo = accessInfo;
	}
}
