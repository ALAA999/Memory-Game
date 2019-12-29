package com.example.alaa.memorygame;

/**
 * Created by acer on 2/25/2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by acer on 2/19/2018.
 */

public class Grid_Adapter_question extends RecyclerView.Adapter<Grid_Adapter_question.RecyclerViewHolder> {
    List<img> list;
    Context context;

    public Grid_Adapter_question(List<img> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Grid_Adapter_question.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.question_item, parent, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final img item = list.get(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//we need to save the number of cards opend mabey 1 or 2 , the id of the card , the position of the first card
//                Log.e("selected", item.isSelected() + "");
                holder.imageView.setImageResource(item.getImg_id());
                if (!item.IsSelected) {
                    if (question_fragment.card_reached == 0) {
                        first_click(item, position, holder);
                    } else if (question_fragment.card_reached == 1) {
                        //secound image make sure it is not car 1
                        secound_click(item, position, holder);
                    } else if (question_fragment.card_reached == 2) {
                        //user clicked 3rd card sp fast
                        question_fragment.card1.getImageView().setImageResource(R.drawable.a);
                        question_fragment.card2.getImageView().setImageResource(R.drawable.a);
                        question_fragment.countDownTimer.cancel();
                        first_click(item, position, holder);
                    }
                }
            }
        });
    }

    public void secound_click(img item, int position, RecyclerViewHolder holder){
        question_fragment.card2.setImg_id(item.getImg_id());
        question_fragment.card2.setImageView(holder.imageView);
        question_fragment.secound_card_postion = position;
        setSelected(position, true);
        question_fragment.card_reached = 2;
        check_cards();
    }

    public void first_click(img item, int position, RecyclerViewHolder holder) {
        question_fragment.card1.setImg_id(item.getImg_id());
        question_fragment.card1.setImageView(holder.imageView);
        question_fragment.first_card_postion = position;
        setSelected(position, true);
        question_fragment.card_reached = 1;
    }

    public void setSelected(int position, boolean selected){
        list.get(position).setSelected(selected);
    }

    public int get_score(int time_spent){
        int scoore = 0;
        if (time_spent >= 18) {
            scoore = 3;
        } else if (time_spent >= 12 && time_spent < 18) {
            scoore = 2;
        } else if (time_spent >= 1) {
            scoore = 1;
        }
        return scoore;
    }

    public void check_cards() {
        if (question_fragment.card1.getImg_id() == question_fragment.card2.getImg_id()) {
            question_fragment.card_reached = 0;
            setSelected(question_fragment.first_card_postion, true);
            setSelected(question_fragment.secound_card_postion, true);
            question_fragment.level_score = question_fragment.level_score + 2;
            if (question_fragment.level_score == list.size()) {//End this game
                question_fragment.ended = true;
                int time_spent = Integer.parseInt(question_fragment.timer.getText().toString());
                DatabaseHelper helper = new DatabaseHelper(context);
                int scoore = get_score(time_spent);//To check if he got a higher score
                if (MainActivity.levels.get(question_fragment.id - 1).getIs_ans() < scoore) {
                    helper.change_level_scoore(scoore, question_fragment.id);
                    MainActivity.levels.get(question_fragment.id - 1).setIs_ans(scoore);
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("win", true);
                Game_status fragment1 = new Game_status();
                fragment1.setArguments(bundle);
                FragmentUtil.replaceFragment(context, fragment1, R.id.question_act);
            }
        } else {//Wrong Answer
            setSelected(question_fragment.first_card_postion, false);
            setSelected(question_fragment.secound_card_postion, false);
            question_fragment.first_card_postion = 0;
            question_fragment.secound_card_postion = 0;
            start_timer();
        }
    }

    public void start_timer() {
        question_fragment.countDownTimer = new CountDownTimer(550, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                question_fragment.card_reached = 0;
                question_fragment.card1.getImageView().setImageResource(R.drawable.a);
                question_fragment.card2.getImageView().setImageResource(R.drawable.a);
            }
        }.start();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public RecyclerViewHolder(View view) {
            super(view);
            imageView = itemView.findViewById(R.id.question_img);
        }
    }
}