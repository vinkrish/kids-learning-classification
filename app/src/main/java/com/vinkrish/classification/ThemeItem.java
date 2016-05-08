package com.vinkrish.classification;

import android.graphics.Bitmap;

/**
 * Created by vinkrish on 08/05/16.
 */
public class ThemeItem {
    private boolean isSelected;
    private Bitmap bitmap;

    public ThemeItem (Bitmap bitmap, boolean isSelected) {
        this.bitmap = bitmap;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
