package com.example.Snake_game.Game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Random;

public class Snake_view extends View {
    private int direction;
    private boolean collide = false;
    private int points = 0;
    Random r = new Random();
    public void moveSnake()
    {
        if(snake.intersect(food.getX(),food.getY()))
        {
            points++;
            if(food.getType() == 0)
            {
                snake.addSection(food.getX(),food.getY());
            }
            else if(food.getType()==1)
            {
                snake.addSection(food.getX(),food.getY());
                snake.addSection(food.getX(),food.getY());
            }
            else if(food.getType()==2)
            {
                boost = true;
            }
            food.generateFood(snake);
        }
        else if(collideSnake())
        {
            collide = true;
        }
        snake.Move(direction);
    }
    public boolean isCollide()
    {
        return collide;
    }

    public int getPoints() {
        return points;
    }

    private Food food;
    private int height;
    private int width;
    private DisplayMetrics dm = new DisplayMetrics();
    private Snake snake;
    private Paint paint;
    private boolean boost;
    public void setBoost(boolean value)
    {
        boost = value;
    }
    public boolean getBoost()
    {
        return boost;
    }
    public Snake_view(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //Mivel még nem tudom mekkora az ablak (azt csak a view megléte után tudom lekérdezni
        //viszont rajzolás előtt már lehet rá szükség, ezért a telefon képernyőjének felét veszem alapúl
        height = dm.heightPixels;
        width = dm.widthPixels;
        snake = new Snake(height,width);
        food = new Food(height/2,width/2);

    }
    public void setDirection(int i)
    {
        direction = i;
    }
    private boolean first = true;
    @Override
    protected void onDraw(Canvas canvas) {
        if(first)
        {
            initAfterView();
            first = false;
        }
        canvas.drawColor(Color.BLACK);
        snake.Draw(canvas);
        food.Draw(canvas);
    }
    private void initAfterView()
    {
        //Itt tudom először lekérni, ezért át kellett rakni sok hívás sorrendjét
        height = getHeight();
        width = getWidth();
        snake.setHeight(height);
        snake.setWidth(width);
        food.setHeight(height);
        food.setWidth(width);
        food.initCoordinates();
        food.generateFood(snake);
        snake.setDeadHeight(height % 40);
        snake.setDeadWidth(width % 40);
    }
    private boolean collideSnake()
    {
        for(int i = 0; i < snake.getLength();i++)
        {
            if(i!= 0 && snake.getSectionX(i) == snake.GetHeadX() && snake.getSectionY(i) == snake.GetHeadY())
                return true;
        }
        return false;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
}
