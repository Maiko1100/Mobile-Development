package com.testapplication.wfcmainpage.activity;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides de facility page met hierin de faciliteiten van het world fashion centre,
 * deze bevat ook een zoekfunctie om het vinden van faciliteiten makkelijker te maken voor de gebruiker.
 */

import android.app.ActionBar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.adapters.CustomAdapter;
import com.testapplication.wfcmainpage.database.MyDatabase;
import com.testapplication.wfcmainpage.models.Facility;

import java.util.ArrayList;
import java.util.List;


public class FacilitiesActivity extends ActionBarActivity{

    /**
     * @param facilityAdapter Provides a custom adapter to put the items from the database to the list.
     * @param mFacilityList Provides a listview for the page.
     * @param mFacilities Provides an arraylist in which the Facility items are stored.
     * @param mSearchInput Provides and inputfield (Edittext) for searching.
     * @param mItems Provides a list for the facility items. data gets added from the database.
     * @param mSearchInputMenu Provides a boolean for opening and closing the searchInput.
     * @param inputMethodManager Provides a show / hide for the softkeyboard.
     */

    CustomAdapter facilityAdapter;
    private ListView mFacilityList;
    private ArrayList<Facility> mFacilities = new ArrayList<>();
    private List<Facility> mItems;
    private MyDatabase mDb;
    private EditText mSearchInput;
    private TextView mTitle;
    private Button mClearText;
    private boolean mSearchInputMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facilities);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // add the custom view to the action bar
        actionBar.setCustomView(R.layout.actionbar_view);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);


        mDb = new MyDatabase(this);
        mItems = mDb.getAllFacilities();

        for (int i = 1; i < mItems.size(); i++) {
            mFacilities.add(
                    new Facility(
                            mItems.get(i).getFacilityNaam(),
                            mItems.get(i).getTelefoonNummer(),
                            mItems.get(i).getWebsite(),
                            mItems.get(i).getTower(),
                            mItems.get(i).getEtage(),
                            mItems.get(i).getShowRoom(),
                            mItems.get(i).getEmail()));
        }

        //initialize
        facilityAdapter = new CustomAdapter(getBaseContext(), mFacilities);
        mFacilityList = (ListView) findViewById(R.id.facilitiesList);
        mSearchInput = (EditText) actionBar.getCustomView().findViewById(
                R.id.searchfield);
        mTitle = (TextView) actionBar.getCustomView().findViewById(
                R.id.facilitiesTitle);

        mClearText =(Button)actionBar.getCustomView().findViewById(R.id.clear_text);
	    mClearText.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    mSearchInput.setText("");
		    }
	    });

        mSearchInputMenu = false;



        mFacilityList.setAdapter(facilityAdapter);
        mSearchInput.setVisibility(View.GONE);
        mClearText.setVisibility(View.GONE);

        mSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            /**
             * Provides a textwatcher that checks if the input field changes and applies the filter from the CustomAdapter
             * @param s holds the input from the edittext
             */

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

                    /**
                     * Provides a itemClickListener for the listview, registers clicks and
                     * sends the extra info through to the details activity.
                     */

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

    /**
     * Provides an open method for the actionbar search button and opens the softkeyboard.
     */
    public void openSearch() {
        if (mSearchInputMenu == false) {
            mSearchInputMenu = true;
            mSearchInput.setVisibility(View.VISIBLE);
            mClearText.setVisibility(View.VISIBLE);
            mTitle.setVisibility(View.GONE);
            mSearchInput.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    public void closeSearch() {
        if (mSearchInputMenu == true) {
            mSearchInputMenu = false;

            mSearchInput.setText("");
            mSearchInput.setVisibility(View.GONE);
            mClearText.setVisibility(View.GONE);
            mTitle.setVisibility(View.VISIBLE);
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mSearchInput.getWindowToken(), 0);
        }
    }

    /**
     * Provides an inflater for the facilities activity menu xml file
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facilities_activity, menu);
        return true;
    }

    /**
     * Provides the option to open the inputfield for searching through the list.
     *
     * @param item gets the menu item id
     * @return
     */

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
        } else {
            closeSearch();
            return super.onOptionsItemSelected(item);
        }
    }
}
