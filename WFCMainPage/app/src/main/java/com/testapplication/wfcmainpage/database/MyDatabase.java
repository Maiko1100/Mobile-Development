package com.testapplication.wfcmainpage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.testapplication.wfcmainpage.models.Facility;
import com.testapplication.wfcmainpage.models.Rentable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fren & Remco on 31-3-2015.
 */
public class MyDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "Facilitiesv6.db";
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
                Facility.setFacilityNaam(cursor.getString(1));
                Facility.setTelefoonNummer(cursor.getString(2));
                Facility.setWebsite(cursor.getString(3));
                Facility.setTower(cursor.getString(4));
                Facility.setEtage(cursor.getString(5));
                Facility.setShowRoom(cursor.getString(6));
                Facility.setEmail(cursor.getString(7));
                Facility.setDamesMode(cursor.getString(8));
                Facility.setHerenMode(cursor.getString(9));
                Facility.setKinderMode(cursor.getString(10));
                Facility.setAccessoires(cursor.getString(11));
                Facility.setVoorraad(cursor.getString(12));
                Facility.setXlDames(cursor.getString(13));
                Facility.setXlHeren(cursor.getString(14));
                Facility.setSportKleding(cursor.getString(15));
                Facility.setBruidsKleding(cursor.getString(16));
                Facility.setBabySpullen(cursor.getString(17));
                Facility.setBadMode(cursor.getString(18));

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
            } while (cursor.moveToNext());
        }
        return rentables;
    }
}