package com.testapplication.wfcmainpage;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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


/**
 * @author Remco Hilbert & Fren de Haan
 *         Main activity voor de carrousel en de Info, Rent, Navigation & Facilities knoppen
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
	CustomPagerAdapter mCustomPagerAdapter;
	ViewPager mViewPager;
	private int[] imageId;
	private int arrayLength;
	private int dotsCount;
	private TextView[] dots;
	private LinearLayout dotsLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        setTitle(getString(R.string.main_title_text));

//        try {
//            android.support.v7.app.ActionBar bar = getSupportActionBar();
//            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8A076D")));
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }


		TypedArray mResources = getResources().obtainTypedArray(R.array.carrouselImages);
		arrayLength = mResources.length();

		imageId = new int[arrayLength];

		for(int i = 0; i < arrayLength; i++)
		{
			imageId[i] = mResources.getResourceId(i,0);
		}

		mResources.recycle();

		mCustomPagerAdapter = new CustomPagerAdapter(this);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCustomPagerAdapter);
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura (Light).ttf");

		setUiPageViewController();


		// knoppen declareren
		Button infoButton = (Button) findViewById(R.id.infoButton);
        infoButton.setTypeface(face);
		Button facilitiesButton = (Button) findViewById(R.id.facilitiesButton);
        facilitiesButton.setTypeface(face);
		Button navigationButton = (Button) findViewById(R.id.navigationButton);
        navigationButton.setTypeface(face);
		Button rentButton = (Button) findViewById(R.id.rentButton);
        rentButton.setTypeface(face);


		//onclick listener initialiseren
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
		dotsLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);
		dotsCount = mCustomPagerAdapter.getCount();
		dots = new TextView[dotsCount];

		for (int i = 0; i < dotsCount; i++) {
			dots[i] = new TextView(this);
			dots[i].setText(Html.fromHtml("&#8226;"));
			dots[i].setTextSize(40);
			dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			dotsLayout.addView(dots[i]);
		}

		dots[0].setTextColor(getResources().getColor(R.color.dot_selected));
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
			return imageId.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((LinearLayout) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

			ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
			imageView.setImageResource(imageId[position]);

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
			for (int i = 0; i < dotsCount; i++){
				dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
			}
			// only one selected
			dots[position].setTextColor(getResources().getColor(R.color.dot_selected));
		}
		}
	}


