package com.my.mywebview.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.my.mywebview.R;

/**
 * [类功能说明]
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/9/7 14:24
 * @email wang_jp@murongtech.com
 */
public class CustomProgressBar extends View{

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
    private int mProgress;

    /**
     * 速度
     */
    private int mSpeed;

    /**
     * 是否应该开始下一个颜色处理
     */
    private boolean isNext = false;
//    是否继续绘制
    private boolean isContinue = true;
//    绘制线程
    private Thread thread;
//    文字画笔
    private Paint textPaint;
//    文字边界
    private Rect rect;
//    文字内容
    private String txtContent;
//    字体颜色
    private int txtColor;
//    字体大小
    private int txtSize;

    public CustomProgressBar(Context context) {
        this(context,null);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar,defStyleAttr,0);
        int n=typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int a=typedArray.getIndex(i);
            switch (a){
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor=typedArray.getColor(a, Color.CYAN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor=typedArray.getColor(a, Color.CYAN);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth= (int) typedArray.getDimension(a,
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed= typedArray.getInt(a,20);
                    break;
                case R.styleable.CustomProgressBar_txtContent:
                    txtContent= typedArray.getString(a);
                    break;
                case R.styleable.CustomProgressBar_txtColor:
                    txtColor= typedArray.getColor(a,Color.BLUE);
                    break;
                case R.styleable.CustomProgressBar_txtSize:
                    txtSize= (int) typedArray.getDimension(a,
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        mPaint=new Paint();
        textPaint=new Paint();
        rect=new Rect();
        thread=new Thread(){
            @Override
            public void run() {
                super.run();
                while (isContinue){
                    mProgress++;
                    if(mProgress==360){
                        mProgress=0;
                        if(!isNext){
                            isNext=true;
                        }else{
                            isNext=false;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(100/mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center=getWidth()/2;//获取圆心的x坐标
        int radius=center-mCircleWidth/2;//半径
        mPaint.setStrokeWidth(mCircleWidth);// 设置圆环的宽度
        mPaint.setAntiAlias(true);// 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF rectF=new RectF(center-radius,center-radius,
                center+radius,center+radius);// 用于定义的圆弧的形状和大小的界限
        textPaint.setColor(txtColor);
        textPaint.setTextSize(txtSize);
        textPaint.setStyle(Paint.Style.FILL);
        String txt=String.format(txtContent,mProgress);
        textPaint.getTextBounds(txt,0,txt.length(),rect);
        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (rect.width() > (center-mCircleWidth)*2)
        {
            TextPaint paint = new TextPaint(textPaint);
            String msg = TextUtils.ellipsize(txt, paint, (float) (center-mCircleWidth)*2 - getPaddingLeft()-getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
           canvas.drawText(msg, center-mCircleWidth-getPaddingLeft(), center+rect.height()/2, textPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(txt, center-rect.width()/2, center+rect.height()/2, textPaint);
        }
        if (!isNext)
        {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(rectF, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else
        {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(rectF, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }

    public void destroyThread(){
        isContinue=false;
        if(thread!=null){
            try {
                thread.interrupt();
                thread.stop();
                thread.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
