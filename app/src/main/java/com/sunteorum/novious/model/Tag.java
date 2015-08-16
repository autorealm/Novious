package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tag implements Parcelable {
	private int id;
	private int alias_id;
	private int group_id;
	private String name; //union
	private String urls; //json
	private int count;
	private int alt; //lang
	private int type;
	private int status; //-1:disable, 0:normal, 1:locked, 2:indexed

	public Tag() {

	}

	public Tag(Parcel source) {
		Tag tag = new Tag();
		tag.id = source.readInt();
		tag.alias_id = source.readInt();
		tag.group_id = source.readInt();
		tag.name = source.readString();
		tag.urls = source.readString();

		tag.count = source.readInt();
		tag.alt = source.readInt();
		tag.type = source.readInt();
		tag.status = source.readInt();
	}

	public static final Creator<Tag> CREATOR = new Creator<Tag>() {

		@Override
		public Tag createFromParcel(Parcel parcel) {
			return new Tag(parcel);
		}

		@Override
		public Tag[] newArray(int i) {
			return new Tag[i];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel desc, int flags) {
		desc.writeLong(id);
		desc.writeInt(alias_id);
		desc.writeInt(group_id);

		desc.writeString(name);
		desc.writeString(urls);

		desc.writeInt(count);
		desc.writeInt(alt);
		desc.writeInt(type);
		desc.writeInt(status);
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAliasId() {
		return alias_id;
	}

	public void setAliasId(int alias_id) {
		this.alias_id = alias_id;
	}

	public int getGroupId() {
		return group_id;
	}

	public void setGroupId(int group_id) {
		this.group_id = group_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
