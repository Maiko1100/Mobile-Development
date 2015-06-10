package com.testapplication.wfcmainpage.adapters;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides an adapter for the rent database that converts the rent data to an arraylist and fills the listview with the
 * rent data.
 * The RentAdapter gets the data through pData from the RentActivity.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.activity.RentActivity;
import com.testapplication.wfcmainpage.activity.RentDetailsActivity;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.ArrayList;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.ViewHolder> {

    private ArrayList<Rentable> mData = null;
    protected Context mContext;

    public RentAdapter(ArrayList<Rentable> pData) {

        this.mData = new ArrayList<>();
        mData.addAll(pData);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int pPosition) {
        return pPosition;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.custom_card_rent, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Rentable rentable = mData.get(position);
        int resId = mContext.getResources().getIdentifier(rentable.getmImage(), "mipmap", mContext.getPackageName());

        holder.mRentable = rentable;
        holder.mPhoto.setImageResource(resId);
        holder.mInfo.setText(rentable.getmInfo());
        holder.mType.setText(rentable.getmType());

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private Rentable mRentable;
        private ImageView mPhoto;
        private TextView mType;
        private TextView mInfo;

        public ViewHolder(View v) {
            super(v);
            mPhoto = (ImageView)v.findViewById(R.id.imgImage);
            mType = (TextView) v.findViewById(R.id.txtType);
            mInfo = (TextView) v.findViewById(R.id.txtInfo);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(v.getContext(), RentDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Rentable", mRentable);
            myIntent.putExtra("Bundle", bundle);
            v.getContext().startActivity(myIntent);
        }
    }

}
