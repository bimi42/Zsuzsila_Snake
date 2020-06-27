package com.example.Snake_game.Game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Snake_game.EndGameMenu.EndGameActivity;
import com.example.Snake_game.GestureDetect.SwipeListener;
import com.example.zsuzsila_snake.R;

import java.util.Timer;
import java.util.TimerTask;

public class Snake_game extends AppCompatActivity {
    private int lastDirection;
    private Snake_view game;
    private int timer_counter = 0;
    private Timer timer;
    private TimerTask tt;
    LinearLayout gl;
    private PointFragment fg;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onPause()
    {
        super.onPause();
        timer.cancel();
        tt.cancel();
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Menü kilépés animáció, játék belépés animáció
        //direkt van ugyanaz az animáció mindkét helyen, így jobban néz ki
        overridePendingTransition( R.anim.layout_enter_animation, R.anim.layout_enter_animation);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_game);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
        );
        gl = findViewById(R.id.game_layout);
        game = new Snake_view(this);
        game.setDirection(1);
        game.setOnTouchListener(new SwipeListener(Snake_game.this)
        {
            public void onSwipeTop() {
                if(lastDirection!= 4)
                {
                    game.setDirection(1);
                    lastDirection = 1;
                }
            }
            public void onSwipeRight()
            {
                if(lastDirection!= 3)
                {
                    game.setDirection(2);
                    lastDirection = 2;
                }



            }
            public void onSwipeLeft()
            {
                if(lastDirection!= 2)
                {
                    game.setDirection(3);
                    lastDirection = 3;
                }


            }
            public void onSwipeBottom()
            {
                if(lastDirection!= 1)
                {
                    game.setDirection(4);
                    lastDirection = 4;
                }



            }
        });
        fg = (PointFragment) getSupportFragmentManager().findFragmentByTag("ScoreFragment");
        timer = new Timer();
        runOnUiThread(tt = new TimerTask() {
            @Override
            public void run() {
                game.postInvalidate();
                game.moveSnake();
                if(game.isCollide())
                {
                    gameOver();
                }
                if(game.getBoost())
                {
                    boost();
                }
                //csak a kreált viewvből férek hozzá, onnan tudok módosítani.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fg.setText(game.getPoints()+"");
                    }
                });
            }
        });
        gl.addView(game);
        timer.scheduleAtFixedRate(tt,100,100);
    }
    private void boost()
    {
        timer_counter = 0;
        timer.cancel();
        timer = new Timer();
        tt = new TimerTask() {
            @Override
            public void run() {
                game.postInvalidate();
                game.moveSnake();
                if(game.isCollide())
                {
                    gameOver();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fg.setText(game.getPoints()+"");
                    }
                });
                timer_counter++;
                if(timer_counter == 50)
                {
                    timer.cancel();
                    tt.cancel();
                    game.setBoost(false);
                    timer = new Timer();
                    tt = new TimerTask() {
                        @Override
                        public void run() {
                            game.postInvalidate();
                            game.moveSnake();
                            if(game.isCollide())
                            {
                                gameOver();
                            }
                            if(game.getBoost())
                            {
                                boost();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fg.setText(game.getPoints()+"");
                                }
                            });
                        }
                    };
                    timer.scheduleAtFixedRate(tt,100,100);
            }
        }
        };
        timer.scheduleAtFixedRate(tt,10,10);
    }
    private void gameOver()
    {
        tt.cancel();
        timer.cancel();
        Intent endGameIntent = new Intent(this, EndGameActivity.class);
        endGameIntent.putExtra("ScoreValue",game.getPoints());
        startActivity(endGameIntent);
    }
}
