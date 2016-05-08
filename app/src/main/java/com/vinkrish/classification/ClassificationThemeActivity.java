package com.vinkrish.classification;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.vinkrish.Utils.GridViewInScroll;

import java.util.ArrayList;

public class ClassificationThemeActivity extends AppCompatActivity {
    private GridViewInScroll themeGrid, backgroundGrid, movementGrid, frameGrid, categoryGrid;
    private ThemeAdapter themeAdapter, backgroundAdapter, movementAdapter, frameAdapter, categoryAdapter;
    private ArrayList<ThemeItem> themeList = new ArrayList<>();
    private ArrayList<ThemeItem> backgroundList = new ArrayList<>();
    private ArrayList<ThemeItem> movementList = new ArrayList<>();
    private ArrayList<ThemeItem> frameList = new ArrayList<>();
    private ArrayList<ThemeItem> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification_theme);

        initThemeBitmap();
        initBackgroundBitmap();
        initMovementBitmap();
        initFrameBitmap();
        initcontainerBitmap();

    }

    private void initThemeBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background1, options), true));
        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background2, options), false));
        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background1, options), false));

        themeGrid = (GridViewInScroll) findViewById(R.id.themeGrid);
        themeAdapter = new ThemeAdapter(getApplicationContext(), themeList);
        themeGrid.setAdapter(themeAdapter);

        themeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                resetThemeSelection();
                ThemeItem themeItem = themeList.get(position);
                themeItem.setSelected(true);
                themeList.set(position, themeItem);
                themeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetThemeSelection() {
        for (ThemeItem ti : themeList) {
            ti.setSelected(false);
        }
    }

    private void initBackgroundBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        backgroundList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background1, options), true));
        backgroundList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background2, options), false));
        backgroundList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.background1, options), false));

        backgroundGrid = (GridViewInScroll) findViewById(R.id.backgroundGrid);
        backgroundAdapter = new ThemeAdapter(getApplicationContext(), backgroundList);
        backgroundGrid.setAdapter(backgroundAdapter);

        backgroundGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                resetBackgroundSelection();
                ThemeItem themeItem = backgroundList.get(position);
                themeItem.setSelected(true);
                backgroundList.set(position, themeItem);
                backgroundAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetBackgroundSelection() {
        for (ThemeItem ti : backgroundList) {
            ti.setSelected(false);
        }
    }

    private void initMovementBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 8;
        movementList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.path1, options), true));
        movementList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.path2, options), false));
        movementList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.path3, options), false));

        movementGrid = (GridViewInScroll) findViewById(R.id.movementGrid);
        movementAdapter = new ThemeAdapter(getApplicationContext(), movementList);
        movementGrid.setAdapter(movementAdapter);

        movementGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                resetMovementSelection();
                ThemeItem themeItem = movementList.get(position);
                themeItem.setSelected(true);
                movementList.set(position, themeItem);
                movementAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetMovementSelection() {
        for (ThemeItem ti : movementList) {
            ti.setSelected(false);
        }
    }

    private void initFrameBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 8;
        frameList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.cutout1, options), true));
        frameList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.cutout2, options), false));
        frameList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.cutout3, options), false));

        frameGrid = (GridViewInScroll) findViewById(R.id.frameGrid);
        frameAdapter = new ThemeAdapter(getApplicationContext(), frameList);
        frameGrid.setAdapter(frameAdapter);

        frameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                resetFrameSelection();
                ThemeItem themeItem = frameList.get(position);
                themeItem.setSelected(true);
                frameList.set(position, themeItem);
                frameAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetFrameSelection() {
        for (ThemeItem ti : frameList) {
            ti.setSelected(false);
        }
    }

    private void initcontainerBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 8;
        categoryList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.category1, options), true));
        categoryList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.category2, options), false));
        categoryList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.category3, options), false));

        categoryGrid = (GridViewInScroll) findViewById(R.id.categoryGrid);
        categoryAdapter = new ThemeAdapter(getApplicationContext(), categoryList);
        categoryGrid.setAdapter(categoryAdapter);

        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                resetCategorySelection();
                ThemeItem themeItem = categoryList.get(position);
                themeItem.setSelected(true);
                categoryList.set(position, themeItem);
                categoryAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetCategorySelection() {
        for (ThemeItem ti : categoryList) {
            ti.setSelected(false);
        }
    }
}
