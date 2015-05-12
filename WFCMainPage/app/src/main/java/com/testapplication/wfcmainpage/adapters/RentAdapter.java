package com.testapplication.wfcmainpage.adapters;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class wordt toegepast op alle items in de list om de rent array te converteren naar een list.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.FacilityRent;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.ArrayList;

public class RentAdapter extends BaseAdapter {

    private ArrayList<Rentable> mData = null;
    private LayoutInflater mCustomInflater;

    public RentAdapter(Context pContext, ArrayList<Rentable> pData) {

        this.mData = new ArrayList<>();
        mData.addAll(pData);
        mCustomInflater = LayoutInflater.from(pContext);

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int pPosition) {
        return mData.get(pPosition);
    }

    @Override
    public long getItemId(int pPosition) {
        return pPosition;
    }

    @Override
    public View getView(int pPosition, View convertView, ViewGroup parent) {
        View customView;
        customView = mCustomInflater.inflate(R.layout.custom_row_rent, null);



        TextView customRowText = (TextView)customView.findViewById(R.id.customRowText);
        ImageView customImage = (ImageView)customView.findViewById(R.id.customImageView);
        TextView mediumRowText = (TextView) customView.findViewById(R.id.mediumRowText);

        Rentable rentable = mData.get(pPosition);
        customRowText.setText(rentable.getmName());
        //customImage.setImageResource(rentable.getmImage());
        mediumRowText.setText(rentable.getmInfo());
        return customView;
    }
}
