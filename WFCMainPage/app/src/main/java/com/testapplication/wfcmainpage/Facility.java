package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een standaard class voor faciliteiten. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */
public class Facility {
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
	 * @param pTitle holds the title of a facility in the listview
	 * @param pInfo holds the info a facility in the listview
	 */
	public Facility(String pTitle, String pInfo) {
		super();
		title = pTitle;
		info = pInfo;
	}

    public void setTitle(String pTitle){
        title = pTitle;
    }

    public String getTitle(){
        return title;
    }

    public void setInfo(String pInfo){
        info = pInfo;
    }

    public String getInfo(){
        return info;
    }


}
