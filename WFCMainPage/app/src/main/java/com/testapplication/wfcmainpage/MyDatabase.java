package com.testapplication.wfcmainpage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fren & Remco on 31-3-2015.
 */
public class MyDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "Facility.db";
	private static final int DATABASE_VERSION = 1;

	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public List<Facility> getAllFacilities() {
		List<Facility> facilities = new LinkedList<Facility>();

		// 1. build the query
		String query = "SELECT  * FROM facilities";

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

				// Add book to books
				facilities.add(Facility);
			} while (cursor.moveToNext());
		}

		Log.d("getAllFacilities()", facilities.toString());

		// return books
		return facilities;
	}
}