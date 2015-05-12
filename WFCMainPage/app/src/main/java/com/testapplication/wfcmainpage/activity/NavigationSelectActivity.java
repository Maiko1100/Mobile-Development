package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.testapplication.wfcmainpage.R;

public class NavigationSelectActivity extends ActionBarActivity implements View.OnClickListener {

	private Button mBtnOutdoor, mBtnIndoor, mBtnIndoorMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_select);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura (Light).ttf");

		mBtnIndoor = (Button) findViewById(R.id.btnNavigationSelectIndoor);
		mBtnIndoor.setTypeface(face);
		mBtnOutdoor = (Button) findViewById(R.id.btnNavigationSelectOutdoor);
		mBtnOutdoor.setTypeface(face);
		mBtnIndoorMap = (Button) findViewById(R.id.btnNavigationSelectIndoorMap);
		mBtnIndoorMap.setTypeface(face);


		mBtnIndoor.setOnClickListener(this);
		mBtnOutdoor.setOnClickListener(this);
		mBtnIndoorMap.setOnClickListener(this);
	}

	public void showMaps(View v) {
		Intent showMaps = new Intent(this, MapsActivity.class);
		startActivity(showMaps);
		overridePendingTransition(R.anim.slide_in_right, R.anim.hold_screen);
	}

	public void showIndoor(View v) {
		Intent showIndoor = new Intent(this, NavigationIndoorActivity.class);
		startActivity(showIndoor);
		overridePendingTransition(R.anim.slide_in_right, R.anim.hold_screen);
	}

	public void showIndoorMap(View v) {
		Intent showIndoorMaps = new Intent(this, NavigationIndoorMapActivity.class);
		startActivity(showIndoorMaps);
		overridePendingTransition(R.anim.slide_in_right, R.anim.hold_screen);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.btnNavigationSelectOutdoor:
				showMaps(v);
				break;
			case R.id.btnNavigationSelectIndoor:
				showIndoor(v);
				break;
			case R.id.btnNavigationSelectIndoorMap:
				showIndoorMap(v);
				break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		MainActivity.animateReverseButtons();
		overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
	}
}
