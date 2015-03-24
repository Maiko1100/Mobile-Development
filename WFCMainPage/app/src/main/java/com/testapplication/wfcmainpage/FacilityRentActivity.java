package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class is de main van de facilities page.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;


public class FacilityRentActivity extends ActionBarActivity{

    ListAdapter facilityRentAdapter;
    //List View volledige pagina
    ListView facilityRentList;
    //Array van Facility objects
    FacilityRent sRentFacilities[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_rent);
        setTitle(getString(R.string.rent_title_text));

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#20699C")));

        //Array voor facility names. vervangen door database items.
        sRentFacilities = new FacilityRent[]{
                new FacilityRent(R.drawable.bedrijf2, "Type: Showroom", "Grootte: 109 m2\n" +
                        "VERHUURD"),
                new FacilityRent(R.drawable.bedrijf3, "Type: Showroom / Kantoor", "Grootte: 406 m2\n" +
                        "Extra: Bijkeuken, conferentie kamer incl. licht, gipsplafond met dimlichten, serverruimte."),
                new FacilityRent(R.drawable.bedrijf4, "Type: Showroom", "Grootte: 183 m2\n" +
                        "Extra: Keuken, vloerbedekking, 3 aparte kamers incl. licht."),
                new FacilityRent(R.drawable.bedrijf5, "Type: Showroom", "Grootte: 303 m2\n" +
                        "Extra: Keuken, toonbank, rekken voor kleding, licht."),
                new FacilityRent(R.drawable.logowfcsmall, "Type: Showroom", "Grootte: 63 m2\n" +
                        "Extra: houten vloer, gesloten keuken met voorraadkast, wandrekken.")

        };

        //convert array to list items.
        facilityRentAdapter = new CustomFacilityRentAdapter(getBaseContext(), sRentFacilities);
        facilityRentList = (ListView) findViewById(R.id.facilitiesRentList);
        facilityRentList.setAdapter(facilityRentAdapter);


        facilityRentList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
                        Intent myIntent = new Intent(FacilityRentActivity.this, FacilitiesRentDetails.class);
                        myIntent.putExtra("info",sRentFacilities[pPosition].info);
                        myIntent.putExtra("title",sRentFacilities[pPosition].title);
                        myIntent.putExtra("icon",sRentFacilities[pPosition].icon);

                        FacilityRentActivity.this.startActivity(myIntent);
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
