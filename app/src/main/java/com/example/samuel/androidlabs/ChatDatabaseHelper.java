package com.example.samuel.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Samuel on 2017-10-13.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Messages.db";
    public static final int VERSION_NUM = 1;

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
}
