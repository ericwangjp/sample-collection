package com.my.mywebview.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.GaussionBlurUtil;
import com.my.mywebview.view.RightAndWrongAnimView;

import java.io.InputStream;

import static com.my.mywebview.R.id.myView;


public class FuzzyPictureActivity extends CommonBaseActivity {
    private TitleHelper title;
    private ImageView img1,img2,imgBlur1,imgBlur2;
    private RightAndWrongAnimView rightAndWrongAnimView;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_picture);
        img1= (ImageView) findViewById(R.id.img1);
        img2= (ImageView) findViewById(R.id.img2);
        imgBlur1= (ImageView) findViewById(R.id.img_blur1);
        imgBlur2= (ImageView) findViewById(R.id.img_blur2);
        rightAndWrongAnimView= (RightAndWrongAnimView) findViewById(myView);
    }
    protected void initTitleBar() {
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_blur);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void initData() {
        super.initData();
        img1.setImageResource(R.drawable.myphoto);
        imgBlur1.setImageResource(R.drawable.myphoto);
        //得到Resources对象
        Resources r = mContext.getResources();
        //以数据流的方式读取资源
        InputStream is = r.openRawResource(R.raw.myphoto);
        BitmapDrawable bmpDraw = new BitmapDrawable(is);
        Bitmap bmp = bmpDraw.getBitmap();
        img2.setImageBitmap(GaussionBlurUtil.createBlurBitmap(bmp,10));
        imgBlur2.setImageBitmap(GaussionBlurUtil.createBlurBitmap(bmp,10));
//        //设置失败
//        rightAndWrongAnimView.setSucces(false);
//        //模拟请求网络操作
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                rightAndWrongAnimView.setFish(true);
//            }
//        }, 4000);
        //设置成功
        rightAndWrongAnimView.setSucces(true);
        //模拟请求网络操作
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rightAndWrongAnimView.setFish(true);
            }
        }, 4000);
    }
}
