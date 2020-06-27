package com.example.Snake_game.DataSave;

import java.util.Date;

public class Player {
    private long id;
    private String name;
    private int score;
    private String scoreDate;

    public long getId()
    {
        return id;
    }
    public void setId(long new_id)
    {
        id = new_id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String new_name)
    {
        name = new_name;
    }
    public int getScore()
    {
        return score;
    }
    public void setScore(int new_score)
    {
        score = new_score;
    }
    public String getDate()
    {
        return scoreDate;
    }
    public void setDate(String new_date)
    {
        scoreDate = new_date;
    }
    @Override
    public String toString()
    {
            return name+" "+score+" "+scoreDate.toString();
    }
}
