package com.example.Snake_game.DataSave;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PlayerTable {
    public static final String TABLE_PLAYER = "player";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_CREATE = "create table " +
            TABLE_PLAYER
            +
            "("
            +COLUMN_ID +
            " integer primary key autoincrement, "
            + COLUMN_NAME +
            " text not null, " +
            COLUMN_SCORE +
            " integer not null, "
            + COLUMN_DATE +
            " text not null );";


    public static void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(database);
    }
}
