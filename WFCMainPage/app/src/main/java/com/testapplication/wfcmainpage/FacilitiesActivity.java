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


public class FacilitiesActivity extends ActionBarActivity{

    /**
     *
     * @param facilityAdapter custom adapter om items uit de array naar list te zetten
     * @param facilityList List View volledige pagina
     * @param mFacilities Array van Facilities objects
     * @param searchInput edittext object voor de zoekfunctie
     */

    ListAdapter facilityAdapter;
    ListView facilityList;
    private Facility mFacilities[];
    EditText searchInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        //Array voor facility names. vervangen door database items.
        mFacilities = new Facility[]{
                new Facility(R.drawable.bedrijf1, "Max Fashion Labels", "Women, Accessoiries"),
                new Facility(R.drawable.bedrijf2, "Mar-XS B.V.", "Men,Accessoiries"),
                new Facility(R.drawable.bedrijf3, "Demm Fashion Group B.V.", "Men,Women,Children,Accessoiries,Shoes,Other"),
                new Facility(R.drawable.bedrijf4, "Maxima Trends BV", "Women,Accessoiries,Shoes"),
                new Facility(R.drawable.bedrijf5, "DC Design & Concept GmbH", "Men,Accessoiries"),
                new Facility(R.drawable.logowfcsmall, "Insolita Moda Internazionale", "Men,Accessoiries")

        };

        //initialize
        facilityAdapter = new CustomAdapter(getBaseContext(), mFacilities);
        facilityList = (ListView) findViewById(R.id.facilitiesList);
        searchInput = (EditText)findViewById(R.id.searchInput);

        facilityList.setAdapter(facilityAdapter);
        //adds filterable input
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //When user changes inputtext

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

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
                        myIntent.putExtra("info", mFacilities[pPosition].info);
                        myIntent.putExtra("title", mFacilities[pPosition].title);
                        myIntent.putExtra("icon", mFacilities[pPosition].icon);

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
