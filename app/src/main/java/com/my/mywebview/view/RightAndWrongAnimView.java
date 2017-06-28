package com.my.mywebview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.my.mywebview.R;

/**
 * Created by WJP-ADMIN on 2017/3/21.
 */

public class RightAndWrongAnimView extends View{
    private float width = 5;
    private int color = Color.BLUE;
    //绘制圆弧的进度值
    private int progress = 0;
    //线1的x轴
    private int line1_x = 0;
    //线1的y轴
    private int line1_y = 0;
    //线2的x轴
    private int line2_x = 0;
    //线2的y轴
    private int line2_y = 0;
    private Paint paint;
    private boolean isFish = false;
    private int newI;
    private long time = 5;
    private boolean isSucces = true;

    public RightAndWrongAnimView(Context context) {
        this(context, null);
    }

    public RightAndWrongAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RightAndWrongAnimView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RightAndWrongAnimView);
        color = typedArray.getColor(R.styleable.RightAndWrongAnimView_paintColor, color);
        width = typedArray.getFloat(R.styleable.RightAndWrongAnimView_strokeWidth, width);
        time = (long) typedArray.getFloat(R.styleable.RightAndWrongAnimView_time, time);
        typedArray.recycle();
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(color);
        //设置圆弧的宽度
        paint.setStrokeWidth(width);
        //设置圆弧为空心
        paint.setStyle(Paint.Style.STROKE);
        //消除锯齿
        paint.setAntiAlias(true);
    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
    //设置颜色
    public void setColor(int color) {
        this.color = color;
    }
    //设置宽度
    public void setWidth(float width) {
        this.width = width;
    }

    //设置动画的结束
    public void setFish(boolean fish) {
        isFish = fish;
    }
    //设置刷新频率
    public void setTime(long time) {
        this.time = time;
    }
    //设置是否成功
    public void setSucces(boolean succes) {
        isSucces = succes;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refresh(canvas);
    }

    private void refresh(Canvas canvas) {
        progress++;
        //获取圆弧形的x坐标
        int center = getWidth() / 2;
        int height = getHeight();
        int lineCenter = center - getWidth() / 5;
        int lineCenter2 = center + getWidth() / 5;
        //圆弧半径
        int radius = getWidth() / 2 - 5;
        //定义的圆弧大小
        RectF rectF = new RectF(center - radius - 1, center - radius - 1, center + radius + 1, center + radius + 1);
        int i = -360 * progress / 100;
        newI = 235;
        if (i != -360) {
            newI = i;
            //根据进度画圆弧
            canvas.drawArc(rectF, newI, newI + 50, false, paint);
        } else {
            if (!isFish) {
                //根据进度画圆弧
                progress = 0;
                canvas.drawArc(rectF, newI, newI + 50, false, paint);

            } else {
                progress = 100;
            }
        }
        if (progress >= 100) {
            if (isSucces) {
                if (line1_x < radius / 3) {
                    line1_x++;
                    line1_y++;
                }
                //画第一根线
                canvas.drawLine(lineCenter, center, lineCenter + line1_x, center + line1_y, paint);

                if (line1_x == radius / 3) {
                    line2_x = line1_x;
                    line2_y = line1_y;
                    line1_x++;
                    line1_y++;
                }
                if (line1_x >= radius / 3 && line2_x <= radius) {
                    line2_x++;
                    line2_y--;
                }
                //画第二根线
                canvas.drawLine(lineCenter + line1_x - 1, center + line1_y, lineCenter + line2_x, center + line2_y, paint);
            } else {
                if (line1_x < radius / 2 + 40) {
                    line1_x++;
                    line1_y++;
                    line2_x++;
                    line2_y++;
                }
                //画第一根线
                canvas.drawLine(lineCenter, height / 3, lineCenter + line1_x, height / 3 + 10 + line1_y, paint);
                canvas.drawLine(lineCenter2, height / 3, lineCenter2 - line2_x, height / 3 + 20 + line2_y, paint);


            }
        }

        //每隔指定毫秒界面刷新
        postInvalidateDelayed(time);
    }

}
