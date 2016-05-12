package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.vinkrish.classification.R;

/**
 * Created by vinkrish on 09/04/16.
 */
public class CurvedView extends View {
    private int w, h;
    private Paint mPaint;
    private Path mPath;
    private int initialX = 100, finalX;

    public CurvedView(Context context, int initialX) {
        super(context);
        this.initialX = initialX;
        init();
    }

    public CurvedView(Context context, int initialX, int finalX) {
        super(context);
        this.initialX = initialX;
        this.finalX = finalX;
        init();
    }

    public CurvedView(Context context) {
        super(context);
        init();
    }

    public CurvedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurvedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.themeYellow));

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = canvas.getWidth();
        h = canvas.getHeight();
        drawArc(canvas);

        mPath.moveTo(initialX, canvas.getHeight());
        mPath.lineTo(initialX, (float) (canvas.getHeight() / 2));

        mPath.moveTo(canvas.getWidth() / 2 + canvas.getWidth() / 2 - initialX, canvas.getHeight());
        mPath.lineTo(canvas.getWidth() / 2 + canvas.getWidth() / 2 - initialX, (float) (canvas.getHeight() / 2));

        canvas.drawPath(mPath, mPaint);

        /*Paint circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(50);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.YELLOW);
        canvas.drawArc(100, 100, canvas.getWidth() - 100, (canvas.getHeight() * 2) - 600, -180, 180, false, circlePaint);*/
    }

    private void drawArc(Canvas canvas) {

        final RectF arrowOval = new RectF();
        arrowOval.set(initialX,
                50,
                canvas.getWidth() - initialX,
                (canvas.getHeight()));

        mPath.addArc(arrowOval, -180, 180);
        //mPath.addArc(100, 100, canvas.getWidth() - 100, canvas.getHeight() - 100, -180, 180);

        canvas.drawPath(mPath, mPaint);
    }

}
