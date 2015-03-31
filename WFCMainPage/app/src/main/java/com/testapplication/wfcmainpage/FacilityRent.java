package com.testapplication.wfcmainpage;

/**
 * Created by Maiko on 17-3-2015.
 */
public class FacilityRent {

    public String title;
    public String info;
    public int icon;
    public FacilityRent(){
      super();
   }

    public FacilityRent(int pIcon, String pTitle, String pInfo){
        super();
        title = pTitle;
        info = pInfo;
        icon=pIcon;
    }
}
