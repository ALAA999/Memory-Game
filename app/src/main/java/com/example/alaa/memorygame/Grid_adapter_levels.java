package com.example.alaa.memorygame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by acer on 2/19/2018.
 */

public class Grid_adapter_levels extends RecyclerView.Adapter<Grid_adapter_levels.RecyclerViewHolder> {
    List<level> list;
    Context context;

    public Grid_adapter_levels(List<level> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Grid_adapter_levels.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.grid_levels_item, parent, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final Grid_adapter_levels.RecyclerViewHolder holder, final int position) {
        final level item = list.get(position);
        Log.e("position", position + "");
        try {
            int prevous_num = list.get(position - 1).getIs_ans();
            //if (prevous_num == 1 || prevous_num == 2 || prevous_num == 3) {
                holder.level.setText(item.getId() + "");
                holder.level_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, question_activity.class);
                        int id = item.getId();
                        intent.putExtra("question_id", id);
                        context.startActivity(intent);
                    }
                });
            //} else {
             //   holder.level.setBackgroundResource(R.drawable.ic_lock_outline_black_24dp);
           // }
        } catch (Exception e) {//it will work only for the first level first time
            holder.level.setText(item.getId() + "");
            Log.e("error", item.getId() + "");
            holder.level_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, question_activity.class);
                    int id = item.getId();
                    intent.putExtra("question_id", id);
                    context.startActivity(intent);
                }
            });

        }
        if (item.is_ans == 1) {
            holder.star1.setBackgroundResource(R.drawable.ic_star_filled_24dp);
        }
        if (item.is_ans == 2) {
            holder.star1.setBackgroundResource(R.drawable.ic_star_filled_24dp);
            holder.star2.setBackgroundResource(R.drawable.ic_star_filled_24dp);
        }
        if (item.is_ans == 3) {
            holder.star1.setBackgroundResource(R.drawable.ic_star_filled_24dp);
            holder.star2.setBackgroundResource(R.drawable.ic_star_filled_24dp);
            holder.star3.setBackgroundResource(R.drawable.ic_star_filled_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        CardView level_card;
        TextView level;
        ImageView star1, star2, star3;

        public RecyclerViewHolder(View view) {
            super(view);
            level_card = itemView.findViewById(R.id.level_card);
            level = itemView.findViewById(R.id.level);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
        }
    }
}
