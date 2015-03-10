package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Standaard class voor faciliteiten. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */

public class Facility {
    public int icon;
    public String title;
    public String info;
    public Facility(){
        super();
    }

    public Facility(int pIcon, String pTitle, String pInfo){
        super();
        icon = pIcon;
        title = pTitle;
        info = pInfo;
    }
}
