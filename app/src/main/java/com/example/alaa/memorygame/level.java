package com.example.alaa.memorygame;

/**
 * Created by acer on 2/24/2018.
 */

public class level {
    int id;
    int is_ans;
    int colum_num;
    int row_num;

    public level(int id, int is_ans, int colum_num, int row_num) {
        this.id = id;
        this.is_ans = is_ans;
        this.colum_num = colum_num;
        this.row_num = row_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_ans() {
        return is_ans;
    }

    public void setIs_ans(int is_ans) {
        this.is_ans = is_ans;
    }

    public int getColum_num() {
        return colum_num;
    }

    public void setColum_num(int colum_num) {
        this.colum_num = colum_num;
    }

    public int getrow_num() {
        return row_num;
    }

    public void setrow_num(int row_num) {
        this.row_num = row_num;
    }
}
