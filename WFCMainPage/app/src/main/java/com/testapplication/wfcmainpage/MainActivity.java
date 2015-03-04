package com.testapplication.wfcmainpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    private Timer myTimer;
    private int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("WFC App");

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 0, 7000);
    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);

    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {

            ImageView img = (ImageView) findViewById(R.id.imageView);

            currentImage++;

            if(currentImage>=4 || currentImage<=0){
                currentImage =1;
            }

            switch (currentImage) {
                case 1:
                    img.setImageResource(R.drawable.image1);
                    break;
                case 2:
                    img.setImageResource(R.drawable.image2);
                    break;
                case 3:
                    img.setImageResource(R.drawable.image3);
                    currentImage = 0;
                    break;
                default:
                    img.setImageResource(R.drawable.image1);
            }
        }
    };

    public void nextImage(View view){
        currentImage++;

        ImageView img = (ImageView) findViewById(R.id.imageView);

        if(currentImage>=4 || currentImage<=0){
            currentImage =1;
        }

        switch (currentImage) {
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

        myTimer.cancel();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 7000, 7000);
    }

    public void prevImage(View view){
        currentImage--;

        ImageView img = (ImageView) findViewById(R.id.imageView);

        if(currentImage>=4 || currentImage<=0){
            currentImage =3;
        }

        switch (currentImage) {
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
                System.out.println(currentImage);
        }

        myTimer.cancel();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
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

}

