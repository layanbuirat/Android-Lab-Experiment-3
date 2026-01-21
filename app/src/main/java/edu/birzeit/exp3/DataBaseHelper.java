package edu.birzeit.exp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL("CREATE TABLE CUSTOMER(ID LONG PRIMARY KEY, NAME TEXT, PHONE TEXT, GENDER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {
    }

    public void insertCustomer(Customer customer) {
        SQLiteDatabase sqliteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", customer.getmCustomerId());
        contentValues.put("NAME", customer.getmName());
        contentValues.put("PHONE", customer.getmPhone());
        contentValues.put("GENDER", customer.getmGender());
        sqliteDatabase.insert("CUSTOMER", null, contentValues);
    }

    public Cursor getAllCustomers() {
        SQLiteDatabase sqliteDatabase = getReadableDatabase();
        return sqliteDatabase.rawQuery("SELECT * FROM CUSTOMER", null);
    }
}