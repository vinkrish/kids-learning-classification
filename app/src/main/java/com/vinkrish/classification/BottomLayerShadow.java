package com.vinkrish.classification;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by vinkrish on 10/04/16.
 */
public class BottomLayerShadow extends View {
    private Path mPath;
    private Paint mPaint;
    private int color = Color.WHITE;
    private Point point1, point2, point3, point4;

    public BottomLayerShadow (Context context, Point point1, Point point2, Point point3, Point point4, int color){
        super(context);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.color = color;
        init();
    }

    public BottomLayerShadow(Context context) {
        super(context);
        init();
    }

    public BottomLayerShadow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomLayerShadow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(getContext(), color));
        mPaint.setStrokeWidth(1);

        mPath.moveTo(point1.x, point1.y);
        mPath.lineTo(point2.x, point2.y);
        mPath.lineTo(point3.x, point3.y);
        mPath.lineTo(point4.x, point4.y);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        //Rect rect = new Rect(10, 10, 200, 100);
        //canvas.drawRect(rect, mPaint);

    }
}
