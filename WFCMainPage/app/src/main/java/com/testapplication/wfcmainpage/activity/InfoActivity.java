package com.testapplication.wfcmainpage.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
	public void onBackPressed() {
		super.onBackPressed();
		MainActivity.animateReverseButtons();
		overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
	}
}
