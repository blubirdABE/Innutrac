package com.innutrac.poly.innutrac.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FoodDatabase {
	FoodDB handler;
	SQLiteDatabase db;
	private final Context ctx;

	public FoodDatabase(Context contx) {
		this.ctx = contx;
	}
	
	public class FoodDB extends SQLiteOpenHelper {
		private static final int DATABASE_VERSION = 1;

		private static final String TABLE_FOODRECORDS = "food_records";
		private static final String TABLE_FOODDB = "food_db";

		private static final String COLUMN_FOODREC_ID = "id";
		private static final String COLUMN_FOODREC_NAME = "name";
		private static final String COLUMN_FOODREC_SERVING_SIZE = "serving_size";
		private static final String COLUMN_FOODREC_CALORIES = "calories";
		private static final String COLUMN_FOODREC_CARBCARBOHYDRATE = "carbcarbohydrate";
		private static final String COLUMN_FOODREC_CHOLESTEROL = "cholesterol";
		private static final String COLUMN_FOODREC_FATS = "fats";
		private static final String COLUMN_FOODREC_FIBER = "fiber";
		private static final String COLUMN_FOODREC_PROTEIN = "protein";
		private static final String COLUMN_FOODREC_SODIUM = "sodium";
		private static final String COLUMN_FOODREC_SUGAR = "sugar";
		private static final String COLUMN_FOODREC_USDA_ID = "usda_id";
		private static final String COLUMN_FOODREC_ENTRY_TIME = "entry_time";

		
		private static final String CREATE_FOODREC_TABLE = 
		"CREATE TABLE " + TABLE_FOODRECORDS + "(" +
		COLUMN_FOODREC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
		COLUMN_FOODREC_NAME + " TEXT," +
		COLUMN_FOODREC_SERVING_SIZE + " TEXT," +
		COLUMN_FOODREC_CALORIES + " TEXT," + 
		COLUMN_FOODREC_CARBCARBOHYDRATE + " TEXT," +
		COLUMN_FOODREC_CHOLESTEROL + " TEXT," +
		COLUMN_FOODREC_FATS + " TEXT," +
		COLUMN_FOODREC_FIBER + " TEXT," +
		COLUMN_FOODREC_PROTEIN + " TEXT," +
		COLUMN_FOODREC_SODIUM + " TEXT," +
		COLUMN_FOODREC_SUGAR + " TEXT," +
		COLUMN_FOODREC_USDA_ID + " TEXT," +
		COLUMN_FOODREC_ENTRY_TIME + " TEXT" + ")";
		
		public FoodDB(Context context, String dbName) {
			super(context, dbName, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_FOODREC_TABLE);
//			db.execSQL(CREATE_FOODDB_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + CREATE_FOODREC_TABLE);
			onCreate(db);			
		}
	}
	
	public FoodDatabase open(String dbName) throws SQLException {
		handler = new FoodDB(ctx, dbName);
		db = handler.getWritableDatabase();
		return this;
	}
	
	public void close() {
		if (db != null && db.isOpen()) {
			db.close();
			handler.close();
		}
	}
	
	public void addToFoodRecord(Food food) {
		ContentValues values = new ContentValues();
		values.put(FoodDB.COLUMN_FOODREC_NAME, food.getName());
		values.put(FoodDB.COLUMN_FOODREC_SERVING_SIZE, food.getServing_size());		
		values.put(FoodDB.COLUMN_FOODREC_CALORIES, food.getCalories());		
		values.put(FoodDB.COLUMN_FOODREC_CARBCARBOHYDRATE, food.getCarbcarbohydrate());		
		values.put(FoodDB.COLUMN_FOODREC_CHOLESTEROL, food.getCholesterol());
		values.put(FoodDB.COLUMN_FOODREC_FATS, food.getFats());		
		values.put(FoodDB.COLUMN_FOODREC_FIBER, food.getFiber());		
		values.put(FoodDB.COLUMN_FOODREC_PROTEIN, food.getProtein());		
		values.put(FoodDB.COLUMN_FOODREC_SODIUM, food.getSodium());		
		values.put(FoodDB.COLUMN_FOODREC_SUGAR, food.getSugar());		
		values.put(FoodDB.COLUMN_FOODREC_USDA_ID, food.getUasdDBID());		
		values.put(FoodDB.COLUMN_FOODREC_ENTRY_TIME, food.getEatTime());
		db.insert(FoodDB.TABLE_FOODRECORDS, null, values);
	}
	
	public void updateFoodEntry(Food food) {
		ContentValues values = new ContentValues();
		values.put(FoodDB.COLUMN_FOODREC_NAME, food.getName());
		values.put(FoodDB.COLUMN_FOODREC_SERVING_SIZE, food.getServing_size());		
		values.put(FoodDB.COLUMN_FOODREC_CALORIES, food.getCalories());		
		values.put(FoodDB.COLUMN_FOODREC_CARBCARBOHYDRATE, food.getCarbcarbohydrate());		
		values.put(FoodDB.COLUMN_FOODREC_CHOLESTEROL, food.getCholesterol());
		values.put(FoodDB.COLUMN_FOODREC_FATS, food.getFats());		
		values.put(FoodDB.COLUMN_FOODREC_FIBER, food.getFiber());		
		values.put(FoodDB.COLUMN_FOODREC_PROTEIN, food.getProtein());		
		values.put(FoodDB.COLUMN_FOODREC_SODIUM, food.getSodium());		
		values.put(FoodDB.COLUMN_FOODREC_SUGAR, food.getSugar());		
		values.put(FoodDB.COLUMN_FOODREC_USDA_ID, food.getUasdDBID());
		
        db.update(FoodDB.TABLE_FOODRECORDS, values, FoodDB.COLUMN_FOODREC_ID + "=" + food.getEntryID(), null);
	}
	
	public Food getFood() {
		Food food = new Food();
		String selectQuery = "SELECT  * FROM " + FoodDB.TABLE_FOODRECORDS;
		Cursor cur = db.rawQuery(selectQuery, null);
		
		// TO BE IMPLEMENTED LATER IF USER WANT TO EDIT FOOD INPUT
		
		return food;
	}
}
