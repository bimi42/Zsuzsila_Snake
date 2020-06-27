package com.example.Snake_game.DataSave.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.Snake_game.DataSave.Player;
import com.example.Snake_game.DataSave.PlayerTable;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataSource {

    private SQLiteDatabase database;
    private PlayerDBHelper dbHelper;

    private String [] allColumns = {PlayerTable.COLUMN_ID,PlayerTable.COLUMN_NAME,PlayerTable.COLUMN_SCORE,PlayerTable.COLUMN_DATE};

    public PlayerDataSource(Context context)
    {
        dbHelper = new PlayerDBHelper(context);
    }
    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }
    public void close()
    {
        dbHelper.close();
    }
    public Player createPlayer(String aName, int aScore, String aDate)
    {
        ContentValues values = new ContentValues();
        values.put(PlayerTable.COLUMN_NAME,aName);
        values.put(PlayerTable.COLUMN_DATE,aDate);
        values.put(PlayerTable.COLUMN_SCORE,aScore);
        long insertId = database.insert(PlayerTable.TABLE_PLAYER, null, values);
        Cursor cursor = database.query(PlayerTable.TABLE_PLAYER, allColumns, PlayerTable.COLUMN_ID + "= "+insertId,null,null,null,null);
        cursor.moveToFirst();
        Player newPlayer = cursorToPlayer(cursor);
        cursor.close();
        return newPlayer;
    }
    private Player cursorToPlayer(Cursor cursor)
    {
        Player player = new Player();
        player.setId(cursor.getLong(0));
        player.setName(cursor.getString(1));
        player.setScore(cursor.getInt(2));
        player.setDate(cursor.getString(3));
        return player;
    }
    public void deletePlayer(Player player)
    {
        long id = player.getId();
        database.delete(PlayerTable.TABLE_PLAYER, PlayerTable.COLUMN_ID + "= " + id,null);
    }
    public List<Player> fetchAll()
    {
        List<Player> players = new ArrayList<Player>();
        Cursor cursor  = database.query(PlayerTable.TABLE_PLAYER,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Player player = cursorToPlayer(cursor);
            players.add(player);
            cursor.moveToNext();
        }
        cursor.close();
        return players;
    }
}
