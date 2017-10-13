package com.example.samuel.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Samuel on 2017-10-10.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Messages.db";
    private static final int VERSION_NUM = 1;
    Context ctx;
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_MESSAGE ="message";
    public static final String TABLE_NAME = "name";

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE" + TABLE_NAME + "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MESSAGE +"text");
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        this.onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + "newVersion" + newVer);
    }
}
