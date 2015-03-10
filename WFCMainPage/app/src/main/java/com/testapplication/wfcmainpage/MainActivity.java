package com.testapplication.wfcmainpage;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Timer mCarrouselTimer;
    private int mCurrentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.main_banner_text));

	    int[] carrouselIds = getResources().getIntArray(R.array.carrouselImages);

        mCarrouselTimer = new Timer();
        mCarrouselTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
		        TimerMethod();
	        }
        }, 0, 7000);
    }

    private void TimerMethod() {
        runOnUiThread(Timer_Tick);

    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {

            ImageView img = (ImageView) findViewById(R.id.imageView);

            mCurrentImage++;

            if(mCurrentImage>=4 || mCurrentImage<=0){
                mCurrentImage =1;
            }

            switch (mCurrentImage) {
                case 1:
                    img.setImageResource(R.drawable.image1);
                    break;
                case 2:
                    img.setImageResource(R.drawable.image2);
                    break;
                case 3:
                    img.setImageResource(R.drawable.image3);
                    mCurrentImage = 0;
                    break;
                default:
                    img.setImageResource(R.drawable.image1);
            }
        }
    };

    public void nextImage(View view){
        mCurrentImage++;

        ImageView img = (ImageView) findViewById(R.id.imageView);

        if(mCurrentImage>=4 || mCurrentImage<=0){
            mCurrentImage =1;
        }

        switch (mCurrentImage) {
            case 1:
                img.setImageResource(R.drawable.image1);
                break;
            case 2:
                img.setImageResource(R.drawable.image2);
                break;
            case 3:
                img.setImageResource(R.drawable.image3);
                break;
            default:
                img.setImageResource(R.drawable.image1);
        }

        mCarrouselTimer.cancel();
        mCarrouselTimer = new Timer();
        mCarrouselTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
		        TimerMethod();
	        }
        }, 7000, 7000);
    }

    public void prevImage(View view){
        mCurrentImage--;

        ImageView img = (ImageView) findViewById(R.id.imageView);

        if(mCurrentImage>=4 || mCurrentImage<=0){
            mCurrentImage =3;
        }

        switch (mCurrentImage) {
            case 1:
                img.setImageResource(R.drawable.image1);
                break;
            case 2:
                img.setImageResource(R.drawable.image2);
                break;
            case 3:
                img.setImageResource(R.drawable.image3);
                break;
            default:
                System.out.println(mCurrentImage);
        }

        mCarrouselTimer.cancel();
        mCarrouselTimer = new Timer();
        mCarrouselTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
		        TimerMethod();
	        }
        }, 7000, 7000);
    }

    public void showInfo(View view) {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }

	public void showMaps(View view) {
		Intent i = new Intent(this, MapsActivity.class);
		startActivity(i);
	}

    public void showFacilities(View view) {
        Intent i = new Intent(this, FacilitiesActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

	}
}

