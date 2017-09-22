package com.my.mywebview.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.utils.log.LogManager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 自定义view练习1
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/9/5 19:47
 * @email wang_jp@murongtech.com
 */
public class CustomTitleView extends View{
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context,null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 获得自定义的属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得自定义的属性样式
         */
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView,defStyleAttr,0);
        int no = array.getIndexCount();
        for (int i=0;i<no;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CustomTitleView_TitleText:
                    mTitleText =  array.getString(attr);
                    break;
                case R.styleable.CustomTitleView_TitleTextColor:
//                    默认颜色设置为黑色
                    mTitleTextColor =  array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_TitleTextSize:
//                    默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize =  array.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }
        }
        array.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint=new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound=new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText=randomText();
                postInvalidate();
            }
        });
    }

    private String randomText() {
        Random random=new Random();
        Set<Integer>set=new HashSet<Integer>();
        while (set.size()<4){
            int randomNo = random.nextInt(10);
            set.add(randomNo);
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(Integer i:set){
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.color9EBDEF));
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTitleTextColor);
        LogManager.i("view宽高：",getWidth()+"**/**"+getHeight());
        LogManager.i("bound宽高：",mBound.width()+"**/**"+mBound.height());
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if(widthMode==MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textWidth=mBound.width();
            int finalWidth= (int) (getPaddingLeft()+textWidth+getPaddingRight());
            width = finalWidth;
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textHeight=mBound.height();
            int finalHeight= (int) (getPaddingTop()+textHeight+getPaddingBottom());
            height = finalHeight;
        }
        setMeasuredDimension(width,height);
    }
}
