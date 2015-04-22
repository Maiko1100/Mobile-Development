package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;

import java.util.ArrayList;


public class FacilitiesDetails extends ActionBarActivity implements View.OnClickListener {

    String tFoon;
    String tEmail;
    String tWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        setTitle(getString(R.string.details_actionbar_text));

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura Extra Bold.ttf");

        Intent intent = getIntent();
        tWebsite = intent.getStringExtra("website");
        String tFacilityName = intent.getStringExtra("facilityname");
        tFoon = intent.getStringExtra("telefoon");
        String tTower = intent.getStringExtra("tower");
        String tEtage = intent.getStringExtra("etage");
        String tShowroom = intent.getStringExtra("showroom");

        String tEmail = intent.getStringExtra("email");
        ArrayList<String> facilityMode = intent.getStringArrayListExtra("mode");

        TextView facilityName, website, foon, tower, etage, showroom, email, contact, locatie, mode, modetitle;

        tEmail = intent.getStringExtra("email");


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
        mode = (TextView) findViewById(R.id.txtMode);
        modetitle = (TextView) findViewById(R.id.modetext);
        modetitle.setTypeface(face);
        facilityName.setText(tFacilityName);
        String mode2 = "";
        for (int i = 0; i < facilityMode.size(); i++) {
            mode2 = mode2 + facilityMode.get(i) + "\n";
        }
        mode.setText(mode2);

        if (tWebsite.isEmpty()) {

            if (tWebsite.isEmpty()) {

                website.setText("Niet Beschikbaar");
            } else {
                website.setText(tWebsite);
                website.setOnClickListener(this);
            }
            if (tFoon.isEmpty()) {
                foon.setText("Niet Beschikbaar");
            } else {
                foon.setText(tFoon);
                foon.setOnClickListener(this);
            }
            if (tEmail.isEmpty()) {
                email.setText("Niet Beschikbaar");
            } else {
                email.setText(tEmail);
                email.setOnClickListener(this);
            }

            tower.setText(tTower);
            etage.setText(tEtage);
            showroom.setText(tShowroom);


        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_facilities_details, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.txtFoon:
                    Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                    intentPhone.setData(Uri.parse("tel:" + tFoon));
                    startActivity(intentPhone);
                    break;
                case R.id.txtEmail:
                    Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                    intentEmail.setData(Uri.parse("mailto:" + tEmail));
                    startActivity(intentEmail);
                    break;
                case R.id.txtWebsite:
                    Intent intentWebsite = new Intent(Intent.ACTION_VIEW);
                    intentWebsite.setData(Uri.parse("http://" + tWebsite));
                    startActivity(intentWebsite);
                    break;
            }
        }

        @Override
        public void onBackPressed () {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}
