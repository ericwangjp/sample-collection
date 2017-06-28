package com.my.mywebview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.my.mywebview.utils.DensityUtil;

import java.lang.ref.WeakReference;

/**
 * Created by zhangdongyang on 2016/9/5.
 * Email:dongyangzhang@foxmail.com
 */
public class HighlightImageView extends ImageView {
    private static final String TAG = HighlightImageView.class.getSimpleName();

    protected Context mContext;

    private static final Xfermode sXfermode = new PorterDuffXfermode(
            PorterDuff.Mode.XOR);
    private Bitmap mMaskBitmap;
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakBitmap;

    public HighlightImageView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public HighlightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
    }

    public HighlightImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sharedConstructor(context);
    }

    private void sharedConstructor(Context context) {
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void invalidate() {
        mWeakBitmap = null;
        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
        }
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int i = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null,
                    Canvas.ALL_SAVE_FLAG);
            try {
                Bitmap bitmap = mWeakBitmap != null ? mWeakBitmap.get() : null;
                // Bitmap not loaded.
                if (bitmap == null || bitmap.isRecycled()) {
                    Drawable drawable = getDrawable();
                    if (drawable != null) {
                        // Allocation onDraw but it's ok because it will not
                        // always be called.
                        bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                                Bitmap.Config.ARGB_8888);
                        Canvas bitmapCanvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(bitmapCanvas);

                        // If mask is already set, skip and use cached mask.
                        if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                            mMaskBitmap = createMask1();
                        }

                        // Draw Bitmap.
                        mPaint.reset();
                        mPaint.setFilterBitmap(false);
                        mPaint.setXfermode(sXfermode);
                        // mBitmapShader = new BitmapShader(mMaskBitmap,
                        // Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                        // mPaint.setShader(mBitmapShader);
                        bitmapCanvas
                                .drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint);

                        mWeakBitmap = new WeakReference<Bitmap>(bitmap);
                    }
                }

                // Bitmap already loaded.
                if (bitmap != null) {
                    mPaint.setXfermode(null);
                    // mPaint.setShader(null);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
                    return;
                }
            } catch (Exception e) {
                System.gc();
            } finally {
                canvas.restoreToCount(i);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    public Bitmap createMask1() {
        int i = getWidth();
        int j = getHeight();
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);// 产生一个相同的画布
        Paint localPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        localPaint.setAntiAlias(true);
        localPaint.setColor(Color.WHITE);
        float f1 = getWidth();
        float f2 = getHeight();
        RectF localRectF = new RectF(f1 / 2
                - DensityUtil.dip2px(getContext(), 41), f2
                - DensityUtil.dip2px(getContext(), 70), f1 / 2
                + DensityUtil.dip2px(getContext(), 41), f2
                + DensityUtil.dip2px(getContext(), 12));
        localCanvas.drawOval(localRectF, localPaint);//画椭圆形,四角正方形则成圆形
        return localBitmap;
    }
}
