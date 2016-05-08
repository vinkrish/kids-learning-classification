package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.vinkrish.classification.R;

/**
 * Created by vinkrish on 08/04/16.
 */
public class ZigzagView extends View {
    private Path mPath;
    private Paint mPaint;
    private int color = R.color.colorPrimaryDark;

    public ZigzagView(Context context, int color) {
        super(context);
        this.color = color;
        init();
    }

    public ZigzagView(Context context) {
        super(context);
        init();
    }

    public ZigzagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZigzagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[] {20,20}, 0));
        mPaint.setColor(ContextCompat.getColor(getContext(), color));
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth() / 8;
        int height = canvas.getHeight();

        //canvas.drawPaint(mPaint);
        boolean flipY = false;
        int newY = 150;
        int newX = 0;
        mPath.moveTo(newX, newY);
        for (int i = 0; i < 9; i++) {
            mPath.lineTo(newX, newY);
            newX += width;
            if (flipY) {
                newY = 150;
                flipY = false;
            } else {
                newY = 50;
                flipY = true;
            }
        }

        /*mPath.lineTo(100, 100);
        mPath.lineTo(200, 0);
        mPath.lineTo(300, 100);
        mPath.lineTo(400, 0);
        mPath.lineTo(500, 100);
        mPath.lineTo(600, 0);
        mPath.lineTo(700, 100);
        mPath.lineTo(800, 0);*/
        //mPath.close();

        canvas.drawPath(mPath, mPaint);

    }
}
