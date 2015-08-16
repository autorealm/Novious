package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Downest implements Parcelable {
	public static final String TAG = Downest.class.getSimpleName();

	public static final Creator<Downest> CREATOR = new Creator<Downest>() {
		@Override
		public Downest createFromParcel(Parcel inputParcel) {
			return new Downest(inputParcel);
		}

		@Override
		public Downest[] newArray(int size) {
			return new Downest[size];
		}
	};

	private Long _id;

	private String Url;
	private String ParentUrl;

	private String Directory;

	private String Name;

	private int Flag;

	public Downest() {
	}

	private Downest(Parcel inputParcel) {
		_id = inputParcel.readLong();
		if (_id < 0) {
			_id = null;
		}

		Url = inputParcel.readString();
		ParentUrl = inputParcel.readString();

		Directory = inputParcel.readString();

		Name = inputParcel.readString();

		Flag = inputParcel.readInt();
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

		outputParcel.writeString(Url);
		outputParcel.writeString(ParentUrl);

		outputParcel.writeString(Directory);

		outputParcel.writeString(Name);

		outputParcel.writeInt(Flag);
	}

	public Long getId() {
		return _id;
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

	public String getDirectory() {
		return Directory;
	}

	public void setDirectory(String directory) {
		Directory = directory;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int flag) {
		Flag = flag;
	}

}
