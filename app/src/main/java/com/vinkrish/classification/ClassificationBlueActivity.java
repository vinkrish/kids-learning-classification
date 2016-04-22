package com.vinkrish.classification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ClassificationBlueActivity extends AppCompatActivity {
    private RelativeLayout classificationLayout, imageLayout, imageFrame;
    private LinearLayout topLayout, bottomLayout;
    private int initX, points;
    private HomeView homeView;
    private ZigzagView zigzagView;
    private String classificationName;
    ClassificationItem classificationItem;
    List<ClassificationItem> classificationItems;
    private String[] categoryList;
    private Button categoryButton[];
    private int[] categoryMarginLeft;
    private int categoryMarginTop, categorySize, categoryX, categoryY, categoryPosition;
    private int deviceWidth, imageWidth, imageHeight, classificationCount;

    private final float SPRITE_SCALE = 0.2f;
    private int SPRITE_SIZE;
    private final int SPRITE_TIMER = 10 * 1000;
    private final int SPRITE_INTERVAL = 1 * 1000;
    private final int WAVES_COUNT = 5;
    private int AMPLITUDE;

    private Handler spritesHandler;
    private Runnable spritesRunnable;

    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 30;
    private GestureDetectorCompat mDetector;

    private ImageView sprite;
    private HexagonImageView hexagonSprite;
    private HexagonImageView2 hexagonSprite2;
    private boolean fallback;
    float initialX;
    float initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification_blue);
        initView();
        loadClassification();

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        zigzagView = new ZigzagView(this,  R.color.themeBlue);

        bottomLayout.post(new Runnable() {
            @Override
            public void run() {
                int[] viewLocation = new int[2];
                topLayout.getLocationOnScreen(viewLocation);
                initX = viewLocation[0];
                homeView = new HomeView(getApplicationContext(), initX);
                classificationLayout.addView(homeView);
                showBottomShadow();
                bottomLayout.bringToFront();
                imageLayout.addView(zigzagView);
                AMPLITUDE = 60;
                showCategory();
            }
        });
    }

    private void initView() {
        classificationLayout = (RelativeLayout) findViewById(R.id.classificationLayout);
        topLayout = (LinearLayout) findViewById(R.id.topLayout);
        imageLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        bottomLayout = (LinearLayout) findViewById(R.id.bottomLayout);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        deviceWidth = displayMetrics.widthPixels;
        imageWidth = deviceWidth / 9;
        imageHeight = deviceWidth / 9;
        categoryX = deviceWidth / 2;

        categorySize = (int) (0.7 * imageWidth);
    }

    private void showBottomShadow() {
        int[] viewLocation = new int[2];
        bottomLayout.getLocationOnScreen(viewLocation);
        categoryY = viewLocation[1];
        Point point1 = new Point(viewLocation[0], viewLocation[1]);
        Point point2 = new Point(viewLocation[0] + 80, viewLocation[1] - 40);
        Point point3 = new Point(viewLocation[0] + bottomLayout.getWidth() - 80, viewLocation[1] - 40);
        Point point4 = new Point(viewLocation[0] + bottomLayout.getWidth(), viewLocation[1]);
        String color = "#999";
        BottomLayerShadow bottomLayerShadow = new BottomLayerShadow(this, point1, point2, point3, point4, color);
        classificationLayout.addView(bottomLayerShadow);
    }

    private void loadClassification() {

        String response1 = "{\"classification\":{\"id\":\"123\", \"name\":\"Classification Of Animals\", " +
                "\"points\":\"10\" ," +
                "\"category\":[\"Land Animal\", \"Sea Animal\", \"Flying Animal\"]," +
                " \"items\":[" +
                "{\"name\":\"cat\", \"source\":\"cat.jpg\", \"category\":\"Land Animal\"}," +
                "{\"name\":\"dog\", \"source\":\"dog.jpg\", \"category\":\"Land Animal\"}," +
                "{\"name\":\"cow\", \"source\":\"cow.jpg\", \"category\":\"Land Animal\"}," +
                "{\"name\":\"dolphin\", \"source\":\"dolphin.jpg\", \"category\":\"Sea Animal\"}," +
                "{\"name\":\"fish\", \"source\":\"fish.jpg\", \"category\":\"Sea Animal\"}," +
                "{\"name\":\"starfish\", \"source\":\"starfish.jpg\", \"category\":\"Sea Animal\"}," +
                "{\"name\":\"parrot\", \"source\":\"parrot.jpg\", \"category\":\"Flying Animal\"}," +
                "{\"name\":\"crow\", \"source\":\"crow.jpg\", \"category\":\"Flying Animal\"}," +
                "{\"name\":\"eagle\", \"source\":\"eagle.jpg\", \"category\":\"Flying Animal\"}" +
                "]}}";

        JSONObject classificationObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "classificationId");
            jsonObject.put("name", "classificationName");

            JSONArray categoryArray = new JSONArray();
            categoryArray.put("Land Animal");
            categoryArray.put("Sea Animal");
            categoryArray.put("Flying Animal");
            jsonObject.put("category", categoryArray);

            String[] name = {"cat", "dog", "cow", "dolphin", "fish", "starfish", "parrot", "crow", "eagle"};
            String[] source = {"cat.jpg", "dog.jpg", "cow.jpg", "dolphin.jpg", "fish.jpg", "starfish.jpg", "parrot.jpg", "crow.jpg", "eagle.jpg"};
            String[] category = {"Land Animal", "Land Animal", "Land Animal", "Sea Animal", "Sea Animal", "Sea Animal", "Flying Animal", "Flying Animal", "Flying Animal"};
            JSONArray categoryItems = new JSONArray();
            JSONObject[] itemsArray = new JSONObject[name.length];
            for (int i = 0; i < name.length; i++) {
                itemsArray[i] = new JSONObject();
                itemsArray[i].put("name", name[i]);
                itemsArray[i].put("source", source[i]);
                itemsArray[i].put("category", category[i]);
                categoryItems.put(itemsArray[i]);
            }
            jsonObject.put("items", categoryItems);

            classificationObject.put("classification", jsonObject);

            Log.d("json", classificationObject + "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        try {
            JSONObject json = new JSONObject(response1);

            Classification classification = gson.fromJson(json.getString("classification"), Classification.class);

            //Classification classification = gson.fromJson(response2, Classification.class);

            classificationName = classification.getClassificationName();
            points = classification.getPoints();
            categoryList = classification.getCategoryList();
            classificationItems = classification.getClassificationItems();

            for (String s : categoryList) {
                //Log.d("category", s);
            }

            for (ClassificationItem ci : classificationItems) {
                //Log.d(ci.getName(), "---" + ci.getSource() + "---" + ci.getCategory());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showCategory() {
        categoryMarginSetUp();
        categoryMarginTop = categoryY - categorySize - 10;
        categoryButton = new Button[categoryList.length];
        for (int i = 0; i < categoryList.length; i++) {
            RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(categorySize, categorySize);
            //buttonParams.setMargins(marginLeft[i], marginTop, 0 ,0);
            buttonParams.leftMargin = categoryMarginLeft[i];
            buttonParams.topMargin = categoryMarginTop;
            //buttonParams.bottomMargin = 5;
            categoryButton[i] = new Button(this);
            categoryButton[i].setLayoutParams(buttonParams);
            //categoryButton[i].setPadding(0, 0, 0, 5);
            categoryButton[i].setText(categoryList[i]);
            categoryButton[i].setTextSize(12);
            categoryButton[i].setTag(categoryList[i]);
            categoryButton[i].setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
            categoryButton[i].setBackgroundResource(R.drawable.category2);
            //categoryButton[i].setX((float)marginLeft[i]);
            //categoryButton[i].setY((float)marginTop);
            classificationLayout.addView(categoryButton[i]);
        }
        initTouchListeners();
        autoGenerateSprites();
    }

    private void autoGenerateSprites() {
        spritesHandler = new Handler();
        spritesRunnable = new Runnable() {
            @Override
            public void run() {
                generateSprite();
            }
        };
        spritesHandler.postDelayed(spritesRunnable, SPRITE_INTERVAL);
    }

    private void resetCategoryButtonParam(int position, View view){
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(categorySize, categorySize);
        //buttonParams.setMargins(marginLeft[i], marginTop, 0 ,0);
        buttonParams.leftMargin = categoryMarginLeft[position];
        buttonParams.topMargin = categoryMarginTop;
        //buttonParams.bottomMargin = 5;
        view.setLayoutParams(buttonParams);
    }

    private void categoryMarginSetUp() {
        if (categoryList.length == 2) {
            categoryMarginLeft = new int[2];
            categoryMarginLeft[0] = categoryX - (2 * categorySize);
            categoryMarginLeft[1] = categoryX + categorySize;
        } else if (categoryList.length == 3) {
            categoryMarginLeft = new int[3];
            categoryMarginLeft[0] = (int) (categoryX - (2.5 * categorySize));
            categoryMarginLeft[1] = (int) (categoryX - (0.5 * categorySize));
            categoryMarginLeft[2] = (int) (categoryX + (1.5 * categorySize));
        } else if (categoryList.length == 4) {
            categoryMarginLeft = new int[4];
            categoryMarginLeft[0] = categoryX - (4 * categorySize);
            categoryMarginLeft[1] = categoryX - (2 * categorySize);
            categoryMarginLeft[2] = categoryX + categorySize;
            categoryMarginLeft[3] = categoryX + (3 * categorySize);
        }
    }

    private void initTouchListeners() {
        for (int i = 0; i < categoryList.length; i++) {
            initSpriteTouchListener(categoryButton[i], i);
        }
    }

    private void cancelTouchListeners() {
        for (int i = 0; i < categoryList.length; i++) {
            categoryButton[i].setOnTouchListener(null);
        }
    }

    private void initSpriteTouchListener(View view, final int categoryPos) {
        view.setOnTouchListener(new View.OnTouchListener() {
            float dX;
            float dY;
            float pivotX;
            float pivotY;

            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                mDetector.onTouchEvent(event);
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = view.getX();
                        initialY = view.getY();
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = (int) (event.getRawX() + dX);
                        layoutParams.topMargin = (int) (event.getRawY() + dY);
                        layoutParams.rightMargin = (int) (0 - SPRITE_SIZE);

                        view.setLayoutParams(layoutParams);
                        view.setPivotX(pivotX);
                        view.setPivotY(pivotY);

                        break;

                    case MotionEvent.ACTION_UP:
                        if (fallback) {
                            wrongGuess(view);
                        } else {
                            cancelTouchListeners();
                            categoryPosition = categoryPos;
                            rightGuess(view);
                        }
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void generateSprite() {
        categoryPosition = -1;
        classificationItem = classificationItems.get(classificationCount);
        String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + classificationItem.getSource();

        imageFrame = new RelativeLayout(this);
        RelativeLayout.LayoutParams fp = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
        //fp.addRule(RelativeLayout.CENTER_VERTICAL);
        fp.setMargins(-imageWidth, 50 , 0, 0);
        imageFrame.setLayoutParams(fp);
        imageFrame.setGravity(Gravity.CENTER_VERTICAL);
        imageFrame.setBackgroundResource(R.drawable.cutout2);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        sprite = new ImageView(this);
        sprite.setLayoutParams(lp);
        sprite.setPadding(5, 20, 5, 15);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        sprite.setImageBitmap(bitmap);

        //imageFrame.addView(sprite);

        LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        hp.gravity = Gravity.CENTER;
        hexagonSprite = new HexagonImageView(this);
        hexagonSprite.setLayoutParams(hp);
        hexagonSprite.setBorderColor(Color.GRAY);
        hexagonSprite.setBorderWidth(5);
        hexagonSprite.setImageBitmap(bitmap);
        //imageFrame.addView(hexagonSprite);

        hexagonSprite2 = new HexagonImageView2(this, imageWidth, imageHeight);
        //hp.setMargins(0, 10, 0 ,0);
        hexagonSprite2.setLayoutParams(hp);
        hexagonSprite2.setImageBitmap(bitmap);
        //hexagonSprite2.setPadding(0, 10, 0, 0);
        imageFrame.addView(hexagonSprite2);

        imageLayout.addView(imageFrame);
        imageFrame.setVisibility(View.VISIBLE);

        /*AnimationUtils.TrajectoryAnimation spriteTrajectory = new AnimationUtils.TrajectoryAnimation(imageFrame, (int) targetX, 50, 0, -imageWidth, 50, 0);
        spriteTrajectory.setDuration(SPRITE_TIMER);
        //spriteTrajectory.setFillAfter(true);
        imageFrame.startAnimation(spriteTrajectory);*/

        /*AnimationUtils.SineWaveTrajectory spriteTrajectory = new AnimationUtils.SineWaveTrajectory(imageFrame, deviceWidth, 1, AMPLITUDE, fp.leftMargin, 50);
        spriteTrajectory.setDuration(SPRITE_TIMER);
        imageFrame.startAnimation(spriteTrajectory);*/

        AnimationUtils.ZigZagTrajectory spriteTrajectory = new AnimationUtils.ZigZagTrajectory(imageFrame, deviceWidth, WAVES_COUNT, AMPLITUDE, fp.leftMargin, 60);
        spriteTrajectory.setDuration(SPRITE_TIMER);
        imageFrame.startAnimation(spriteTrajectory);

        spriteTrajectory.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rebootSprite();
            }
        });
        spriteTrajectory.setFillAfter(false);

        classificationCount++;
    }

    private void regenerateSprite(){
        spritesHandler = new Handler();
        spritesRunnable = new Runnable() {
            @Override
            public void run() {
                //imageFrame.setVisibility(View.GONE);
                imageFrame.clearAnimation();
            }
        };
        spritesHandler.postDelayed(spritesRunnable, 500);
    }

    private void rebootSprite() {
        //imageFrame.setVisibility(View.GONE);
        //imageFrame.removeAllViews();
        //((ViewGroup) findViewById(R.id.imageLayout)).removeView(sprite);
        ((ViewGroup) findViewById(R.id.imageLayout)).removeView(imageFrame);
        if (categoryPosition != -1) {
            resetCategoryButtonParam(categoryPosition, categoryButton[categoryPosition]);
            AnimationUtils.alphaScale(categoryButton[categoryPosition], this, 500);
        }
        //classificationVisibility();
        if (classificationCount < classificationItems.size()) {
            initTouchListeners();
            generateSprite();
        }
    }

    private void rightGuess(final View view) {
        int[] viewLocation = new int[2];
        imageFrame.getLocationOnScreen(viewLocation);

        AnimationUtils.TrajectoryAnimation resetPositionAnimation = new AnimationUtils.TrajectoryAnimation(view, (int) viewLocation[0] + 20, (int) viewLocation[1], 0, (int) view.getX(), (int) view.getY(), view.getRotation());
        resetPositionAnimation.setDuration(500);
        resetPositionAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
                //fadeView(sprite);
                fadeView(hexagonSprite2);
            }
        });
        resetPositionAnimation.setFillAfter(false);
        view.startAnimation(resetPositionAnimation);
    }

    private void animateResult() {
        View answeredScoreView = getLayoutInflater().inflate(R.layout.answered_score_view, imageFrame, false);
        imageFrame.addView(answeredScoreView);
        ImageView star1 = (ImageView) findViewById(R.id.star1);
        ImageView star2 = (ImageView) findViewById(R.id.star2);
        ImageView star3 = (ImageView) findViewById(R.id.star3);
        //TextView scoreTV = (TextView) findViewById(R.id.current_score);
        //scoreTV.setText("10 Points");
        AnimationUtils.alphaScale(star1, this, 500);
        AnimationUtils.alphaScale(star2, this, 500);
        AnimationUtils.alphaScale(star3, this, 500);
        //AnimationUtils.alphaScale(scoreTV, this, 500);
        spritesHandler = new Handler();
        spritesRunnable = new Runnable() {
            @Override
            public void run() {
                //sprite.clearAnimation();
                regenerateSprite();
                //imageFrame.removeView(answeredScoreView);
            }
        };
        spritesHandler.postDelayed(spritesRunnable, 500);
    }

    private void fadeView (final View view) {
        AlphaAnimation fadeAnimation = new AlphaAnimation(1f, 0f);
        fadeAnimation.setDuration(500);
        view.startAnimation(fadeAnimation);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                animateResult();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void wrongGuess(View view) {
        AnimationUtils.TrajectoryAnimation resetPositionAnimation = new AnimationUtils.TrajectoryAnimation(view, (int) initialX, (int) initialY, 0, (int) view.getX(), (int) view.getY(), view.getRotation());
        resetPositionAnimation.setDuration(300);
        resetPositionAnimation.setFillAfter(true);
        view.startAnimation(resetPositionAnimation);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            float event1X = event1.getRawX();
            float event1Y = event1.getRawY();
            float event2X = event2.getRawX();
            float event2Y = event2.getRawY();

            if (event1Y - event2Y > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY &&
                    event2X > event1X &&
                    imageFrame.getX() > initialX) {
                fallback = false;

                return false; // Bottom to top and right fling
            } else if (event1Y - event2Y > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY &&
                    event1X > event2X &&
                    imageFrame.getX() < initialX) {
                fallback = false;

                return false; // Bottom to top and left fling
            } else if (event1Y - event2Y > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY &&
                    event2X > event1X &&
                    imageFrame.getX() < initialX &&
                    imageFrame.getX()+imageWidth > initialX) {
                fallback = false;

                return false;
            } else {
                fallback = true;
            }
            return true;
        }

    }

}
