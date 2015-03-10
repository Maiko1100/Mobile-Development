package com.testapplication.wfcmainpage;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class FacilitiesDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        Intent intent = getIntent();
        String iInfo = intent.getStringExtra("info");
        String tTitle = intent.getStringExtra("title");
        int icon2 = intent.getIntExtra("test",0);

        TextView info,title;
        ImageView icon;
        info = (TextView) findViewById(R.id.txtInfo);
        title = (TextView) findViewById(R.id.txtTitle);
        icon = (ImageView) findViewById(R.id.icon);
        info.setText(iInfo);
        title.setText(tTitle);

        //icon.setImageResource(test);

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
