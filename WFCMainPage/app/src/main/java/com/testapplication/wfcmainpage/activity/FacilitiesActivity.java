package com.testapplication.wfcmainpage.activity;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides de facility page met hierin de faciliteiten van het world fashion centre,
 * deze bevat ook een zoekfunctie om het vinden van faciliteiten makkelijker te maken voor de gebruiker
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.testapplication.wfcmainpage.adapters.CustomAdapter;
import com.testapplication.wfcmainpage.database.MyDatabase;
import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.Facility;

import java.util.ArrayList;
import java.util.List;


public class FacilitiesActivity extends ActionBarActivity {

	/**
	 * @param facilityAdapter custom adapter om mItems uit de array naar list te zetten
	 * @param mFacilityList List View volledige pagina
	 * @param mFacilities Array van Facilities objects
	 * @param mSearchInput edittext object voor de zoekfunctie
	 */
	CustomAdapter facilityAdapter;
	private ListView mFacilityList;
	private ArrayList<Facility> mFacilities= new ArrayList<>();
	private EditText mSearchInput;
	private List<Facility> mItems;
	private MyDatabase mDb;
    private boolean mSearchInputMenu;
    InputMethodManager inputMethodManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facilities);

		//Array voor facility names. vervangen door database mItems.

		mDb = new MyDatabase(this);
		mItems = mDb.getAllFacilities();

		for(int i=1; i< mItems.size(); i++){
			mFacilities.add(new Facility(mItems.get(i).getFacilityNaam(), mItems.get(i).getTelefoonNummer(), mItems.get(i).getWebsite(), mItems.get(i).getTower(), mItems.get(i).getEtage(), mItems.get(i).getShowRoom(), mItems.get(i).getEmail()));
		}

		//initialize
		facilityAdapter = new CustomAdapter(getBaseContext(), mFacilities);
		mFacilityList = (ListView) findViewById(R.id.facilitiesList);
		mSearchInput = (EditText)findViewById(R.id.searchInput);
        mSearchInputMenu = false;
        inputMethodManager = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);

		mFacilityList.setAdapter(facilityAdapter);

        mSearchInput.setVisibility(View.GONE);
		mSearchInput.addTextChangedListener(new TextWatcher() {
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


		mFacilityList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
                        Facility facility = (Facility) parent.getItemAtPosition(pPosition);

                        Intent myIntent = new Intent(FacilitiesActivity.this, FacilitiesDetails.class);
                        myIntent.putExtra("facilityname", facility.getFacilityNaam());
                        myIntent.putExtra("telefoon", facility.getTelefoonNummer());
                        myIntent.putExtra("website", facility.getWebsite());
                        myIntent.putExtra("tower", facility.getTower());
                        myIntent.putExtra("etage", facility.getEtage());
                        myIntent.putExtra("showroom", facility.getShowRoom());
                        myIntent.putExtra("email", facility.getEmail());


                        FacilitiesActivity.this.startActivity(myIntent);
                    }
                }
        );

	}

    public void openSearch(){
        if (mSearchInputMenu == false) {
            mSearchInputMenu = true;
            mSearchInput.setVisibility(View.VISIBLE);
            inputMethodManager.showSoftInput(mSearchInput, 0);
        }
    }

    public void closeSearch(){
        if (mSearchInputMenu == true) {
            mSearchInputMenu = false;
            mSearchInput.setVisibility(View.GONE);
            inputMethodManager.hideSoftInputFromWindow(mSearchInput.getWindowToken(), 0);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds mItems to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_facilities_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_search && !mSearchInputMenu) {
            openSearch();
			return true;
		}else {
            closeSearch();
            return super.onOptionsItemSelected(item);
        }
	}
}