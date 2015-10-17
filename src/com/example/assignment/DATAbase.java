package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DATAbase {
 public static final String photo = "photo";
 private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;

  private static final String DATABASE_NAME = "dname";
 private static final int DATABASE_VERSION = 1;

  private static final String TABLE = "table";

  private static final String CREATE_TABLE = "create table "
   + TABLE + " ( " + photo
   + " blob not null );";

  private final Context mCtx;

  private static class DatabaseHelper extends SQLiteOpenHelper {
  DatabaseHelper(Context context) {
   super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

   public void onCreate(SQLiteDatabase db) {
   db.execSQL(CREATE_TABLE);
  }

   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   db.execSQL("DROP TABLE IF EXISTS " + TABLE);
   onCreate(db);
  }
 }

  public void Reset() {
  mDbHelper.onUpgrade(this.mDb, 1, 1);
 }

  public DATAbase(Context ctx) {
  mCtx = ctx;
  mDbHelper = new DatabaseHelper(mCtx);
 }

  /*public DATAbase open() throws SQLException {
  mDb = mDbHelper.getWritableDatabase();
  return this;
 }*/

  public void close() {
  mDbHelper.close();
 }

  public void inserDetails(Image image) {
	  mDb = mDbHelper.getWritableDatabase();
  ContentValues cv = new ContentValues();
  cv.put(photo, R.drawable.ic_launcher);
  mDb.insert(TABLE, null, cv);
 }

 
}
