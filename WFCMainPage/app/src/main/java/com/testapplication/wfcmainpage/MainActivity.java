package com.testapplication.wfcmainpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;

/**
 * @author Remco Hilbert & Fren de Haan
 *         Main activity voor de carrousel en de Info, Rent, Navigation & Facilities knoppen
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	private Timer mCarrouselTimer;
	private int mCurrentImage;
	private int mOtherImage;
	private ImageView mCarrouselImage;
	private ImageView mCarrouselImage2;
	CustomPagerAdapter mCustomPagerAdapter;
	ViewPager mViewPager;
	public int[] mResources = {
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCustomPagerAdapter = new CustomPagerAdapter(this);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCustomPagerAdapter);

		// knoppen declareren
		Button infoButton = (Button) findViewById(R.id.infoButton);
		Button facilitiesButton = (Button) findViewById(R.id.facilitiesButton);
		Button navigationButton = (Button) findViewById(R.id.navigationButton);
		Button rentButton = (Button) findViewById(R.id.rentButton);

		//onclick listener initialiseren
		infoButton.setOnClickListener(this);
		facilitiesButton.setOnClickListener(this);
		navigationButton.setOnClickListener(this);
		rentButton.setOnClickListener(this);
	}


	public void showInfo(View v) {
		Intent showInfo = new Intent(this, InfoActivity.class);
		startActivity(showInfo);
	}

	public void showMaps(View v) {
		Intent showMaps = new Intent(this, MapsActivity.class);
		startActivity(showMaps);
	}

	public void showFacilities(View v) {
		Intent showFacilities = new Intent(this, FacilitiesActivity.class);
		startActivity(showFacilities);
	}

	public void showRent(View v) {
		Intent showRentFacilities = new Intent(this, FacilityRentActivity.class);
		startActivity(showRentFacilities);
	}

	@Override
	public void onClick(View v) {
		{

			int id = v.getId();
			switch (id) {
				case R.id.infoButton:
					showInfo(v);
					break;

				case R.id.navigationButton:
					showMaps(v);
					break;

				case R.id.facilitiesButton:
					showFacilities(v);
					break;

				case R.id.rentButton:
					showRent(v);
					break;

			}
		}
	}

	class CustomPagerAdapter extends PagerAdapter {

		Context mContext;
		LayoutInflater mLayoutInflater;

		public CustomPagerAdapter(Context context) {
			mContext = context;
			mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return mResources.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((LinearLayout) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
			imageView.setImageResource(mResources[position]);

			container.addView(itemView);

			return itemView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
		}
	}
}