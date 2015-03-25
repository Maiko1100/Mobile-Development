package com.testapplication.wfcmainpage;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author Remco Hilbert & Fren de Haan
 * activity om informatie over het WFC te laten zien.
 */
public class InfoActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura Extra Bold.ttf");

        TextView historyTitle = (TextView)findViewById(R.id.historyTitle);
        historyTitle.setTypeface(face);

        TextView mijlpaalTitle = (TextView)findViewById(R.id.mijlpaalTitle);
        mijlpaalTitle.setTypeface(face);

        TextView managementTitle = (TextView)findViewById(R.id.managementTitle);
        managementTitle.setTypeface(face);

        TextView bezoekTitle = (TextView)findViewById(R.id.bezoekTitle);
        bezoekTitle.setTypeface(face);

        setTitle(getString(R.string.info_title_text));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8A076D")));
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
}
