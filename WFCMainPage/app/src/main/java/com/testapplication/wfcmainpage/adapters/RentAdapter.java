package com.testapplication.wfcmainpage.adapters;


/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides an adapter for the rent database that converts the rent data to an arraylist and fills the listview with the
 * rent data.
 * The RentAdapter gets the data through pData from the RentActivity.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.ArrayList;

public class RentAdapter extends BaseAdapter {

	private ArrayList<Rentable> mData = null;
	private LayoutInflater mCustomInflater;
	private Context mContext;

	public RentAdapter(Context pContext, ArrayList<Rentable> pData) {

		this.mData = new ArrayList<>();
		mData.addAll(pData);
		mCustomInflater = LayoutInflater.from(pContext);
		mContext = pContext;
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


		ImageView imgImage = (ImageView) customView.findViewById(R.id.imgImage);
		TextView txtType = (TextView) customView.findViewById(R.id.txtType);
		TextView txtInfo = (TextView) customView.findViewById(R.id.txtInfo);

		Rentable rentable = mData.get(pPosition);
		int resId = mContext.getResources().getIdentifier(rentable.getmImage(), "mipmap", mContext.getPackageName());

		txtType.setText(rentable.getmType());
		txtInfo.setText("Extra: " + rentable.getmInfo());
		imgImage.setImageResource(resId);

		return customView;
	}


}
