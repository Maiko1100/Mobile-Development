package com.testapplication.wfcmainpage;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een custom adapter die alle items uit de sFacility array converteerd naar een list.
 *
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Facility> implements Filterable {

    Facility data[] = null;

    public CustomAdapter(Context context, Facility[] pData) {
        super(context, R.layout.custom_row,pData);
        data = pData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.custom_row, parent, false);


        TextView customRowText = (TextView)customView.findViewById(R.id.customRowText);
        ImageView customImage = (ImageView)customView.findViewById(R.id.customImageView);
        TextView mediumRowText = (TextView) customView.findViewById(R.id.mediumRowText);


        Facility facility = data[position];
        customRowText.setText(facility.title);
        customImage.setImageResource(facility.icon);
        mediumRowText.setText(facility.info);
        return customView;
    }

}

