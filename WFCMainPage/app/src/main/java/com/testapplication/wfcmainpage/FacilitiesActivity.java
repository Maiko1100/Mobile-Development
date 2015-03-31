package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides de facility page met hierin de faciliteiten van het world fashion centre,
 * deze bevat ook een zoekfunctie om het vinden van faciliteiten makkelijker te maken voor de gebruiker
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FacilitiesActivity extends ActionBarActivity {

	/**
	 * @param facilityAdapter custom adapter om items uit de array naar list te zetten
	 * @param facilityList List View volledige pagina
	 * @param mFacilities Array van Facilities objects
	 * @param searchInput edittext object voor de zoekfunctie
	 */
	CustomAdapter facilityAdapter;
	ListView facilityList;
	ArrayList<Facility> mFacilities= new ArrayList<>();
	EditText searchInput;
	private List<Facility> items;
	private MyDatabase db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facilities);

		//Array voor facility names. vervangen door database items.

		db = new MyDatabase(this);
		items = db.getAllFacilities();

		for(int i=1; i<items.size(); i++){
			mFacilities.add(new Facility(items.get(i).getFacilityNaam(),items.get(i).getTelefoonNummer(),items.get(i).getWebsite()));
		}





		//initialize
		facilityAdapter = new CustomAdapter(getBaseContext(), mFacilities);
		facilityList = (ListView) findViewById(R.id.facilitiesList);
		searchInput = (EditText)findViewById(R.id.searchInput);

		facilityList.setAdapter(facilityAdapter);

		searchInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				facilityAdapter.getFilter().filter(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});


		facilityList.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
						Intent myIntent = new Intent(FacilitiesActivity.this, FacilitiesDetails.class);
						myIntent.putExtra("info", mFacilities.get(pPosition).getFacilityNaam());
						myIntent.putExtra("title", mFacilities.get(pPosition).getWebsite());

						FacilitiesActivity.this.startActivity(myIntent);
					}
				}
		);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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
}