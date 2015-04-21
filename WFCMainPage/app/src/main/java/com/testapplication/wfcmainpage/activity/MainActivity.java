package com.testapplication.wfcmainpage.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.testapplication.wfcmainpage.R;


/**
 * @author Remco Hilbert & Fren de Haan
 *         Main activity voor de carrousel en de Info, Rent, Navigation & Facilities knoppen
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	private CustomPagerAdapter mCustomPagerAdapter;
	private int[] mImageId;
	private int mDotsCount;
	private TextView[] mDots;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle(getString(R.string.main_title_text));

		//Images in Array laden
		TypedArray mResources = getResources().obtainTypedArray(R.array.carrouselImages);
		int mCarrouselLength = mResources.length();
		mImageId = new int[mCarrouselLength];

		for (int i = 0; i < mCarrouselLength; i++) {
			mImageId[i] = mResources.getResourceId(i, 0);
		}
		mResources.recycle();

		mCustomPagerAdapter = new CustomPagerAdapter(this);
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCustomPagerAdapter);
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura (Light).ttf");

		setUiPageViewController();


		// Declare buttons and add fonts
		Button infoButton = (Button) findViewById(R.id.infoButton);
		infoButton.setTypeface(face);
		Button facilitiesButton = (Button) findViewById(R.id.facilitiesButton);
		facilitiesButton.setTypeface(face);
		Button navigationButton = (Button) findViewById(R.id.navigationButton);
		navigationButton.setTypeface(face);
		Button rentButton = (Button) findViewById(R.id.rentButton);
		rentButton.setTypeface(face);


		//init onclick listener on buttons
		infoButton.setOnClickListener(this);
		facilitiesButton.setOnClickListener(this);
		navigationButton.setOnClickListener(this);
		rentButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void setUiPageViewController() {
		LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);
		mDotsCount = mCustomPagerAdapter.getCount();
		mDots = new TextView[mDotsCount];

		for (int i = 0; i < mDotsCount; i++) {
			mDots[i] = new TextView(this);
			mDots[i].setText(Html.fromHtml("&#8226;"));
			mDots[i].setTextSize(40);
			mDots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			dotsLayout.addView(mDots[i]);
		}

		mDots[0].setTextColor(getResources().getColor(R.color.dot_selected));
	}

	public void showInfo(View v) {
		Intent showInfo = new Intent(this, InfoActivity.class);
		startActivity(showInfo);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	public void showMaps(View v) {
		Intent showMaps = new Intent(this, NavigationSelectActivity.class);
		startActivity(showMaps);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	public void showFacilities(View v) {
		Intent showFacilities = new Intent(this, FacilitiesActivity.class);
		startActivity(showFacilities);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	public void showRent(View v) {
		Intent showRentFacilities = new Intent(this, FacilityRentActivity.class);
		startActivity(showRentFacilities);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
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

	class CustomPagerAdapter extends PagerAdapter {

		Context mContext;
		LayoutInflater mLayoutInflater;


		public CustomPagerAdapter(Context context) {
			mContext = context;
			mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}


		@Override
		public int getCount() {
			return mImageId.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == (object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
			imageView.setImageResource(mImageId[position]);

			container.addView(itemView);

			return itemView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
		}
	}

	public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
		@SuppressWarnings("static-access")
		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
		                           int positionOffsetPixel) {
		}

		@Override
		public void onPageSelected(int position) {
			// making everything as un selected
			for (int i = 0; i < mDotsCount; i++) {
				mDots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			}
			// only one selected
			mDots[position].setTextColor(getResources().getColor(R.color.dot_selected));
		}
	}
}


