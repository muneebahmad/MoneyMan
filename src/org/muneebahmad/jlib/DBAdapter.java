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

public class DBAdapter {
	// --constants
	public static final String KEY_ROWID = "id";
	public static final String KEY_DATE = "date";
	public static final String KEY_DAY = "day";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "desc";
	public static final String KEY_TRANSACTION = "trans";
	public static final String KEY_TAGS = "tags";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_CURRENCY = "currency";
	public static final String KEY_AMOUNT = "amount";
	public static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "MoneyManDB";
	private static final String DATABASE_TABLE = "persons";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = "create table if not exists persons(id integer primary key autoincrement, "
			+ " date date, day VARCHAR, name VARCHAR, desc VARCHAR,  trans VARCHAR, tags VARCHAR, category VARCHAR, currency VARCHAR, amount VARCHAR ) ; ";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
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
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// -- closes database
	public void close() {
		DBHelper.close();
	}

	// -- insert a record into database
	public long insertRecord(String date, String day, String name, String desc,
			String trans, String tags, String category, String currency,
			String amount) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_DAY, day);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_DESCRIPTION, desc);
		initialValues.put(KEY_TRANSACTION, trans);
		initialValues.put(KEY_TAGS, tags);
		initialValues.put(KEY_CATEGORY, category);
		initialValues.put(KEY_CURRENCY, currency);
		initialValues.put(KEY_AMOUNT, amount);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// -- deletes a particular record
	public boolean deleteRecord(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// -- retrieves all records
	public Cursor getAllRecords() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_DATE,
				KEY_DAY, KEY_NAME, KEY_DESCRIPTION, KEY_TRANSACTION, KEY_TAGS,
				KEY_CATEGORY, KEY_CURRENCY, KEY_AMOUNT }, null, null, null,
				null, null);
	}

	// -- retrieves a particular record
	public Cursor getRecords(long rowId) {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_DATE, KEY_DAY, KEY_NAME, KEY_DESCRIPTION,
				KEY_TRANSACTION, KEY_TAGS, KEY_CATEGORY, KEY_CURRENCY,
				KEY_AMOUNT }, KEY_ROWID + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// -- retrieving budget record
	public Cursor getTransRecord(String transaction) {
		return db.query(true, DATABASE_TABLE,
				new String[] { KEY_AMOUNT }, KEY_TRANSACTION + "=?",
				new String[] { transaction }, null, null, null, null);
	}

	public boolean updateRecord(long rowId, String date, String day,
			String name, String desc, String trans, String tags,
			String category, String currency, String amount) {
		ContentValues args = new ContentValues();
		args.put(KEY_DATE, date);
		args.put(KEY_DAY, day);
		args.put(KEY_NAME, name);
		args.put(KEY_DESCRIPTION, desc);
		args.put(KEY_TRANSACTION, trans);
		args.put(KEY_TAGS, tags);
		args.put(KEY_CATEGORY, category);
		args.put(KEY_CURRENCY, currency);
		args.put(KEY_AMOUNT, amount);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}// end class
