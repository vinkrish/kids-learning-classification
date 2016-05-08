package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.vinkrish.classification.R;

/**
 * Created by vinkrish on 09/04/16.
 */
public class HomeView extends View {
    private Path mPath;
    private Paint mPaint;
    private int initialX = 100;

    public HomeView (Context context, int initialX) {
        super(context);
        this.initialX = initialX;
        init();
    }

    public HomeView(Context context) {
        super(context);
        init();
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.themeBlue));
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(initialX, canvas.getHeight());
        mPath.lineTo(initialX, (float) (canvas.getHeight() - 0.75 * canvas.getHeight()));
        mPath.lineTo(canvas.getWidth() / 2, 50);
        mPath.lineTo(canvas.getWidth() / 2 + canvas.getWidth() / 2 - initialX, (float) (canvas.getHeight() - 0.75 * canvas.getHeight()));
        mPath.lineTo(canvas.getWidth() / 2 + canvas.getWidth() / 2 - initialX, canvas.getHeight());

        canvas.drawPath(mPath, mPaint);

    }

}
