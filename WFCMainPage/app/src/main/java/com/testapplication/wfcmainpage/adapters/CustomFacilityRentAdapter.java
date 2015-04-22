package com.testapplication.wfcmainpage.adapters;


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

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.FacilityRent;

public class CustomFacilityRentAdapter extends ArrayAdapter<FacilityRent> {

    private FacilityRent mData[] = null;

    public CustomFacilityRentAdapter(Context context, FacilityRent[] pData) {
        super(context, R.layout.custom_row_rent,pData);
	    mData = pData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.custom_row_rent, parent, false);

        TextView customRowText = (TextView)customView.findViewById(R.id.customRowText);
        ImageView customImage = (ImageView)customView.findViewById(R.id.customImageView);
        TextView mediumRowText = (TextView) customView.findViewById(R.id.mediumRowText);

        FacilityRent facilityRent = mData[position];
        customRowText.setText(facilityRent.title);
        customImage.setImageResource(facilityRent.icon);
        mediumRowText.setText(facilityRent.info);
        return customView;
    }
}
