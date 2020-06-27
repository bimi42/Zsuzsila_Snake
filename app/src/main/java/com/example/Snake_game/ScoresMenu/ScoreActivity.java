package com.example.Snake_game.ScoresMenu;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Snake_game.DataSave.Player;
import com.example.Snake_game.DataSave.SQLite.PlayerDataSource;
import com.example.zsuzsila_snake.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private PlayerDataSource datasource;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.layout_enter_animation);
        final LinearLayout ll = findViewById(R.id.scores_layout);
        ll.startAnimation(anim);
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        datasource = new PlayerDataSource(this);
        datasource.open();
        List<Player> players = datasource.fetchAll();
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p2.getScore()-p1.getScore();
            }
        });
        mAdapter = new ScoreAdapter(players);
        recyclerView.setAdapter(mAdapter);
    }
}
