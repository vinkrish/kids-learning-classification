package com.vinkrish.classification;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by vinkrish on 04/04/16.
 */
public class SinusoidalView extends View {
    private Path mPath;
    private Paint mPaint;
    private int color = R.color.colorPrimaryDark;

    public SinusoidalView(Context context, int color) {
        super(context);
        this.color = color;
        init();
    }

    public SinusoidalView(Context context) {
        super(context);
        init();
    }

    public SinusoidalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SinusoidalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();

        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        //mPaint.setPathEffect(new DashPathEffect(new float[] {10,20}, 5));
        mPaint.setColor(ContextCompat.getColor(getContext(), color));
        mPaint.setStrokeWidth(5);

        /*mPath.moveTo(0, 0);
        mPath.quadTo(50, -50, 100, 0);
        mPath.quadTo(150, 50, 200, 0);
        mPath.quadTo(250, -50, 300, 0);
        mPath.quadTo(350, 50, 400, 0);
        mPath.quadTo(450, -50, 500, 0);*/

        //or
        /*mPath.moveTo(0,100);
        mPath.quadTo(50, -50, 100, 0);
        mPath.quadTo(150, 50, 200, 0);
        mPath.quadTo(250, -50, 300, 0);
        mPath.quadTo(350, 50, 400, 0);
        mPath.quadTo(450, -50, 500, 100);*/

        //or
        /*mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.quadTo(50, -100, 100, -50);
        mPath.quadTo(150, -0, 200, -50);
        mPath.quadTo(250, -100, 300, -50);
        mPath.quadTo(350, -0, 400, -50);
        mPath.quadTo(450, -100, 500, -0);
        mPath.close();*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float i, old_x, old_y, new_x, new_y;

        int w = canvas.getWidth();
        int h = canvas.getHeight();

        //canvas.drawPaint(mPaint);

        /*for (i = 0; i < 720; i = i + 10)
        {
            canvas.drawLine (i, 100 + 100*(float)Math.sin(i/180.0*Math.PI), i + 10, 100 + 100*(float)Math.sin ((i + 10)/180.0*Math.PI), mPaint);
        }*/

        /*float old_x = (float)0.0;
        float old_y = (float)Math.sin(old_x/180.0*Math.PI);

        for (i = 10; i < 720; i = i + 10) {
            float new_x = i;
            float new_y = (float) Math.sin(new_x / 180.0 * Math.PI);

            canvas.drawLine(old_x, 100 + 100 * old_y, new_x, 100 + 100 * new_y, mPaint);

            old_x = new_x;
            old_y = new_y;
        }*/

        old_x = (float) 0.0;
        old_y = (float) Math.cos(2 * old_x / 180.0 * Math.PI);

        for (i = 5; i <= 510; i = i + 5) {
            new_x = i;
            new_y = (float) Math.cos(2 * new_x / 180.0 * Math.PI);

            canvas.drawLine((float) (old_x / 510.0 * w), 110 + 50 * old_y, (float) (new_x / 510.0 * w), 110 + 50 * new_y, mPaint);

            old_x = new_x;
            old_y = new_y;
        }

        /*canvas.save();
        canvas.translate(0, getMeasuredHeight() / 2F);
        // just making sure to clip starting and ending curve
        canvas.clipRect(50, -100, 450, 100, Region.Op.INTERSECT);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();*/

    }

}
