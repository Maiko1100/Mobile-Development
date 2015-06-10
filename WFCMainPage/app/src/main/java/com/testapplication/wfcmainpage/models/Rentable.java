package com.testapplication.wfcmainpage.models;

import java.io.Serializable;

/**
 * Created by Maiko on 12-5-2015.
 */
public class Rentable implements Serializable{
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

	public Rentable(String pName, String pInfo, String pType, String pSize, String pTower, String pFloor, String pRoom, String pImage, String pSiteLink) {
		mName = pName;
		mInfo = pInfo;
		mType = pType;
		mSize = pSize;
		mTower = pTower;
		mFloor = pFloor;
		mRoom = pRoom;
		mImage = pImage;
		mSiteLink = pSiteLink;
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
