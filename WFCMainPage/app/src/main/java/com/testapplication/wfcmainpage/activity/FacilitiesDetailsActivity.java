package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;

import java.util.ArrayList;


public class FacilitiesDetailsActivity extends ActionBarActivity implements View.OnClickListener {

    /**
     * @param mKledingCategories Provides a string where all modeCategories are stored in
     * @param mFacilityMode Provides an arraylist to fill mKledingCategories
     */

    private String mKledingCategories = "";
    private String mFoon, mEmail, mWebsite, mFacilityName, mTower, mEtage, mShowroom;
    private ArrayList<String> mFacilityMode;
    private TextView mTextFacilityName, mTextWebsite, mTextFoon, mTextTower, mTextEtage, mTextShowroom, mTextEmail, mTextContact, mTextLocatie, mTextMode, mTextModetitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_details);
        setTitle(getString(R.string.details_actionbar_text));

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Futura Extra Bold.ttf");

        Intent intent = getIntent();
        mWebsite = intent.getStringExtra("website");
        mFacilityName = intent.getStringExtra("facilityname");
        mFoon = intent.getStringExtra("telefoon");
        mTower = intent.getStringExtra("tower");
        mEtage = intent.getStringExtra("etage");
        mShowroom = intent.getStringExtra("showroom");
        mEmail = intent.getStringExtra("email");
        mFacilityMode = intent.getStringArrayListExtra("mode");

        // Declaratie Textviews
        mTextFacilityName = (TextView) findViewById(R.id.txtFacilityName);
        mTextFacilityName.setTypeface(face);
        mTextWebsite = (TextView) findViewById(R.id.txtWebsite);
        mTextFoon = (TextView) findViewById(R.id.txtFoon);
        mTextTower = (TextView) findViewById(R.id.txtTower);
        mTextEtage = (TextView) findViewById(R.id.txtEtage);
        mTextShowroom = (TextView) findViewById(R.id.txtShowroom);
        mTextEmail = (TextView) findViewById(R.id.txtEmail);
        mTextContact = (TextView) findViewById(R.id.locatietext);
        mTextContact.setTypeface(face);
        mTextLocatie = (TextView) findViewById(R.id.contacttext);
        mTextLocatie.setTypeface(face);
        mTextMode = (TextView) findViewById(R.id.txtMode);
        mTextModetitle = (TextView) findViewById(R.id.modetext);
        mTextModetitle.setTypeface(face);
        mTextFacilityName.setText(mFacilityName);


        // Fills mKledingCategories with all mode categories from this facility
        getMode();

        //Checks for empty strings when found changes the string in not availible
        //also gives the given textvieuws an onclicklistener when the string is not empty
        setOnTextClick(mTextWebsite, mWebsite);
        setOnTextClick(mTextFoon, mFoon);
        setOnTextClick(mTextEmail, mEmail);

        mTextTower.setText(mTower);
        mTextEtage.setText(mEtage);
        mTextShowroom.setText(mShowroom);


    }

    /**
     * @param textView Provides a TextView to store information about the facility and provides an onclicklistener
     * @param text     Provides the text used for the textview
     *                 Method to check if the string text is empty if the string is empty it gives him a standard value
     *                 else this method gives the given textview the given text + an onclicklistener
     */
    public void setOnTextClick(TextView textView, String text) {
        if (text.isEmpty()) {
            textView.setText(getString(R.string.niet_beschikbaar));
        } else {
            textView.setText(text);
            textView.setOnClickListener(this);
        }
    }

    /**
     * method to check if txtFoon txtEmail or txtWebsite is clicked. when one of these items are clicked
     * this method sends them to the right acitivy in this case the phone,mail or website.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtFoon:
                Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                intentPhone.setData(Uri.parse("tel:" + mFoon));
                startActivity(intentPhone);
                break;
            case R.id.txtEmail:
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                intentEmail.setData(Uri.parse("mailto:" + mEmail));
                startActivity(intentEmail);
                break;
            case R.id.txtWebsite:
                Intent intentWebsite = new Intent(Intent.ACTION_VIEW);
                intentWebsite.setData(Uri.parse("http://" + mWebsite));
                startActivity(intentWebsite);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
    }

    /**
     * Fills mKledingCategories with all mode categories from this facility
     */
    public void getMode() {
        for (int i = 0; i < mFacilityMode.size(); i++) {
            mKledingCategories = mKledingCategories + mFacilityMode.get(i) + "\n";
            mTextMode.setText(mKledingCategories);
        }

    }

}
