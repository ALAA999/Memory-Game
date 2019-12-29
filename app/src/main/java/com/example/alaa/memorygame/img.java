package com.example.alaa.memorygame;

import android.widget.ImageView;

/**
 * Created by acer on 2/26/2018.
 */

public class img {
    boolean IsSelected;
    boolean IsOpend;
    int img_id;
    ImageView imageView;

    public img(boolean isSelected, boolean isOpend, int img_id) {
        IsSelected = isSelected;
        IsOpend = isOpend;
        this.img_id = img_id;
    }
    public img(boolean IsSelected , boolean IsOpend){
        this.IsOpend = IsOpend;
        this.IsSelected = IsSelected;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public boolean isSelected() {
        return IsSelected;
    }

    public void setSelected(boolean selected) {
        IsSelected = selected;
    }

    public boolean isOpend() {
        return IsOpend;
    }

    public void setOpend(boolean opend) {
        IsOpend = opend;
    }
}
