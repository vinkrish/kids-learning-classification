package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.vinkrish.classification.R;

/**
 * Created by vinkrish on 11/04/16.
 */
public class DashedLineView extends View {
    private Path mPath;
    private Paint mPaint;
    private int color = R.color.colorPrimaryDark;

    public DashedLineView(Context context, int color) {
        super(context);
        this.color = color;
        init();
    }

    public DashedLineView(Context context) {
        super(context);
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {
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

        int width = canvas.getWidth();
        int height = canvas.getHeight() / 2;

        mPath.moveTo(0, height);
        mPath.lineTo(width, height);

        canvas.drawPath(mPath, mPaint);

    }

}
