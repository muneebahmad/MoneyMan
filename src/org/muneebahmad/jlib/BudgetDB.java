package org.muneebahmad.jlib;

/*
 * @author:MuneebAhmad
 * */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Browser.SearchColumns;
import android.util.Log;

public class BudgetDB {
	// --constants
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_CURRENCY = "currency";
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_TIMELINE = "timeline";
	public static final String TAG = "BudgetDB";

	private static final String DATABASE_NAME = "BudgetDB";
	private static final String DATABASE_TABLE = "budget";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = "create table if not exists budget(id integer primary key autoincrement, "
			+ " name VARCHAR,  currency VARCHAR, amount VARCHAR, timeline VARCHAR ) ; ";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public BudgetDB(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}// end inner class

	// -- opens the database
	public BudgetDB open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// -- closes database
	public void close() {
		DBHelper.close();
	}

	// -- insert a record into database
	public long insertRecord(String name, String currency, String amount,
			String timeLine) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_CURRENCY, currency);
		initialValues.put(KEY_AMOUNT, amount);
		initialValues.put(KEY_TIMELINE, timeLine);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// -- deletes a particular record
	public boolean deleteRecord(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// -- retrieves all records
	public Cursor getAllRecords() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_CURRENCY, KEY_AMOUNT, KEY_TIMELINE }, null, null, null,
				null, null);
	}

	// -- retrieves a particular record
	public Cursor getRecords(long rowId) {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_CURRENCY, KEY_AMOUNT, KEY_TIMELINE },
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// -- retrieving budget record
	public Cursor getNameRecord(String name) {
		return db.query(true, DATABASE_TABLE, new String[] { KEY_AMOUNT },
				KEY_NAME + "=?", new String[] { name }, null, null, null, null);
	}

	public boolean updateRecord(long rowId, String name, String currency,
			String amount, String timeLine) {
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_CURRENCY, currency);
		args.put(KEY_AMOUNT, amount);
		args.put(KEY_TIMELINE, timeLine);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}// end class
