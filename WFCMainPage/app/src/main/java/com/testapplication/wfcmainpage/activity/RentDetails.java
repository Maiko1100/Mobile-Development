package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;


public class RentDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_details);
        Intent intent = getIntent();
        String iInfo = intent.getStringExtra("info");
        String iType = intent.getStringExtra("type");
        String iTower = intent.getStringExtra("tower");
        String iFloor = intent.getStringExtra("floor");
        String iRoom = intent.getStringExtra("room");
        String iSite = intent.getStringExtra("site");
        String iImage = intent.getStringExtra("image");
        setTitle(R.string.details_actionbar_text);


        TextView info, type,tower,floor,room,site;
        ImageView imgImage;
        info = (TextView) findViewById(R.id.txtInfo);
        type = (TextView) findViewById(R.id.txtType);
        tower = (TextView) findViewById(R.id.txtTower);
        floor = (TextView) findViewById(R.id.txtFloor);
        room = (TextView) findViewById(R.id.txtRoom);
        site = (TextView) findViewById(R.id.txtSite);
        imgImage = (ImageView) findViewById(R.id.imgImage);
        int resId = getResources().getIdentifier(iImage, "mipmap", getPackageName());
        info.setText(iInfo);
        type.setText(iType);
        tower.setText(iTower);
        floor.setText(iFloor);
        room.setText(iRoom);
        site.setText(iSite);
        imgImage.setImageResource(resId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facilities_rent_details, menu);
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
        overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
    }
}
