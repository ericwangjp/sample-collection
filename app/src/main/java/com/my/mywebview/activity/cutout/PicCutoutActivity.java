package com.my.mywebview.activity.cutout;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicCutoutActivity extends CommonBaseActivity implements View.OnTouchListener {
    @BindView(R.id.btn_pick_img)
    Button btnPickImg;
    @BindView(R.id.img_picked)
    ImageView imgPicked;
    @BindView(R.id.btn_save)
    Button btnSave;
    private TitleHelper title;
    private static final int  REQUEST_CODE=666;
    private Bitmap alteredBitmap;
    private Canvas canvas;
    private Paint paint;
    private float downX;
    private float downY;
    private float upX;
    private float upY;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_pic_cutout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_pic_gesture);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onListener() {
        super.onListener();
        btnPickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        //保存已经涂鸦过的图片
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*
             * PNG :非常适合艺术线条和图形：始终保持所有数据
             * JPEG:非常适合带渐变的全彩图像，例如照片。是“有损的”编解码器，可设置质量
             */
                if (null != alteredBitmap) {
                    //创建一个新的uri
                    Uri imageFileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
                    try {
                        OutputStream imageFileOS = getContentResolver().openOutputStream(imageFileUri);//输出流
                        alteredBitmap.compress(Bitmap.CompressFormat.JPEG, 90, imageFileOS);//生成图片
                        Toast.makeText(PicCutoutActivity.this, "has saved", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d("bitmap", "has result ok");
            Uri uri = data.getData();
            int dw = getWindowManager().getDefaultDisplay().getWidth();
            int dh = getWindowManager().getDefaultDisplay().getHeight();
            try {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;//如果设置为true，本身不会返回
                Bitmap chooseBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
                int bw = opts.outWidth;//此时，chooseBitmap的值为null，但opts仍然获得其config
                int bh = opts.outHeight;
                int widthRatio = (int) Math.ceil(bw / (float) dw);
                int heightRatio = (int) Math.ceil(bh / (float) dh);
                if (widthRatio > 1 || heightRatio >1) {
                    if (widthRatio > heightRatio) {
                        opts.inSampleSize = widthRatio;//设置比例
                    } else {
                        opts.inSampleSize = heightRatio;
                    }
                }
                opts.inJustDecodeBounds = false;
                chooseBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
                Log.d("bitmap", "chooseBitmap is :" + chooseBitmap);
                alteredBitmap = Bitmap.createBitmap(chooseBitmap.getWidth(), chooseBitmap.getHeight(), chooseBitmap.getConfig());
                canvas = new Canvas(alteredBitmap);//画布
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);
                Matrix matrix = new Matrix();
                canvas.drawBitmap(chooseBitmap, matrix , paint);
                imgPicked.setImageBitmap(alteredBitmap);
                imgPicked.setOnTouchListener(PicCutoutActivity.this);//设置监听
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouch(View v,MotionEvent event) {
        Log.d("touch_draw", "ontouch()");
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
//                          upX = event.getX();
//                          upY = event.getY();//画直线的相关代码，可自己测试。同理，画圆椭圆都可在相关方法里设置
//                          canvas.drawLine(downX, downY, upX, upY, paint);
//                          mImageView.invalidate();
//                          break;
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upX = event.getX();
                upY = event.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                imgPicked.invalidate();
                downX = upX;
                downY = upY;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
