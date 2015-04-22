package com.testapplication.wfcmainpage.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;

/**
 * @author Remco Hilbert & Fren de Haan
 *         activity om informatie over het WFC te laten zien.
 */
public class InfoActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura Extra Bold.ttf");

        TextView historyTitle = (TextView) findViewById(R.id.historyTitle);
        historyTitle.setTypeface(face);

        TextView mijlpaalTitle = (TextView) findViewById(R.id.mijlpaalTitle);
        mijlpaalTitle.setTypeface(face);

        TextView managementTitle = (TextView) findViewById(R.id.managementTitle);
        managementTitle.setTypeface(face);

        TextView bezoekTitle = (TextView) findViewById(R.id.bezoekTitle);
        bezoekTitle.setTypeface(face);

        setTitle(getString(R.string.info_title_text));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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
		MainActivity.animateReverseButtons();
		overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
	}
}
