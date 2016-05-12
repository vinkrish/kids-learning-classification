package com.vinkrish.classification;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vinkrish.Utils.CurvedView;
import com.vinkrish.Utils.DashedLineView;
import com.vinkrish.Utils.GridViewInScroll;
import com.vinkrish.Utils.HomeView;
import com.vinkrish.Utils.SinusoidalView;
import com.vinkrish.Utils.SlateView;
import com.vinkrish.Utils.ZigzagView;

import java.util.ArrayList;

public class ClassificationThemeActivity extends AppCompatActivity {
    private GridViewInScroll themeGrid, backgroundGrid, movementGrid, frameGrid, categoryGrid;
    private ThemeAdapter themeAdapter, backgroundAdapter, movementAdapter, frameAdapter, categoryAdapter;
    private ArrayList<ThemeItem> themeList = new ArrayList<>();
    private ArrayList<ThemeItem> backgroundList = new ArrayList<>();
    private ArrayList<ThemeItem> movementList = new ArrayList<>();
    private ArrayList<ThemeItem> frameList = new ArrayList<>();
    private ArrayList<ThemeItem> categoryList = new ArrayList<>();

    private RelativeLayout classificationLayout, imageLayout;
    private LinearLayout topLayout, bottomLayout;
    private ImageView categoryImage, frameImage;

