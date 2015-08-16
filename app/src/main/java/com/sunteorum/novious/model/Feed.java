package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable {
	private long id;
	private String title;
	private String content;
	private String url;
	private String pubTime;
	private String preview;
	private int status;
	private int flag;

	public Feed() {

	}

	public Feed(Parcel source) {
		Feed article = new Feed();
		article.id = source.readLong();
		article.title = source.readString();
		article.content = source.readString();
		article.url = source.readString();
		article.pubTime = source.readString();
		article.preview = source.readString();

		article.status = source.readInt();
		article.flag = source.readInt();
	}

	public static final Creator<Feed> CREATOR = new Creator<Feed>() {

		@Override
		public Feed createFromParcel(Parcel parcel) {
			return new Feed(parcel);
		}

		@Override
		public Feed[] newArray(int i) {
			return new Feed[i];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel desc, int flags) {
		desc.writeLong(id);

		desc.writeString(title);
		desc.writeString(content);
		desc.writeString(url);
		desc.writeString(pubTime);
		desc.writeString(preview);

		desc.writeInt(status);
		desc.writeInt(flag);
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
