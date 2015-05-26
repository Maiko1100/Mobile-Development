package com.testapplication.wfcmainpage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.testapplication.wfcmainpage.models.Facility;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fren & Remco on 31-3-2015.
 */
public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Facilitiesv7.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public List<Facility> getAllFacilities() {
        List<Facility> facilities = new LinkedList<Facility>();

        // 1. build the query
        String query = "SELECT * FROM Facility";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Facility Facility = null;
        if (cursor.moveToFirst()) {
            do {
                Facility = new Facility();
                Facility.setId(Integer.parseInt(cursor.getString(0)));
                Facility.setmFacilityName(cursor.getString(1));
                Facility.setmTelefoonNummer(cursor.getString(2));
                Facility.setmWebsite(cursor.getString(3));
                Facility.setmTower(cursor.getString(4));
                Facility.setmEtage(cursor.getString(5));
                Facility.setmShowRoom(cursor.getString(6));
                Facility.setmEmail(cursor.getString(7));
                Facility.setmDamesMode(cursor.getString(8));
                Facility.setmHerenMode(cursor.getString(9));
                Facility.setmKinderMode(cursor.getString(10));
                Facility.setmAccessoires(cursor.getString(11));
                Facility.setmVoorraad(cursor.getString(12));
                Facility.setmXlDames(cursor.getString(13));
                Facility.setmXlHeren(cursor.getString(14));
                Facility.setmSportKleding(cursor.getString(15));
                Facility.setmBruidsKleding(cursor.getString(16));
                Facility.setmBabySpullen(cursor.getString(17));
                Facility.setmBadMode(cursor.getString(18));

                facilities.add(Facility);
            } while (cursor.moveToNext());
        }
        return facilities;
    }

    public List<Rentable> getAllRentables() {
        List<Rentable> rentables = new LinkedList<Rentable>();

        // 1. build the query
        String query = "SELECT * FROM Rentable";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Rentable Rentable = null;
        if (cursor.moveToFirst()) {
            do {
                Rentable = new Rentable();
                Rentable.setmId(Integer.parseInt(cursor.getString(0)));
                Rentable.setmName(cursor.getString(1));
                Rentable.setmInfo(cursor.getString(2));
                Rentable.setmType(cursor.getString(3));
                Rentable.setmSize(cursor.getString(4));
                Rentable.setmTower(cursor.getString(5));
                Rentable.setmFloor(cursor.getString(6));
                Rentable.setmRoom(cursor.getString(7));
                Rentable.setmImage(cursor.getString(8));
                Rentable.setmSiteLink(cursor.getString(9));
                rentables.add(Rentable);
                System.out.println(Rentable.toString());
            } while (cursor.moveToNext());
        }
        return rentables;
    }
}