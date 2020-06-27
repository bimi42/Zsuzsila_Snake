package com.example.Snake_game.Game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.zsuzsila_snake.R;

public class PointFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsttanceBundle)
    {
        View root = View.inflate(getActivity(), R.layout.score_fragment,null);
        return  root;
    }
    public void setText(String text){
        TextView textView = getView().findViewById(R.id.scores_text);
        textView.setText(text);
    }
}
