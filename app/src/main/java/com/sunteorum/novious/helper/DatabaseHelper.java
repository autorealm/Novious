package com.sunteorum.novious.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "novious_dc";
	public static final int DB_VERSION = 1;

	private static SQLiteDatabase mDatabase = null;
	private static DatabaseHelper mDBHelper = null;

	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mDBHelper = this;
		mDatabase = this.getWritableDatabase();
		
	}

	protected DatabaseHelper (Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		mDatabase = this.getWritableDatabase();
		
	}
	
	public synchronized static DatabaseHelper getInstance(Context context) {
		if (mDBHelper == null) {
			mDBHelper = new DatabaseHelper(context);
		}
		
		return mDBHelper;
	}
	
	@Override
	public synchronized void close() {
		super.close();
		if (mDatabase != null && mDatabase.isOpen()) {
			mDatabase.close();
			mDatabase = null;
		}
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {
			db.execSQL("");
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(" DROP TABLE IF EXISTS ");
		
		onCreate(db);

	}

}
