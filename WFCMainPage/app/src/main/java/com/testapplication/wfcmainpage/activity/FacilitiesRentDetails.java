package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;


public class FacilitiesRentDetails extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_rent_details);
        Intent intent = getIntent();
        String iInfo = intent.getStringExtra("info");
        String tTitle = intent.getStringExtra("title");
        int icon2 = intent.getIntExtra("icon",0);

        TextView info,title;
        ImageView icon;
        info = (TextView) findViewById(R.id.txtInfo);
        title = (TextView) findViewById(R.id.txtTitle);
        icon = (ImageView) findViewById(R.id.icon);
        info.setText(iInfo);
        title.setText(tTitle);
        icon.setImageResource(icon2);

	    findViewById(R.id.facilityPhone).setOnClickListener(this);
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
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.facilityPhone:
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse("Call me maybe?"));
//				this.startActivity(callIntent);
				break;
		}
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
}
