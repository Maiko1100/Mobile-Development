package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;


public class FacilitiesDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        setTitle(getString(R.string.details_actionbar_text));

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura Extra Bold.ttf");

        Intent intent = getIntent();
        String tWebsite = intent.getStringExtra("website");
        String tFacilityName = intent.getStringExtra("facilityname");
        String tFoon = intent.getStringExtra("telefoon");
        String tTower = intent.getStringExtra("tower");
        String tEtage = intent.getStringExtra("etage");
        String tShowroom = intent.getStringExtra("showroom");
        String tEmail = intent.getStringExtra("email");

        TextView facilityName,website,foon,tower,etage,showroom,email,contact,locatie;

        facilityName = (TextView) findViewById(R.id.txtFacilityName);
        facilityName.setTypeface(face);
        website = (TextView) findViewById(R.id.txtWebsite);
        foon = (TextView) findViewById(R.id.txtFoon);
        tower = (TextView) findViewById(R.id.txtTower);
        etage = (TextView) findViewById(R.id.txtEtage);
        showroom = (TextView) findViewById(R.id.txtShowroom);
        email = (TextView) findViewById(R.id.txtEmail);
        contact = (TextView) findViewById(R.id.locatietext);
        contact.setTypeface(face);
        locatie = (TextView) findViewById(R.id.contacttext);
        locatie.setTypeface(face);

        facilityName.setText(tFacilityName);
        if(tWebsite.isEmpty()){
            website.setText("Niet Beschikbaar");
        }
        else{
            website.setText(tWebsite);
        }
        if(tFoon.isEmpty()) {
            foon.setText("Niet Beschikbaar");
        }
        else{
            foon.setText(tFoon);
        }
        if(tEmail.isEmpty()){
            email.setText("Niet Beschikbaar");
        }
        else{
            email.setText(tEmail);
        }

        tower.setText(tTower);
        etage.setText(tEtage);
        showroom.setText(tShowroom);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facilities_details, menu);
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
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
}
