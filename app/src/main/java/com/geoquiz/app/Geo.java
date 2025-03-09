/*
Copyright (c) 2025 Denny Placzek
All rights reserved. This program and the accompanying materials
are made available under the terms of the Eclipse Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/epl-v10.html

Contributors:
    Denny Placzek - initial API and implementation
*/

package com.geoquiz.app;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Geo {
	
	private static final String LOG_TAG = null;
	SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    public Geo(Context context) {
        dbHelper = new MySQLiteHelper(context);

    }
    
    /**
     * CRUD operations (create "add", read "get", update, delete)
     */
      
 // Geo table name
    private static final String TABLE_GEO = "geo";
 
    // Geo Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_CAPITAL = "capital";
    private static final String COLUMN_POPULATION = "population";
    
    private static final String[] COLUMNS = {COLUMN_ID,COLUMN_COUNTRY,COLUMN_CAPITAL,COLUMN_POPULATION};
    
    public void addData(Data data){
        Log.d("addData", data.toString());
        // 1. open DB
        open();
        
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNTRY, data.getCountry()); // get country
        values.put(COLUMN_CAPITAL, data.getCapital()); // get capital
        values.put(COLUMN_POPULATION, data.getPopulation()); // get population
 
        // 3. insert
        database.insert(TABLE_GEO, null, values); // key/value -> keys = column names/ values = column values

        // 4. close
        close(); 
    }
 
    public Data getData(int id){
 
        // 1. open readable DB
        readDB();
 
        // 2. build query
        Cursor cursor = 
                database.query(TABLE_GEO, // a. table
                COLUMNS, // b. column names
                " _id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build data object
        Data data = new Data();
        data.setId(Integer.parseInt(cursor.getString(0)));
        data.setCountry(cursor.getString(1));
        data.setCapital(cursor.getString(2));
        data.setPopulation(Integer.parseInt(cursor.getString(3)));

        Log.i("getData("+id+")", data.toString());

        // 5. return data
        return data;
    }
 
    // Get All Data
    public List<Data> getAllData() {
        List<Data> geo = new ArrayList<Data>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_GEO;
 
        // 2. open writable DB
        open();
        Cursor cursor = database.rawQuery(query, null);
 
        // 3. go over each row, build data and add it to list
        Data data = null;
        if (cursor.moveToFirst()) {
            do {
            	data = new Data();
            	data.setId(Integer.parseInt(cursor.getString(0)));
            	data.setCountry(cursor.getString(1));
            	data.setCapital(cursor.getString(2));
            	data.setPopulation(Integer.parseInt(cursor.getString(3)));
 
                // Add geo to data
            	geo.add(data);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllData()", data.toString());
 
        // return geo
        return geo;
    }
 
     // Updating single book
    public int updateData(Data data) {
 
        // 1. open writable DB
        open();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("country", data.getCountry()); // get title 
        values.put("capital", data.getCapital()); // get author
        values.put("population", data.getPopulation()); // get author
 
        // 3. updating row
        int i = database.update(TABLE_GEO, //table
                values, // column/value
                COLUMN_ID+" = ?", // selections
                new String[] { String.valueOf(data.getId()) }); //selection args
 
        // 4. close
        close();
 
        return i;
 
    }
 
    // Deleting single book
    public void deleteData(Data data) {
 
        // 1. open DB
        open();
 
        // 2. delete
        database.delete(TABLE_GEO,
                COLUMN_ID+" = ?",
                new String[] { String.valueOf(data.getId()) });
 
        // 3. close
        database.close();
 
        Log.d("deleteData", data.toString());
 
    }

    public void readDB() {
    	database = dbHelper.getReadableDatabase();
    	Log.i(LOG_TAG, "Database opened");
    }
    
    
	public void open() {
	    database = dbHelper.getWritableDatabase();
	    Log.i(LOG_TAG, "Database Opened");
	} // end method open
	
	public void close() {
	    Log.i(LOG_TAG, "Database Closed");
	    dbHelper.close();
	} // end method close

    public boolean chkGeo(int id){

        readDB();

        Cursor cursor  = database.rawQuery("SELECT * FROM " + TABLE_GEO + " WHERE _id = " + id, null );

        boolean bcursor = cursor.getCount() > 0;

        cursor.close();
        close();

        return bcursor;
    }

}
