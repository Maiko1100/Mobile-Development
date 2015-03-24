package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Standaard class voor faciliteiten. Array sFacilities uit de main wordt gevult met onderstaande attributen.
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
}
