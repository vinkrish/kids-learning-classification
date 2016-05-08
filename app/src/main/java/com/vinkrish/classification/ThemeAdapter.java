package com.vinkrish.classification;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vinkrish on 08/05/16.
 */
public class ThemeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ThemeItem> data = new ArrayList<>();
    private LayoutInflater inflater;

    public ThemeAdapter(Context context, ArrayList<ThemeItem> gridArray) {
        this.context = context;
        this.data = gridArray;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        RecordHolder holder;

        if (row == null) {
            row = inflater.inflate(R.layout.theme_grid_item, viewGroup, false);
            holder = new RecordHolder();
            holder.themeBackground = (RelativeLayout) row.findViewById(R.id.theme_background);
            holder.themeImage = (ImageView) row.findViewById(R.id.theme_image);
            row.setTag(holder);
        } else holder = (RecordHolder) row.getTag();

        ThemeItem themeItem = data.get(position);
        if (themeItem.isSelected()) {
            holder.themeBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.green_theme_selection));
        } else {
            holder.themeBackground.setBackgroundColor(ContextCompat.getColor(context,android.R.color.white));
        }
        holder.themeImage.setImageBitmap(themeItem.getBitmap());

        return row;
    }

    public static class RecordHolder {
        public RelativeLayout themeBackground;
        public ImageView themeImage;
    }
}
