package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Deze class is de main van de facilities page.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;


public class FacilitiesActivity extends ActionBarActivity{

    ListAdapter facilityAdapter;
    //List View volledige pagina
    ListView facilityList;
    //Array van Facility objects
    Facility sFacilities[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        //Array voor facility names. vervangen door database items.
        sFacilities = new Facility[]{
                new Facility(R.drawable.bedrijf1, "Max Fashion Labels", "Women, Accessoiries"),
                new Facility(R.drawable.bedrijf2, "Mar-XS B.V.", "Men,Accessoiries"),
                new Facility(R.drawable.bedrijf3, "Demm Fashion Group B.V.", "Men,Women,Children,Accessoiries,Shoes,Other"),
                new Facility(R.drawable.bedrijf4, "Maxima Trends BV", "Women,Accessoiries,Shoes"),
                new Facility(R.drawable.bedrijf5, "DC Design & Concept GmbH", "Men,Accessoiries"),
                new Facility(R.drawable.logowfcsmall, "Insolita Moda Internazionale", "Men,Accessoiries")

        };

        //convert array to list items.
        facilityAdapter = new CustomAdapter(getBaseContext(), sFacilities);
        facilityList = (ListView) findViewById(R.id.facilitiesList);
        facilityList.setAdapter(facilityAdapter);

        facilityList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
                        Intent myIntent = new Intent(FacilitiesActivity.this, FacilitiesDetails.class);
                        myIntent.putExtra("info",sFacilities[pPosition].info);
                        myIntent.putExtra("title",sFacilities[pPosition].title);
                        myIntent.putExtra("icon",sFacilities[pPosition].icon);

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
