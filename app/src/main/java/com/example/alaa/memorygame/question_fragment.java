package com.example.alaa.memorygame;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class question_fragment extends Fragment {

    static TextView timer;
    static CountDownTimer countDownTimer;
    static int first_card_postion, secound_card_postion, id , card_reached, level_score;
    static boolean ended;
    static img card1, card2;
    int time;
    Grid_Adapter_question grid_adapter_question;
    RecyclerView recyclerView;
    ArrayList<img> question_imgs_arraylist;

    public question_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_fragment, container, false);
        Bundle bundle = this.getArguments();
        id = bundle.getInt("id");
        ((TextView)view.findViewById(R.id.game_title)).setText("لغز رقم "+id);
        level l = MainActivity.levels.get(id - 1);
        clear_parameters(l);
        view.findViewById(R.id.back_button).setVisibility(View.VISIBLE);
        view.findViewById(R.id.back_button).setEnabled(true);
        view.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ended = true;
                getActivity().finish();
            }
        });
        timer = view.findViewById(R.id.timer);
        timer.setVisibility(View.VISIBLE);
        timer.setText(time + "");
        question_imgs_arraylist = new ArrayList<>();
        Collections.shuffle(MainActivity.imgs);
        for (int i = 0; i < (l.getrow_num() * l.getColum_num()) / 2; i++) {
            question_imgs_arraylist.add(new img(false, false, MainActivity.imgs.get(i)));
            question_imgs_arraylist.add(new img(false, false, MainActivity.imgs.get(i)));
        }
        card1 = new img(false, false);
        card2 = new img(false, false);
        Collections.shuffle(question_imgs_arraylist);
        recyclerView = view.findViewById(R.id.question_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), l.getColum_num()));
        grid_adapter_question = new Grid_Adapter_question(question_imgs_arraylist, getActivity());
        recyclerView.setAdapter(grid_adapter_question);
        timer();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    ended = true;
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void timer() {
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                --time;
                timer.setText(time + "");
                if (time > 20) {
                    timer.setTextColor(Color.BLUE);
                } else if (time >= 10 && time <= 20) {
                    timer.setTextColor(Color.YELLOW);
                } else {
                    timer.setTextColor(Color.RED);
                }
                if (time == 0) {
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("win", false);
                        Game_status fragment1 = new Game_status();
                        fragment1.setArguments(bundle);
                        FragmentUtil.replaceFragment(getActivity(), fragment1, R.id.question_act);
                    } catch (Exception e) {

                    }
                } else {
                    if (!ended) {
                        timer();
                    }
                }
            }
        }.start();
    }

    public void clear_parameters(level l) {
        switch (l.getColum_num()) {
            case 2:
                time = 25;
                break;
            case 3:
                time = 35;
                break;
            case 4:
                time = 45;
                break;
        }
        first_card_postion = 0;
        secound_card_postion = 0;
        card1 = null;
        card2 = null;
        ended = false;
        card_reached = 0;
        level_score = 0;
    }
}
