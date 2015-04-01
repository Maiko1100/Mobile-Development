package com.testapplication.wfcmainpage;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class FacilitiesDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        setTitle(getString(R.string.details_actionbar_text));

        Intent intent = getIntent();
        String tWebsite = intent.getStringExtra("website");
        String tTitle = intent.getStringExtra("title");
        String tFoon = intent.getStringExtra("telefoon");
        String tTower = intent.getStringExtra("tower");
        String tEtage = intent.getStringExtra("etage");
        String tShowroom = intent.getStringExtra("showroom");
        String tEmail = intent.getStringExtra("email");

        TextView info,title,foon,tower,etage,showroom,email;

        info = (TextView) findViewById(R.id.txtInfo);
        title = (TextView) findViewById(R.id.txtTitle);
        foon = (TextView) findViewById(R.id.txtFoon);
        tower = (TextView) findViewById(R.id.txtTower);
        etage = (TextView) findViewById(R.id.txtEtage);
        showroom = (TextView) findViewById(R.id.txtShowroom);
        email = (TextView) findViewById(R.id.txtEmail);

        info.setText(tWebsite);
        title.setText(tTitle);
        foon.setText(tFoon);
        tower.setText(tTower);
        etage.setText(tEtage);
        showroom.setText(tShowroom);
        email.setText(tEmail);
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
}
