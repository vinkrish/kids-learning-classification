package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.vinkrish.classification.R;

/**
 * Created by vinkrish on 08/05/16.
 */
public class SlateView extends View {

    private Path mPath;
    private Paint mPaint;
    private int initialX = 100;

    public SlateView(Context context, int initialX) {
        super(context);
        this.initialX = initialX;
        init();
    }

    public SlateView(Context context) {
        super(context);
        init();
    }

    public SlateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.themeViolet));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(20);
        mPath.moveTo(initialX, canvas.getHeight());
        mPath.lineTo(initialX, (float) (canvas.getHeight() - 50));
        mPaint.setStrokeWidth(30);
        mPath.lineTo(canvas.getWidth() - initialX, 50);
        mPaint.setStrokeWidth(20);
        mPath.lineTo(canvas.getWidth() - initialX, canvas.getHeight());

        canvas.drawPath(mPath, mPaint);

    }

}