    private SlateView slateView;
    private HomeView homeView;
    private CurvedView curvedView;
    private DashedLineView dashedLineView;
    private ZigzagView zigzagView;
    private SinusoidalView sinusoidalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification_theme);

        initView();

        initThemeBitmap();
        initBackgroundBitmap();
        initMovementBitmap();
        initFrameBitmap();
        initcontainerBitmap();

        bottomLayout.post(new Runnable() {
            @Override
            public void run() {
                int[] viewLocation = new int[2];
                topLayout.getLocationOnScreen(viewLocation);
                int initX = viewLocation[0] - 30;
                //int finalX = initX + topLayout.getWidth();
                slateView = new SlateView(ClassificationThemeActivity.this, initX);
                curvedView = new CurvedView(getApplicationContext(), initX);
                homeView = new HomeView(getApplicationContext(), initX);
                classificationLayout.addView(slateView);
                initVioletTheme();
            }
        });
    }

    private void initView() {
        classificationLayout = (RelativeLayout) findViewById(R.id.classificationLayout);
        topLayout = (LinearLayout) findViewById(R.id.topLayout);
        imageLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        bottomLayout = (LinearLayout) findViewById(R.id.bottomLayout);
        categoryImage = (ImageView) findViewById(R.id.categoryImage);
        frameImage = (ImageView) findViewById(R.id.frameImage);
    }

    private void initThemeBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.theme1, options), true));

        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.theme2, options), false));

        themeList.add(new ThemeItem(BitmapFactory.decodeResource(this.getResources(), R.drawable.theme3, options), false));

        themeGrid = (GridViewInScroll) findViewById(R.id.themeGrid);
        themeAdapter = new ThemeAdapter(getApplicationContext(), themeList);
        themeGrid.setAdapter(themeAdapter);

        themeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                resetThemeSelection();
                ThemeItem themeItem = themeList.get(position);
                themeItem.setSelected(true);
                themeList.set(position, themeItem);
                themeAdapter.notifyDataSetChanged();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (position == 0) {
                                    initVioletTheme();
                                } else if (position == 1) {
                                    initBlueTheme();
                                } else if (position == 2) {
                                    initYellowTheme();
                                }
                            }
                        });
                    }
                }).start();
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
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                resetBackgroundSelection();
                ThemeItem themeItem = backgroundList.get(position);
                themeItem.setSelected(true);
                backgroundList.set(position, themeItem);
                backgroundAdapter.notifyDataSetChanged();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (position == 0) {
                                    classificationLayout.setBackgroundResource(R.drawable.background1);
                                } else if (position == 1) {
                                    classificationLayout.setBackgroundResource(R.drawable.background2);
                                } else if (position == 2) {
                                    classificationLayout.setBackgroundResource(R.drawable.background3);
                                }
                            }
                        });
                    }
                }).start();
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
                imageLayout.removeAllViews();
                if (position == 0) {
                    FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
                    fp.setMargins(0, 80, 0, 0);
                    imageLayout.setLayoutParams(fp);
                    imageLayout.addView(dashedLineView);
                } else if (position == 1) {
                    FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
                    fp.setMargins(0, 130, 0, 0);
                    imageLayout.setLayoutParams(fp);
                    imageLayout.addView(zigzagView);
                } else if (position == 2) {
                    FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
                    fp.setMargins(0, 130, 0, 0);
                    imageLayout.setLayoutParams(fp);
                    imageLayout.addView(sinusoidalView);
                }
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
                if (position == 0) {
                    frameImage.setBackgroundResource(R.drawable.cutout1);
                } else if (position == 1) {
                    frameImage.setBackgroundResource(R.drawable.cutout2);
                } else if (position == 2) {
                    frameImage.setBackgroundResource(R.drawable.cutout3);
                }
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
                if (position == 0) {
                    categoryImage.setBackgroundResource(R.drawable.category1);
                } else if (position == 1) {
                    categoryImage.setBackgroundResource(R.drawable.category2);
                } else if (position == 2) {
                    categoryImage.setBackgroundResource(R.drawable.category3);
                }
            }
        });
    }

    private void resetCategorySelection() {
        for (ThemeItem ti : categoryList) {
            ti.setSelected(false);
        }
    }

    private void initVioletTheme() {
        /*BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        BitmapDrawable ob = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(this.getResources(), R.drawable.theme1, options));
        classificationLayout.setBackgroundDrawable(ob);*/

        dashedLineView = new DashedLineView(this, R.color.themeViolet);
        zigzagView = new ZigzagView(this,  R.color.themeViolet);
        sinusoidalView = new SinusoidalView(this, R.color.themeViolet);

        classificationLayout.setBackgroundResource(R.drawable.background1);
        bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.themeViolet));
        //categoryImage.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.category1));
        categoryImage.setBackgroundResource(R.drawable.category1);
        imageLayout.removeAllViews();
        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        fp.setMargins(0, 80, 0, 0);
        imageLayout.setLayoutParams(fp);
        imageLayout.addView(dashedLineView);
        frameImage.setBackgroundResource(R.drawable.cutout1);
        removeFrameViews();
        classificationLayout.addView(slateView);
        updateCustomiseTheme(0);
    }

    private void initBlueTheme(){
        dashedLineView = new DashedLineView(this, R.color.themeBlue);
        zigzagView = new ZigzagView(this,  R.color.themeBlue);
        sinusoidalView = new SinusoidalView(this, R.color.themeBlue);

        classificationLayout.setBackgroundResource(R.drawable.background2);
        bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.themeBlue));
        categoryImage.setBackgroundResource(R.drawable.category2);
        imageLayout.removeAllViews();
        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        fp.setMargins(0, 130, 0, 0);
        imageLayout.setLayoutParams(fp);
        imageLayout.addView(zigzagView);
        frameImage.setBackgroundResource(R.drawable.cutout2);
        removeFrameViews();
        classificationLayout.addView(homeView);
        updateCustomiseTheme(1);
    }

    private void initYellowTheme() {
        dashedLineView = new DashedLineView(this, R.color.themeYellow);
        zigzagView = new ZigzagView(this,  R.color.themeYellow);
        sinusoidalView = new SinusoidalView(this, R.color.themeYellow);

        classificationLayout.setBackgroundResource(R.drawable.background3);
        //classificationLayout.addView(curvedView);
        bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.themeBlack));
        categoryImage.setBackgroundResource(R.drawable.category3);
        imageLayout.removeAllViews();
        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        fp.setMargins(0, 130, 0, 0);
        imageLayout.setLayoutParams(fp);
        imageLayout.addView(sinusoidalView);
        frameImage.setBackgroundResource(R.drawable.cutout3);
        removeFrameViews();
        classificationLayout.addView(curvedView);
        bottomLayout.bringToFront();
        //curvedView.bringToFront();
        updateCustomiseTheme(2);
    }

    private void removeFrameViews() {
        classificationLayout.removeView(slateView);
        classificationLayout.removeView(homeView);
        classificationLayout.removeView(curvedView);
    }

    private void updateCustomiseTheme(int position) {

        resetBackgroundSelection();
        ThemeItem backgroundItem = backgroundList.get(position);
        backgroundItem.setSelected(true);
        backgroundList.set(position, backgroundItem);
        backgroundAdapter.notifyDataSetChanged();

        resetMovementSelection();
        ThemeItem movementItem = movementList.get(position);
        movementItem.setSelected(true);
        movementList.set(position, movementItem);
        movementAdapter.notifyDataSetChanged();

        resetFrameSelection();
        ThemeItem cutoutItem = frameList.get(position);
        cutoutItem.setSelected(true);
        frameList.set(position, cutoutItem);
        frameAdapter.notifyDataSetChanged();

        resetCategorySelection();
        ThemeItem categoryItem = categoryList.get(position);
        categoryItem.setSelected(true);
        categoryList.set(position, categoryItem);
        categoryAdapter.notifyDataSetChanged();
    }
}
