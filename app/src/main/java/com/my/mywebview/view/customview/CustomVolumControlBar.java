package com.my.mywebview.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.my.mywebview.R;

/**
 * [类功能说明]
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/9/8 11:03
 * @email wang_jp@murongtech.com
 */
public class CustomVolumControlBar extends View{

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;

    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    private Rect mRect;

    private RectF rectF;

    public CustomVolumControlBar(Context context) {
        this(context,null);
    }

    public CustomVolumControlBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomVolumControlBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar,defStyleAttr,0);
        int n=typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int a=typedArray.getIndex(i);
            switch (a){
                case R.styleable.CustomVolumControlBar_firstColor:
                    mFirstColor=typedArray.getColor(a, Color.BLACK);
                    break;
                case R.styleable.CustomVolumControlBar_secondColor:
                    mSecondColor=typedArray.getColor(a, Color.WHITE);
                    break;
                case R.styleable.CustomVolumControlBar_circleWidth:
                    mCircleWidth= (int) typedArray.getDimension(a,
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_dotCount:
                    mCount=typedArray.getInt(a,6);
                    break;
                case R.styleable.CustomVolumControlBar_splitSize:
                    mSplitSize=typedArray.getInt(a,20);
                    break;
                case R.styleable.CustomVolumControlBar_bg:
                    mImage= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(a,0));
                    break;
            }
        }
        typedArray.recycle();
        mPaint=new Paint();
        mRect=new Rect();
        rectF=new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//先画一个黑色背景
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.lightblacktransp));
        mPaint.setStyle(Paint.Style.FILL);
        rectF.left=0;
        rectF.top=0;
        rectF.right=getMeasuredWidth();
        rectF.bottom=getMeasuredHeight();
        canvas.drawRoundRect(rectF,30,30,mPaint);

        mPaint.setAntiAlias(true);// 消除锯齿
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 定义线段断点形状为圆头
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        int center = getWidth() / 2; // 获取圆心的x坐标
        int radius = center - mCircleWidth / 2;// 半径

//        绘制画块
        drawOval(canvas,center,radius);
//        计算内切正方形的位置
        int relRadius=radius-mCircleWidth/2;// 获得内圆的半径
//        内切正方形边长/2= relRadius*√2 / 2
//        内切正方形边长距离顶部最短距离= radius-relRadius*√2 / 2+mCircleWidth
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
//        内切正方形距离左边=radius-relRadius*√2 / 2+mCircleWidth
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);
/**
 * 如果图片比较小，那么根据图片的尺寸放置到正中心
 */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius){
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());

        }
        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);


    }
    //根据参数画出每个小块
    private void drawOval(Canvas canvas, int center, int radius) {
        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;

        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        for (int i = 0; i < mCount; i++){
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++){
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }
    }

    /**
     * 当前数量+1
     */
    public void up()
    {
        mCurrentCount++;
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down()
    {
        mCurrentCount--;
        postInvalidate();
    }

    private int yDown, yUp;

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                yDown = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                yUp = (int) event.getY();
                // 下滑-
                if (yUp > yDown&&mCurrentCount>0){
                    down();
                    // 上滑+
                } else if(yUp < yDown&&mCurrentCount<mCount){
                    up();
                }
                break;
        }

        return true;
    }
}
