package com.example.Snake_game.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SnakeSection {
    private int X;
    private int Y;
    private Paint sectionColor;
    public SnakeSection(int x, int y)
    {
        X = x;
        Y = y;

    }
    public int  GetX()
    {
        return X;
    }
    public int GetY()
    {
        return Y;
    }
    public void SetX(int x)
    {
        X = x;
    }
    public void SetY(int y)
    {
        Y = y;
    }
    public void Draw(Canvas canvas,int ColorCode)
    {
        sectionColor = new Paint();
        sectionColor.setColor(ColorCode);
        canvas.drawCircle(X,Y,20,sectionColor);
    }
}
