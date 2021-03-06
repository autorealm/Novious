package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recent implements Parcelable {
	public static final String TAG = Recent.class.getSimpleName();

	public static final Creator<Recent> CREATOR = new Creator<Recent>() {
		@Override
		public Recent createFromParcel(Parcel inputParcel) {
			return new Recent(inputParcel);
		}

		@Override
		public Recent[] newArray(int size) {
			return new Recent[size];
		}
	};

	private Long _id;

	private String Source;
	private String Url;
	private String ParentUrl;

	private String Name;
	private String ThumbnailUrl;

	private long Date;
	private int PageNumber;

	private boolean Offline;

	public Recent() {
	}

	private Recent(Parcel inputParcel) {
		_id = inputParcel.readLong();
		if (_id < 0) {
			_id = null;
		}

		Source = inputParcel.readString();
		Url = inputParcel.readString();
		ParentUrl = inputParcel.readString();

		Name = inputParcel.readString();
		ThumbnailUrl = inputParcel.readString();

		Date = inputParcel.readLong();
		PageNumber = inputParcel.readInt();

		Offline = inputParcel.readByte() != 0;
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
		outputParcel.writeString(ThumbnailUrl);

		outputParcel.writeLong(Date);
		outputParcel.writeInt(PageNumber);

		outputParcel.writeByte((byte) (Offline ? 1 : 0));
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

	public String getThumbnailUrl() {
		return ThumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		ThumbnailUrl = thumbnailUrl;
	}

	public long getDate() {
		return Date;
	}

	public void setDate(long date) {
		Date = date;
	}

	public int getPageNumber() {
		return PageNumber;
	}

	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}

	public boolean isOffline() {
		return Offline;
	}

	public void setOffline(boolean offline) {
		Offline = offline;
	}

}
