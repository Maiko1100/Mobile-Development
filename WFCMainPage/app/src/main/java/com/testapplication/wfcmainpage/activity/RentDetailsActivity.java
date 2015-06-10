package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.models.Rentable;


public class RentDetailsActivity extends ActionBarActivity implements View.OnClickListener {

	private String iSite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rent_details);
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("Bundle");
		Rentable rentable = (Rentable) bundle.getSerializable("Rentable");

		String iInfo = rentable.getmInfo();
		String iType = rentable.getmType();
		String iTower = rentable.getmTower();
		String iFloor = rentable.getmFloor();
		String iRoom = rentable.getmRoom();
		iSite = rentable.getmSiteLink();
		String iImage = rentable.getmImage();

		setTitle(R.string.details_actionbar_text);

		((TextView) findViewById(R.id.txtInfo)).setText(iInfo);
		((TextView) findViewById(R.id.txtType)).setText(iType);
		((TextView) findViewById(R.id.txtTower)).setText(iTower);
		((TextView) findViewById(R.id.txtFloor)).setText(iFloor);
		((TextView) findViewById(R.id.txtRoom)).setText(iRoom);
		((TextView) findViewById(R.id.txtSite)).setText(iSite);
		findViewById(R.id.txtSite).setOnClickListener(this);

		int resId = getResources().getIdentifier(iImage, "mipmap", getPackageName());
		((ImageView) findViewById(R.id.imgImage)).setImageResource(resId);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.txtSite:
				Intent intentWebsite = new Intent(Intent.ACTION_VIEW);
				intentWebsite.setData(Uri.parse(iSite));
				startActivity(intentWebsite);
				break;
		}
	}
}
