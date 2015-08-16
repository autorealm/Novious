package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Chapter implements Parcelable {
	public static final String TAG = Chapter.class.getSimpleName();

	public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
		@Override
		public Chapter createFromParcel(Parcel inputParcel) {
			return new Chapter(inputParcel);
		}

		@Override
		public Chapter[] newArray(int size) {
			return new Chapter[size];
		}
	};

	private Long _id;

	private String Source;
	private String Url;
	private String ParentUrl;

	private String Name;
	private String Description;
	private String Category;
	private String Tags;
	private boolean New;
	private long Date;

	private int Number;
	private int Comments;
	private int Collects;
	private int Forwards;
	private int Status;

	public Chapter() {
	}

	private Chapter(Parcel inputParcel) {
		_id = inputParcel.readLong();
		if (_id < 0) {
			_id = null;
		}

		Source = inputParcel.readString();
		Url = inputParcel.readString();
		ParentUrl = inputParcel.readString();

		Name = inputParcel.readString();
		Description = inputParcel.readString();
		Category = inputParcel.readString();
		Tags = inputParcel.readString();
		New = inputParcel.readByte() != 0;
		Date = inputParcel.readLong();

		Number = inputParcel.readInt();
		Comments = inputParcel.readInt();
		Collects = inputParcel.readInt();
		Forwards = inputParcel.readInt();
		Status = inputParcel.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel outputParcel, int flags) {
		if (_id != null) {
			outputParcel.writeLong(_id);
		} else {
			outputParcel.writeLong(-1);
		}

		outputParcel.writeString(Source);
		outputParcel.writeString(Url);
		outputParcel.writeString(ParentUrl);

		outputParcel.writeString(Name);
		outputParcel.writeString(Description);
		outputParcel.writeString(Category);
		outputParcel.writeString(Tags);
		outputParcel.writeByte((byte) (New ? 1 : 0));
		outputParcel.writeLong(Date);

		outputParcel.writeInt(Number);
		outputParcel.writeInt(Comments);
		outputParcel.writeInt(Collects);
		outputParcel.writeInt(Forwards);
		outputParcel.writeInt(Status);
	}

	public Long getId() {
		return _id;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getParentUrl() {
		return ParentUrl;
	}

	public void setParentUrl(String parentUrl) {
		ParentUrl = parentUrl;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public boolean isNew() {
		return New;
	}

	public void setNew(boolean isNew) {
		New = isNew;
	}

	public long getDate() {
		return Date;
	}

	public void setDate(long date) {
		Date = date;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public int getComments() {
		return Comments;
	}

	public void setComments(int comments) {
		Comments = comments;
	}

	public int getCollects() {
		return Collects;
	}

	public void setCollects(int collects) {
		Collects = collects;
	}

	public int getForwards() {
		return Forwards;
	}

	public void setForwards(int forwards) {
		Forwards = forwards;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
