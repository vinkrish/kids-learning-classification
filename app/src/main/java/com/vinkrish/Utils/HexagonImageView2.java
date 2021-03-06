package com.vinkrish.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by vinkrish on 12/04/16.
 */
public class HexagonImageView2 extends ImageView {
    private int width, height;

    public HexagonImageView2(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
    }

    public HexagonImageView2(Context context) {
        super(context);
    }

    public HexagonImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HexagonImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        //int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.width); // (width and height of ImageView)
        Bitmap drawnBitmap = drawCanvas(bitmap, width, height);
        canvas.drawBitmap(drawnBitmap, 0, 0, null);
    }

    private Bitmap drawCanvas(Bitmap recycledBitmap, int width, int height) {
        final Bitmap bitmap = verifyRecycledBitmap(recycledBitmap, width, height);

        final Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final Rect rect = new Rect(0, 0, width, height);
        final int offset = (int) (width / (double) 2 * Math.tan(30 * Math.PI / (double) 180)); // (width / 2) * tan(30deg)
        final int length = height - (2 * offset);

        final Path path = new Path();
        path.moveTo(width / 2, 0); // top
        path.lineTo(0, offset); // left top
        path.lineTo(0, offset + length); // left bottom
        path.lineTo(width / 2, height); // bottom
        path.lineTo(width, offset + length); // right bottom
        path.lineTo(width, offset); // right top
        path.close(); //back to top

        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint); // draws the bitmap for the image

        paint.reset();
        paint.setColor(Color.parseColor("white"));
        paint.setStrokeWidth(4);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setPathEffect(new CornerPathEffect(10));
        paint.setAntiAlias(true); // draws the border

        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, offset + 2); // message so border doesn't over extend
        path.lineTo(0, offset + length - 2); // massage so border doesn't over extend
        path.close();
        paint.setStrokeWidth(8);
        paint.setPathEffect(new CornerPathEffect(5));
        canvas.drawPath(path, paint); // draw left border a bit thicker

        path.reset();
        path.moveTo(width, offset + 2);  // massage so border doesn't over extend
        path.lineTo(width, offset + length - 2);  // massage so border doesn't over extend
        path.close();
        canvas.drawPath(path, paint); // draw right border a bit thicker

        return output;
    }

    private static Bitmap verifyRecycledBitmap(Bitmap bitmap, int width, int height) {
        final Bitmap recycledBitmap;
        if (bitmap.getWidth() != width || bitmap.getHeight() != height) {
            recycledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        } else {
            recycledBitmap = bitmap;
        }
        return recycledBitmap;
    }

}
