package com.testapplication.wfcmainpage.activity;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * This class provides the main functions of the rent page
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.adapters.RentAdapter;
import com.testapplication.wfcmainpage.database.MyDatabase;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.ArrayList;
import java.util.List;


public class RentActivity extends ActionBarActivity {

    private MyDatabase mDb;
    private List<Rentable> mItems;
    private ArrayList<Rentable> mRentables = new ArrayList<>();

    private RecyclerView mRentList;
    private RecyclerView.Adapter mRentAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        setTitle(getString(R.string.rent_title_text));
        mDb = new MyDatabase(this);
        mItems = mDb.getAllRentables();
        mRentables = getAllRentables();

        initRecyclerView();

    }

    private void initRecyclerView() {
        mRentList = (RecyclerView) findViewById(R.id.rentList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRentList.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRentList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mRentAdapter = new RentAdapter(mRentables);
        mRentList.setAdapter(mRentAdapter);
    }

    public ArrayList getAllRentables() {
        mRentables.clear();
        for (int i = 0; i < mItems.size(); i++) {
            mRentables.add(
                    new Rentable(
                            mItems.get(i).getmName(),
                            mItems.get(i).getmInfo(),
                            mItems.get(i).getmType(),
                            mItems.get(i).getmSize(),
                            mItems.get(i).getmTower(),
                            mItems.get(i).getmFloor(),
                            mItems.get(i).getmRoom(),
                            mItems.get(i).getmImage(),
                            mItems.get(i).getmSiteLink()));
        }
        return mRentables;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.animateReverseButtons();
        overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
    }
}
