package org.muneebahmad.jlib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PwdDb {
	// --constants
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_SECRET = "secret";
	public static final String TAG = "PwdDb";

	private static final String DATABASE_NAME = "UserDB";
	private static final String DATABASE_TABLE = "user";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = "create table if not exists user(id integer primary key autoincrement, "
			+ " name VARCHAR, password VARCHAR, secret VARCHAR ) ; ";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase pDb;

	public PwdDb(Context ctx) {
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
	public PwdDb open() throws SQLException {
		pDb = DBHelper.getWritableDatabase();
		return this;
	}

	// -- closes database
	public void close() {
		DBHelper.close();
	}

	// -- insert a record into database
	public long insertRecord(String name, String pwd, String secret) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PASSWORD, pwd);
		initialValues.put(KEY_SECRET, secret);
		return pDb.insert(DATABASE_TABLE, null, initialValues);
	}

	// -- deletes a particular record
	public boolean deleteRecord(long rowId) {
		return pDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// -- retrieves all records
	public Cursor getAllRecords() {
		return pDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_PASSWORD, KEY_SECRET }, null, null, null, null, null);
	}

	// -- retrieves a particular record
	public Cursor getRecords(long rowId) {
		Cursor mCursor = pDb.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_SECRET }, KEY_ROWID
				+ "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateRecord(long rowId, String name, String pwd,
			String secret) {
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_PASSWORD, pwd);
		args.put(KEY_SECRET, secret);
		return pDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
