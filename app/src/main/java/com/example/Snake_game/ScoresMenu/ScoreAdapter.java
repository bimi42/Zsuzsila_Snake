package com.example.Snake_game.ScoresMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.Snake_game.DataSave.Player;
import com.example.zsuzsila_snake.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder> {
    private List<Player> players = new ArrayList<Player>();
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView position;
        public TextView name;
        public TextView score;
        public TextView date;
        public MyViewHolder(View v) {
            super(v);
            position = v.findViewById(R.id.position_score);
            name = v.findViewById(R.id.player_Name);
            score = v.findViewById(R.id.player_Score);
            date = v.findViewById(R.id.Date_score);
        }
    }
    public ScoreAdapter(List<Player> list) {
        players = list;
    }
    @Override
    public ScoreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scores, parent, false);
        MyViewHolder vh = new MyViewHolder(row);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.position.setText(position+1+".");
        holder.name.setText(players.get(position).getName());
        holder.score.setText(players.get(position).getScore()+"");
        holder.date.setText(players.get(position).getDate());
    }
    @Override
    public int getItemCount() {
        return players.size();
    }
}
