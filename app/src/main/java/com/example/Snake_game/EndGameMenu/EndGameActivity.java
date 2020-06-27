package com.example.Snake_game.EndGameMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Snake_game.DataSave.SQLite.PlayerDataSource;
import com.example.Snake_game.Menu.MenuActivity;
import com.example.zsuzsila_snake.R;

import java.util.*;

public class EndGameActivity extends AppCompatActivity {

    private PlayerDataSource datasource;
    private String playerName;
    private EditText playerName_text;
    private Button nextBtn;
    private TextView score_text;
    private int score_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        datasource = new PlayerDataSource(this);
        datasource.open();
        nextBtn = findViewById(R.id.nextBtn);
        playerName_text = findViewById(R.id.playerName);
        score_text = findViewById(R.id.scoreText);
        Intent scoreIntent = getIntent();
        score_number = scoreIntent.getIntExtra("ScoreValue",0);
        score_text.setText(Integer.toString(score_number));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = playerName_text.getText().toString();
                if(playerName.matches(""))
                {
                    playerName_text.setError("Hibás értéket adtál meg");
                }
                else if(playerName.length()>= 7)
                {
                    playerName_text.setError("Maximum 6 karakter hosszú nevet adhatsz meg");
                }
                else
                {
                    saveName(playerName_text.getText().toString());
                    goToMenu();
                }
            }
        });
    }
    private void saveName(String name)
    {
        this.playerName = name;
    }
    private void goToMenu()
    {
        savePlayer();
        Intent menuIntent = new Intent(this,MenuActivity.class);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(menuIntent);
        finish();
    }
    private void savePlayer()
    {
        Calendar cal = Calendar.getInstance();
        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH) + 1;
        int Day = cal.get(Calendar.DAY_OF_MONTH);
        String date_format = Year + "." + Month + "." + Day;
        datasource.createPlayer(playerName,score_number,date_format);
    }
    @Override
    protected void onResume()
    {
        datasource.open();
        super.onResume();
    }
    @Override
    protected void onPause()
    {
        datasource.close();
        super.onPause();
    }
}
