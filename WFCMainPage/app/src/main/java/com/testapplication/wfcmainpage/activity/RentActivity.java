package com.testapplication.wfcmainpage.activity;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class is de main van de rent page.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.adapters.RentAdapter;
import com.testapplication.wfcmainpage.database.MyDatabase;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.ArrayList;
import java.util.List;


public class RentActivity extends ActionBarActivity {

	private MyDatabase mDb;
	private List<Rentable> mItems;
	private ArrayList<Rentable> mRentables = new ArrayList<>();
	private ListView mRentList;
	private RentAdapter rentAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rent);
		setTitle(getString(R.string.rent_title_text));
		mDb = new MyDatabase(this);
		mItems = mDb.getAllRentables();
		mRentables = getAllRentables();

		rentAdapter = new RentAdapter(getBaseContext(), mRentables);
		mRentList = (ListView) findViewById(R.id.rentList);
		mRentList.setAdapter(rentAdapter);


		mRentList.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					/**
					 * Provides a itemClickListener for the listview, registers clicks and
					 * sends the extra info through to the details activity.
					 */
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
						Rentable rentable = (Rentable) parent.getItemAtPosition(pPosition);
						Intent myIntent = new Intent(RentActivity.this, RentDetailsActivity.class);
						myIntent.putExtra("info", rentable.getmInfo());
						myIntent.putExtra("type", rentable.getmType());
						myIntent.putExtra("tower", rentable.getmTower());
						myIntent.putExtra("floor", rentable.getmFloor());
						myIntent.putExtra("room", rentable.getmRoom());
						myIntent.putExtra("site", rentable.getmSiteLink());
						myIntent.putExtra("image", rentable.getmImage());
						RentActivity.this.startActivity(myIntent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.hold_screen);
					}
				});


	}

	public ArrayList getAllRentables() {
		mRentables.clear();
		for (int i = 0; i < mItems.size(); i++) {
			mRentables.add(
					new Rentable(
							mItems.get(i).getmName(),
							mItems.get(i).getmInfo(),
							mItems.get(i).getmType(),
							mItems.get(i).getmSize(),
							mItems.get(i).getmTower(),
							mItems.get(i).getmFloor(),
							mItems.get(i).getmRoom(),
							mItems.get(i).getmImage(),
							mItems.get(i).getmSiteLink()));
		}
		return mRentables;
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		MainActivity.animateReverseButtons();
		overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
	}
}
