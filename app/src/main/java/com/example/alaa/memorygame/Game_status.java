package com.example.alaa.memorygame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alaa.memorygame.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game_status extends Fragment {


    public Game_status() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_status, container, false);
        Bundle bundle = this.getArguments();
        boolean win = bundle.getBoolean("win", false);
        if (win) {
            view.findViewById(R.id.win_img).setBackgroundResource(R.drawable.win);
        } else {
            view.findViewById(R.id.win_img).setBackgroundResource(R.drawable.lose);
        }
        return view;
    }

}
