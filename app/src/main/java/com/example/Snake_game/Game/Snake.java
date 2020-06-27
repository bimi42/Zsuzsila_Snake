package com.example.Snake_game.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakeSection> Snake = new LinkedList<>();
    private int headX;
    private int headY;;
    private int sc_width;
    private int sc_height;
    private int sc_width_dead;
    private int sc_height_dead;
    public void setDeadHeight(int height)
    {
        sc_height_dead = height;
    }
    public void setDeadWidth(int width)
    {
        sc_width_dead = width;
    }
    private Paint headColor;
    public void setHeight(int height)
    {
        sc_height = height;
    }
    public void setWidth(int width)
    {
        sc_width = width;
    }
    public Snake(int height,int width)
    {
        sc_height = height;
        sc_width = width;
        headX = 160;
        headY = 200;
        Snake.addFirst(new SnakeSection(headX,headY));
        headColor= new Paint();
        headColor.setColor(Color.YELLOW);
        Log.d("Értékek dead endhez", sc_height_dead+ " " + sc_width_dead);
    }
    public void addSection(int x,int y)
    {
        Snake.addFirst(new SnakeSection(x,y));
    }
    public boolean intersect(int x, int y)
    {
        if(x == headX && y == headY)
        {
            return true;
        }
        return false;
    }
    public void Move(int direction)
    {
        if(direction == 1)
        {
            headY-= 40;
        }
        else if(direction == 2)
        {
            headX+=40;
        }
        else if(direction == 3)
        {
            headX-=40;
        }
        else if(direction == 4)
        {
            headY+=40;
        }
        if(headY < 0)
            headY = sc_height - sc_height_dead;
        if(headY > sc_height)
            headY = 0;
        if(headX < 0)
            headX = sc_width - sc_width_dead;
        if(headX > sc_width)
            headX = 0;
            if(Snake.size()>0)
                Snake.removeLast();
            Snake.addFirst(new SnakeSection(headX,headY));
    }
    public int GetHeadX()
    {
        return headX;
    }
    public int GetHeadY()
    {
        return headY;
    }
    public void Draw(Canvas canvas)
    {
        for(int i = 0; i < Snake.size();i++)
        {
            if(i == 0)
            {
                Snake.get(i).Draw(canvas,Color.YELLOW);
            }
            else
            {
                Snake.get(i).Draw(canvas,Color.RED);
            }
        }
    }
    public int getSectionX(int index)
    {
        return Snake.get(index).GetX();
    }
    public int getSectionY(int index)
    {
        return Snake.get(index).GetY();
    }
    public int getLength()
    {
        return Snake.size();
    }
}
