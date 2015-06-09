package com.testapplication.wfcmainpage.models;

/**
 * Created by Maiko on 12-5-2015.
 */
public class Rentable {
	private int mId;
	private String mName;
	private String mInfo;
	private String mType;
	private String mSize;
	private String mTower;
	private String mFloor;
	private String mRoom;
	private String mImage;
	private String mSiteLink;

	public Rentable() {
	}

	public Rentable(String mName, String mInfo, String mType, String mSize, String mTower, String mFloor, String mRoom, String mImage, String mSiteLink) {
		this.mName = mName;
		this.mInfo = mInfo;
		this.mType = mType;
		this.mSize = mSize;
		this.mTower = mTower;
		this.mFloor = mFloor;
		this.mRoom = mRoom;
		this.mImage = mImage;
		this.mSiteLink = mSiteLink;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmInfo() {
		return mInfo;
	}

	public void setmInfo(String mInfo) {
		this.mInfo = mInfo;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getmSize() {
		return mSize;
	}

	public void setmSize(String mSize) {
		this.mSize = mSize;
	}

	public String getmTower() {
		return mTower;
	}

	public void setmTower(String mTower) {
		this.mTower = mTower;
	}

	public String getmFloor() {
		return mFloor;
	}

	public void setmFloor(String mFloor) {
		this.mFloor = mFloor;
	}

	public String getmRoom() {
		return mRoom;
	}

	public void setmRoom(String mRoom) {
		this.mRoom = mRoom;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(String mImage) {
		this.mImage = mImage;
	}

	public String getmSiteLink() {
		return mSiteLink;
	}

	public void setmSiteLink(String mSiteLink) {
		this.mSiteLink = mSiteLink;
	}

	@Override
	public String toString() {
		return "Rentable{" +
				"mName='" + mName + '\'' +
				", mId=" + mId +
				'}';
	}
}
