package com.example.Snake_game.DataSave.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.Snake_game.DataSave.PlayerTable;

public class PlayerDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "playertable.db";
    private static final int DATABASE_VERSION = 3;

    public PlayerDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        PlayerTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        PlayerTable.onUpgrade(sqLiteDatabase,i,i1);
    }
}
