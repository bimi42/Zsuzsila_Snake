package com.example.Snake_game.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    private int height;
    private int width;
    private Paint food_color;
    private Random r_number;
    private int type;
    private List<Integer> x_coordinate;
    private List<Integer> y_coordinate;
    public void setHeight(int height)
    {
        this.height = height;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public Food(int sc_height,int sc_width)
    {
        x_coordinate = new ArrayList<Integer>();
        y_coordinate = new ArrayList<Integer>();
        food_color = new Paint();
        food_color.setColor(Color.RED);
        height = sc_height;
        width = sc_width;
    }
    public void initCoordinates()
    {
        for(int i = 0; i < width;i++)
        {
            if(i % 40 == 0 && i != 0)
            {
                x_coordinate.add(i);
            }
        }
        for(int i = 0;i < height;i++)
        {
            if(i % 40 == 0 && i!= 0)
            {
                y_coordinate.add(i);
            }
        }
    }
    public void generateFood(Snake snake)
    {
        int random_help_x = 0;
        int random_help_y = 0;
        boolean ok = true;
        r_number = new Random();
        while(true)
        {
            ok = true;
            random_help_x = r_number.nextInt(x_coordinate.size());
            random_help_y = r_number.nextInt(y_coordinate.size());
            for (int i = 0; i < snake.getLength(); i++) {
                if (x_coordinate.get(random_help_x) == snake.getSectionX(i) && y_coordinate.get(random_help_y) == snake.getSectionY(i)) {
                    ok = false;
                }
            }
            if(ok)
            {
                break;
            }
        }
        y = y_coordinate.get(random_help_y);
        x = x_coordinate.get(random_help_x);
        type = r_number.nextInt(3);
        if(type == 0)
        {
            food_color.setColor(Color.RED);
        }
        else if(type == 1)
        {
            food_color.setColor(Color.BLUE);
        }
        else if(type == 2)
        {
            food_color.setColor(Color.YELLOW);
        }
    }
    public int getType()
    {
        return type;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void Draw(Canvas canvas)
    {
        canvas.drawCircle(x,y,10,food_color);
    }
}
