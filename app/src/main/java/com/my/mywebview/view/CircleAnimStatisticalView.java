package com.my.mywebview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.my.mywebview.R;

import static android.R.attr.textSize;
import static android.graphics.Paint.Style.STROKE;

/**
 * Created by WJP on 2017/4/17.
 */

public class CircleAnimStatisticalView extends View{
    float roundWidth=100;
    int progress=30;
    int max=100;
    boolean textIsDisplayable=true;
    Paint.Style style= Paint.Style.STROKE;

    public CircleAnimStatisticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public CircleAnimStatisticalView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先初始化一个Paint来简单指定一下Canvas的颜色
        Paint paint = new Paint();
        int centre = getWidth() / 2;
        int radius = (int) (centre - roundWidth / 2);

        paint.setColor(getResources().getColor(R.color.colorADADAD));
        paint.setStyle(STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);

//关键语句：设置进度条轨道时应该设置背景色为空
        paint.setShader(null);
        canvas.drawCircle(centre, centre, radius, paint);

        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int) (((float) progress / (float) max) * 100);
        float textWidth = paint.measureText(percent + "%");

        if (textIsDisplayable && percent != 0 && style == STROKE) {
            canvas.drawText(percent + "%", centre - textWidth / 2, centre
                    + textSize / 2, paint);
        }

        paint.setStrokeWidth(roundWidth);
        // paint.setColor(roundProgressColor);
        int[] mColors = new int[] {// 渐变色数组
                0xFF0da7ff, 0xFF31da41, 0xFF31da41, 0xFF0da7ff ,0xFF0da7ff  };
        Shader sg = new SweepGradient(0, 0, mColors, null);

        SweepGradient sg2 = new SweepGradient(centre, centre, getResources()
                .getColor(R.color.color4C89E6), getResources().getColor(
                R.color.color2DC868));
        paint.setShader(sg);
        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);

        switch (style) {
            case STROKE: {
                Log.e("zhang", centre+"调试1："+radius);
                paint.setStyle(STROKE);
                canvas.drawArc(oval, -90, 360 * progress / max, false, paint);
                break;
            }
            case FILL: {
                Log.e("zhang", "调试2："+360 * progress / max);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);
                break;
            }
        }
    }
}
