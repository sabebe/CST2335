package com.example.samuel.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Samuel on 2017-10-13.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "name ";
    public static final String KEY_ID = "id ";
    public static final String KEY_MESSAGE = "message ";
    public static final String DATABASE_NAME = "Messages.db ";

    public static final int VERSION_NUM = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "( "
            + KEY_ID
            + "integer primary key autoincrement, "
            + KEY_MESSAGE
            + "text not null); ";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ChatDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion =" + oldVersion + "newVersion =" + newVersion);
    }
}