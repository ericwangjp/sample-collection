package com.my.mywebview.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.utils.log.LogManager;

/**
 * [类功能说明]
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/9/6 16:26
 * @email wang_jp@murongtech.com
 */
public class CustomImageView extends View{

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

    private Bitmap mImg;
    private int scaleType;
    private int mWidth,mHeight;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mTextBound,rect;
    private Paint mPaint;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 初始化所有自定义属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView,defStyleAttr,0);
        LogManager.i("typedArray结构：",typedArray);
        int n=typedArray.getIndexCount();
        LogManager.i("******n******：",n);
        for(int i=0;i<n;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CustomImageView_TitleText:
                    mTitleText=typedArray.getString(attr);
                    LogManager.i("具体属性：",typedArray.getString(attr));
                    break;
                case R.styleable.CustomImageView_TitleTextColor:
                    mTitleTextColor=typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_TitleTextSize:
                    mTitleTextSize=typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImageView_image:
                    mImg = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    scaleType = typedArray.getInt(attr,0);
                    break;
            }
        }
        typedArray.recycle();
        rect=new Rect();
        mPaint=new Paint();
        mTextBound=new Rect();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        设置宽度
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);
        if(specMode==MeasureSpec.EXACTLY){
            mWidth=specSize;
        }else {
//            由图片决定的宽
            int widthByImg=getPaddingLeft()+getPaddingRight()+mImg.getWidth();
//            由字体决定的宽
            int widthByText=getPaddingLeft()+getPaddingRight()+mTextBound.width();

            if(specMode==MeasureSpec.AT_MOST){
                int finalWidth=Math.max(widthByImg,widthByText);
                mWidth=Math.min(finalWidth,specSize);
            }
        }
//        设置高度
        int specModeH=MeasureSpec.getMode(heightMeasureSpec);
        int specSizeS=MeasureSpec.getSize(heightMeasureSpec);
        if(specModeH==MeasureSpec.EXACTLY){
            mHeight=specSizeS;
        }else {
            int totHeight=getPaddingTop()+getPaddingBottom()+mImg.getHeight()+mTextBound.height();

            if(specModeH==MeasureSpec.AT_MOST){
                mHeight=Math.min(totHeight,specSizeS);
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        rect.left=getPaddingLeft();
        rect.right=mWidth-getPaddingRight();
        rect.top=getPaddingTop();
        rect.bottom=mHeight-getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

//        当前设置的宽度小于字体需要的宽度，将超出部分文字改为...
        if(mTextBound.width()>mWidth){
            TextPaint textPaint=new TextPaint(mPaint);
            String msg= TextUtils.ellipsize(mTitleText,textPaint,mWidth-getPaddingLeft()-getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight-getPaddingBottom(),mPaint);
        }else{
//            正常情况，将字体居中
            canvas.drawText(mTitleText,mWidth/2-mTextBound.width()*1.0f/2,mHeight-getPaddingBottom(),mPaint);
//            取消使用掉的块
            rect.bottom-=mTextBound.height();
            if(scaleType==0){
                canvas.drawBitmap(mImg,null,rect,mPaint);
            }else{
//                计算居中的矩形范围
                rect.left=mWidth/2-mImg.getWidth()/2;
                rect.right=mWidth/2+mImg.getWidth()/2;
                rect.top=(mHeight-mTextBound.height())/2-mImg.getHeight()/2;
                rect.bottom=(mHeight-mTextBound.height())/2+mImg.getHeight()/2;
                canvas.drawBitmap(mImg,null,rect,mPaint);
            }
        }
    }
}
