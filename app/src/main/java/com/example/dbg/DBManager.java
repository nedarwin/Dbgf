package com.example.dbg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addResult(String username, int score) {
		db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score
				+ ");");
	}
	//User1 150 'User', 150);DROP TABLE RESULTS --
	//zapretit '
	//"INSERT INTO RESULTS VALUES ('User1',150);
	//
	public int gCount(){
		Cursor c2 = db.rawQuery("SELECT COUNT(*) as count FROM RESULTS;",null );
		c2.moveToFirst();
		return c2.getInt(0);
	}
	public int maxCount(){
		Cursor c2 = db.rawQuery("SELECT MAX(SCORE) FROM RESULTS;",null );
		c2.moveToFirst();
		return c2.getInt(0);
	}
	public int minCount(){
		Cursor c2 = db.rawQuery("SELECT MIN(SCORE) FROM RESULTS;",null );
		c2.moveToFirst();
		return c2.getInt(0);
	}
	public void delete(){
		db.delete("RESULTS", null, null);
	}
	ArrayList<Result> getAllResults() {
		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);

		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}
		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}
}