package com.sunteorum.novious.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KYO on 2015/5/19.
 */
public class Recommend implements Parcelable {
	private int id;
	private String code;
	private String pages;
	private String num;

	private String userName; //推荐人名字
	private int userId; //推荐人ID
	private long time; //推荐时间
	private String msg; //推荐信息
	private String faceUrl; //推荐人头像地址

	public Recommend() {

	}

	public Recommend(Parcel source) {
		Recommend ce = new Recommend();
		ce.id = source.readInt();
		ce.code = source.readString();
		ce.pages = source.readString();
		ce.num = source.readString();

	}

	public static final Creator<Recommend> CREATOR = new Creator<Recommend>() {

		@Override
		public Recommend createFromParcel(Parcel parcel) {
			return new Recommend(parcel);
		}

		@Override
		public Recommend[] newArray(int i) {
			return new Recommend[i];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel desc, int flags) {
		desc.writeInt(id);
		desc.writeString(code);
		desc.writeString(pages);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
