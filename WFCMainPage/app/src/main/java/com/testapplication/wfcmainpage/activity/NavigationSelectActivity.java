package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.testapplication.wfcmainpage.R;

public class NavigationSelectActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mBtnOutdoor, mBtnIndoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_select);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura (Light).ttf");

        mBtnIndoor = (Button) findViewById(R.id.btnNavigationSelectIndoor);
        mBtnIndoor.setTypeface(face);
        mBtnOutdoor = (Button) findViewById(R.id.btnNavigationSelectOutdoor);
        mBtnOutdoor.setTypeface(face);

        mBtnIndoor.setOnClickListener(this);
        mBtnOutdoor.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation_select, menu);
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

    public void showMaps(View v) {
        Intent showMaps = new Intent(this, MapsActivity.class);
        startActivity(showMaps);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void showIndoor(View v) {
        Intent indoorBeacon = new Intent(this, NavigationIndoor.class);
        startActivity(indoorBeacon);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnNavigationSelectOutdoor:
                showMaps(v);
                break;
            case R.id.btnNavigationSelectIndoor:
                showIndoor(v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
