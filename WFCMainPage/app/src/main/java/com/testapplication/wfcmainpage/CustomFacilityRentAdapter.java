package com.testapplication.wfcmainpage;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class wordt toegepast op alle items in de list om de sFacility array te converteren naar een list.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomFacilityRentAdapter extends ArrayAdapter<FacilityRent> {

    FacilityRent data[] = null;

    public CustomFacilityRentAdapter(Context context, FacilityRent[] pData) {
        super(context, R.layout.activity_custom_facility_rent_adapter,pData);
        data = pData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.activity_custom_facility_rent_adapter, parent, false);


        TextView customRowText = (TextView)customView.findViewById(R.id.customRowText);
        ImageView customImage = (ImageView)customView.findViewById(R.id.customImageView);
        TextView mediumRowText = (TextView) customView.findViewById(R.id.mediumRowText);


        FacilityRent facilityRent = data[position];
        customRowText.setText(facilityRent.title);
        customImage.setImageResource(facilityRent.icon);
        mediumRowText.setText(facilityRent.info);
        return customView;
    }
}
