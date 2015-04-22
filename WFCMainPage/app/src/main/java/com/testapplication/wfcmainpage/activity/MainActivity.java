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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
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
	private static Button mInfoButton;
	private static Button mFacilitiesButton;
	private static Button mNavigationButton;
	private static Button mRentButton;



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

		//ViewPager and the adapter for it
		mCustomPagerAdapter = new CustomPagerAdapter(this);
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCustomPagerAdapter);
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());

		//font for buttons
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura (Light).ttf");

		//Method creating dots below Carroussel
		setUiPageViewController();


		// Declare buttons and add fonts
	 	mInfoButton = (Button) findViewById(R.id.infoButton);
		mInfoButton.setTypeface(face);
		mFacilitiesButton = (Button) findViewById(R.id.facilitiesButton);
		mFacilitiesButton.setTypeface(face);
		mNavigationButton = (Button) findViewById(R.id.navigationButton);
		mNavigationButton.setTypeface(face);
		mRentButton = (Button) findViewById(R.id.rentButton);
		mRentButton.setTypeface(face);

		//init onclick listener on buttons
		mInfoButton.setOnClickListener(this);
		mFacilitiesButton.setOnClickListener(this);
		mNavigationButton.setOnClickListener(this);
		mRentButton.setOnClickListener(this);
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

	/*
	Method for creating dots in the right amount below the viewpager
	 */
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
	/*
	Method for reversing the animation
	This method is called in the onBackPress of the 4 activities the buttons lead to.
	 */

	public static void animateReverseButtons() {

		// Setting the buttons on the mainpage clickable
		mInfoButton.setClickable(true);
		mNavigationButton.setClickable(true);
		mRentButton.setClickable(true);
		mFacilitiesButton.setClickable(true);

		// the animation
		AnimatorSet animations = new AnimatorSet();
		animations.playTogether(
				ObjectAnimator.ofFloat(mInfoButton, "translationX", -250, 0),
				ObjectAnimator.ofFloat(mInfoButton, "translationY", 300, 0),
				ObjectAnimator.ofFloat(mNavigationButton, "translationX", 250, 0),
				ObjectAnimator.ofFloat(mNavigationButton, "translationY", 300, 0),
				ObjectAnimator.ofFloat(mRentButton, "translationX", -250, 0),
				ObjectAnimator.ofFloat(mRentButton, "translationY", -300, 0),
				ObjectAnimator.ofFloat(mFacilitiesButton, "translationX", 250, 0),
				ObjectAnimator.ofFloat(mFacilitiesButton, "translationY", -300, 0)
		);
		animations.setInterpolator(new AccelerateInterpolator(1.8f));
		animations.setStartDelay(400);
		animations.setDuration(400).start();
	}

	/**
	 *
	 * @param startActivity the intent of which activity to start
	 */
	public void animateButtons(final Intent startActivity) {
		AnimatorSet animations = new AnimatorSet();
		animations.playTogether(
				ObjectAnimator.ofFloat(mInfoButton, "translationX", 0, -250),
				ObjectAnimator.ofFloat(mInfoButton, "translationY", 0, 300),
				ObjectAnimator.ofFloat(mNavigationButton, "translationX", 0, 250),
				ObjectAnimator.ofFloat(mNavigationButton, "translationY", 0, 300),
				ObjectAnimator.ofFloat(mRentButton, "translationX", 0, -250),
				ObjectAnimator.ofFloat(mRentButton, "translationY", 0, -300),
				ObjectAnimator.ofFloat(mFacilitiesButton, "translationX", 0, 250),
				ObjectAnimator.ofFloat(mFacilitiesButton, "translationY", 0, -300)
		);

		animations.setInterpolator(new AccelerateInterpolator(1.8f));
		animations.setDuration(400).start();
		// If the animation is running you can't click the buttons
		if (animations.isRunning()) {
			mInfoButton.setClickable(false);
			mNavigationButton.setClickable(false);
			mRentButton.setClickable(false);
			mFacilitiesButton.setClickable(false);
		}

		{
			// a listener on the animationset, If the animation ends it wil start the next activity
			animations.addListener(new Animator.AnimatorListener() {
				                @Override
				                public void onAnimationStart(Animator animation) {
				                }

				                @Override
				                public void onAnimationRepeat(Animator animation) {
				                }

				                @Override
				                public void onAnimationEnd(Animator animation) {
					                startActivity(startActivity);
					                overridePendingTransition(R.anim.zoom_enter, R.anim.hold_screen);
				                }

				                @Override
				                public void onAnimationCancel(Animator animation) {

				                }
			                }
			);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.infoButton:
				animateButtons(new Intent(this, InfoActivity.class));
				break;

			case R.id.navigationButton:
				animateButtons(new Intent(this, NavigationSelectActivity.class));
				break;

			case R.id.facilitiesButton:
				animateButtons(new Intent(this, FacilitiesActivity.class));
				break;

			case R.id.rentButton:
				animateButtons(new Intent(this, FacilityRentActivity.class));
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


