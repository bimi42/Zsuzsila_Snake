package com.example.Snake_game.Menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Snake_game.Game.Snake_game;
import com.example.Snake_game.ScoresMenu.ScoreActivity;
import com.example.zsuzsila_snake.R;

public class MenuActivity extends AppCompatActivity {
    private Button startBtn;
    private Button scoreBtn;
    private Button exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        startBtn = findViewById(R.id.startButton);
        scoreBtn = findViewById(R.id.scoreButton);
        exitBtn = findViewById(R.id.exitButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openGame();
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                exitGame();
            }
        });
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScores();
            }
        });
    }
    private void openGame()
    {
        Intent game = new Intent(this, Snake_game.class);
        startActivity(game);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void exitGame()
    {
        finishAndRemoveTask();
    }
    private void openScores()
    {
        Intent scores = new Intent(this, ScoreActivity.class);
        startActivity(scores);
    }
}
