package com.my.mywebview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.my.mywebview.R;

/**
 * Created by WJP on 2017/6/15.
 */

public class WifiAnimSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    // 用于控制SurfaceView 的大小、格式等，并且主要用于监听SurfaceView 的状态
    private SurfaceHolder sfh;
    // 声明一个画布
    private Canvas canvas;
    // 声明一个线程
    private Thread th;
    // 线程消亡的标识符
    private boolean flag;
    private Context context;
    private Paint paint;
    // 首先声明6个容量的位图数组
    private Bitmap wifiBmp[] = new Bitmap[6];
    // 对应6张图片的Id
    private int[] wifiBmpId = new int[] { R.drawable.wifi01, R.drawable.wifi02,
            R.drawable.wifi03, R.drawable.wifi04, R.drawable.wifi05,
            R.drawable.wifi06 };
    // 记录当前播放帧
    private int currentFrame;

    public WifiAnimSurfaceView(Context context) {
        super(context);
        // 实例SurfaceView
        sfh = this.getHolder();
        // 为SurfaceView添加状态监听
        sfh.addCallback(this);
        this.context=context;
        paint = new Paint();
        // 画笔无锯齿
        paint.setAntiAlias(true);
        // 将帧图放入帧数组
        for (int i = 0; i < wifiBmp.length; i++) {
            wifiBmp[i] = BitmapFactory.decodeResource(this.getResources(),
                    wifiBmpId[i]);
        }
    }

    /**
     * SurfaceView 视图创建，响应此函数
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        // 实例线程
        th = new Thread(this);
        // 启动线程
        th.start();
    }
    /**
     * SurfaceView 视图状态发生改变时，响应此函数
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    /**
     * SurfaceView 视图消亡时，响应此函数
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            startDraw();
            currentFrame++;
            // 当播放的当前帧大于并且等于帧数组时，重置当前帧为0
            if (currentFrame >= wifiBmp.length) {
                currentFrame = 0;
            }
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startDraw() {
        try {
            canvas=sfh.lockCanvas();
            if(canvas!=null){
                //刷屏，画布白色
                canvas.drawColor(Color.WHITE);
                // 绘制位图,居中显示
                canvas.drawBitmap(
                        wifiBmp[currentFrame],
                        this.getWidth() / 2 - wifiBmp[currentFrame].getWidth()
                                / 2,
                        this.getHeight() / 2
                                - wifiBmp[currentFrame].getHeight() / 2, paint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sfh.unlockCanvasAndPost(canvas);
        }
    }
}
