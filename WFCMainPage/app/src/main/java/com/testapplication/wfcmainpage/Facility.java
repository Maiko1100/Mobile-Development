package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een standaard class voor faciliteiten. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */
public class Facility {
	public int icon;
	public String title;
	public String info;

	/**
	 *
	 */
	public Facility() {
		super();
	}

	/**
	 *
	 * @param pIcon holds the image of a facility in the listview
	 * @param pTitle holds the title of a facility in the listview
	 * @param pInfo holds the info a facility in the listview
	 */
	public Facility(int pIcon, String pTitle, String pInfo) {
		super();
		icon = pIcon;
		title = pTitle;
		info = pInfo;
	}

	public int getIcon() {
		return 0;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
