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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private Context context;
	// All Static variables

    private static final String LOG_TAG = null;
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "GeoDB";
    
 // Geo table name
    private static final String TABLE_GEO = "geo";
    private static final String TABLE_USER = "users";
 
    // Geo Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_CAPITAL = "capital";
    private static final String COLUMN_POPULATION = "population";
    
    //User Table Column names
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_HIGHSCORE = "high_score";
    private static final String COLUMN_USER_DATE = "user_date";
     
    //Table Create Statements 
    //Geo Table Statement
    private static final String CREATE_GEO_TABLE = "CREATE TABLE IF NOT EXISTS  " + TABLE_GEO + " ("
    		+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COUNTRY + " TEXT, "
            + COLUMN_CAPITAL + " TEXT, " + COLUMN_POPULATION + " INTEGER);";
    
    //User Table Statement
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ("
    		 + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, "
    		    + COLUMN_USER_HIGHSCORE + " INTEGER, " + COLUMN_USER_DATE + " DATETIME);";
   
    
    public MySQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //Log.i(LOG_TAG, CREATE_GEO_TABLE);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(LOG_TAG, CREATE_GEO_TABLE);
        db.execSQL(CREATE_GEO_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
 
        // Create tables again
        onCreate(db);
    }
}

