package com.timilehin.persistentstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.StringBuilderPrinter;

/**
 * Created by tijanioluwatimilehin on 8/15/17.
 */

public class MyDBHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoItems.db";
    public static final String TABLE_NAME = "todoItems";
    // Declaring the columns of the table
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHORENAME = "chore";

    // Constructor
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // First column
                        COLUMN_CHORENAME + " TEXT " +  // Second column
                        ");";

        sqLiteDatabase.execSQL(query);

    }

    // On upgrade to database model, delete the old table and create a new one.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    // Add new item to the database
    public void addItem(Items item)
    {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORENAME, item.get_choreName()); // Column name vs column date(item string)
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    // Delete an entry from the database
    public void deleteItem(String itemName) {

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CHORENAME + "=\"" + itemName
                        + "\";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    public String databaseToString()
    {

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        Cursor c = db.rawQuery(query, null); // Returns the contents of the database in a cursor.
        c.moveToFirst();

        while (!c.isAfterLast())
        {

            if (c.getString(c.getColumnIndex("chore")) != null) // get index of itemName col, then string content, then check.
            {

                dbString += c.getString(c.getColumnIndex("chore"));
                dbString += "\n";

            }

            c.moveToNext();

        }

        db.close();
        return dbString;

    }

}
