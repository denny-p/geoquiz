/* Copyright (c) 2025 Denny Placzek
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

public class Highscore {	
		
		private static final String LOG_TAG = null;
		SQLiteOpenHelper dbHelper;
	    SQLiteDatabase database;
	    
	    public Highscore(Context context) {
	        dbHelper = new MySQLiteHelper(context);

	    }
	    
	    /**
	     * CRUD operations (create "add", read "get", update, delete)
	     */
	      
	 // Geo table name
	    private static final String TABLE_USER = "users";
	 
	  //User Table Column names
	    private static final String KEY_USER_ID = "id";
	    private static final String KEY_USER_NAME = "user_name";
	    private static final String KEY_USER_HIGHSCORE = "high_score";
	    private static final String KEY_USER_DATE = "user_date";
	    
	    private static final String[] COLUMNS = {KEY_USER_ID,KEY_USER_NAME,KEY_USER_HIGHSCORE,KEY_USER_DATE};
	    
	    public void addData(HighscoreData data){
	        Log.d("addData", data.toString());
	        // 1. open DB
	        open();
	        
	        // 2. create ContentValues to add key "column"/value
	        ContentValues values = new ContentValues();
	        values.put(KEY_USER_NAME, data.getUser_name()); // get country 
	        values.put(KEY_USER_HIGHSCORE, data.getHighscore()); // get capital
	        values.put(KEY_USER_DATE, data.getUser_date()); // get population
	 
	        // 3. insert
	        database.insert(TABLE_USER, // table
	                null, //nullColumnHack
	                values); // key/value -> keys = column names/ values = column values
	 
	        // 4. close
	        close(); 
	    }
	 
	    public HighscoreData getData(int id){
	 
	        // 1. open readable DB
	        readDB();
	 
	        // 2. build query
	        Cursor cursor = 
	                database.query(TABLE_USER, // a. table
	                COLUMNS, // b. column names
	                " id = ?", // c. selections 
	                new String[] { String.valueOf(id) }, // d. selections args
	                null, // e. group by
	                null, // f. having
	                null, // g. order by
	                null); // h. limit
	 
	        // 3. if we got results get the first one
	        if (cursor != null)
	            cursor.moveToFirst();
	 
	        // 4. build highdata object
	        HighscoreData highdata = new HighscoreData();
	        highdata.set_userid(Integer.parseInt(cursor.getString(0)));
	        highdata.setUser_name(cursor.getString(1));
	        highdata.setHighscore(Integer.parseInt(cursor.getString(2)));
	        highdata.setUser_date(cursor.getString(3));
	        	 
	        Log.d("getData("+id+")", highdata.toString());
	 
	        // 5. return data
	        return highdata;
	    }
	 
	    // Get All Data
	    public List<HighscoreData> getAllData() {
	        List<HighscoreData> users = new ArrayList<HighscoreData>();
	 
	        // 1. build the query
	        String query = "SELECT  * FROM " + TABLE_USER;
	 
	        // 2. open writable DB
	        open();
	        Cursor cursor = database.rawQuery(query, null);
	 
	        // 3. go over each row, build data and add it to list
	        HighscoreData highdata = null;
	        if (cursor.moveToFirst()) {
	            do {
	            	highdata = new HighscoreData();
	            	highdata.set_userid(Integer.parseInt(cursor.getString(0)));
	            	highdata.setUser_name(cursor.getString(1));
	            	highdata.setUser_date(cursor.getString(2));
	            	highdata.setHighscore(Integer.parseInt(cursor.getString(3)));
	 
	                // Add geo to data
	            	users.add(highdata);
	            } while (cursor.moveToNext());
	        }
	 
	        Log.d("getAllData()", highdata.toString());
	 
	        // return geo
	        return users;
	    }
	 
	     // Updating single book
	    public int updateData(HighscoreData data) {
	 
	        // 1. open writable DB
	        open();
	 
	        // 2. create ContentValues to add key "column"/value
	        ContentValues values = new ContentValues();
	        values.put("user_name", data.getUser_name()); // get user name 
	        values.put("highscore", data.getHighscore()); // get highscore
	        values.put("user_date", data.getUser_date()); // get user date
	 
	        // 3. updating row
	        int i = database.update(TABLE_USER, //table
	                values, // column/value
	                KEY_USER_ID+" = ?", // selections
	                new String[] { String.valueOf(data.get_userid()) }); //selection args
	 
	        // 4. close
	        close();
	 
	        return i;
	 
	    }
	 
	    // Deleting single book
	    public void deleteData(HighscoreData data) {
	 
	        // 1. open DB
	        open();
	 
	        // 2. delete
	        database.delete(TABLE_USER,
	                KEY_USER_ID+" = ?",
	                new String[] { String.valueOf(data.get_userid()) });
	 
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



	}

	

