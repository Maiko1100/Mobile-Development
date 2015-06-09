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

		setTitle(getString(R.string.info_title_text));
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		MainActivity.animateReverseButtons();
		overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
	}
}
