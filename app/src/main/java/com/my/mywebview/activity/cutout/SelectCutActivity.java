package com.my.mywebview.activity.cutout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCutActivity extends CommonBaseActivity {
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.btn_pick_photo)
    Button btnPickPhoto;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.dialog_layout)
    LinearLayout dialogLayout;
    @BindView(R.id.img_head)
    ImageView imgHead;
    private TitleHelper title;
    private String[] items = new String[]{"选择本地图片", "拍照"};
    /**
     * 头像名称
     */
    private static final String IMAGE_FILE_NAME = "image.jpg";

    /**
     * 请求码
     */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private boolean mSaving;
    private static final String OUTPUT_PIC_PATH=Environment.getExternalStorageDirectory().getPath();
    private String imagePathsThrow;
    private Uri oldPhoto;
    private Drawable cropDrawable;
    private Bitmap photo;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_select_cut);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_pic_can_cut);
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
        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    /**
     * 显示选择对话框
     */
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("设置头像")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0 :
                                Intent intentFromGallery = new Intent();
                                intentFromGallery.setType("image/*"); // 设置文件类型
                                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intentFromGallery,IMAGE_REQUEST_CODE);
                                break;
                            case 1 :
                                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 判断存储卡是否可以用，可用进行存储
                                String state = Environment.getExternalStorageState();
                                if (state.equals(Environment.MEDIA_MOUNTED)) {
                                    File path = Environment
                                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                    File file = new File(path, IMAGE_FILE_NAME);
                                    intentFromCapture.putExtra(
                                            MediaStore.EXTRA_OUTPUT,
                                            Uri.fromFile(file));
                                }

                                startActivityForResult(intentFromCapture,CAMERA_REQUEST_CODE);
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 结果码不等于取消时候
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE :
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE :
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        File path = Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RESULT_REQUEST_CODE : // 图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        oldPhoto=uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            cropDrawable = new BitmapDrawable(this.getResources(), photo);
//            imgHead.setImageDrawable(cropDrawable);
            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), oldPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Rect rect=new Rect(0, 0, cropDrawable.getIntrinsicWidth(), cropDrawable.getIntrinsicHeight());
            drawCropPic(mBitmap,rect);
        }
    }
    /**
     * 按区域裁剪图片，对框外区域添加蒙层
     * @param mBitmap 被裁剪图片
     * @param r 裁剪区域
     */
    private void drawCropPic(Bitmap mBitmap,Rect r) {
        try {

            final Bitmap croppedImage;
            // If we are circle cropping, we want alpha channel, which is the
            // third param here.
            int w = mBitmap.getWidth();
            int h = mBitmap.getHeight();
            croppedImage = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
            Rect allrect = new Rect(0, 0, w, h);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAlpha(80);
            Canvas canvas = new Canvas(croppedImage);
            canvas.drawBitmap(mBitmap, allrect, allrect, paint);
            // 计算左边位置
            int left = w/4 - cropDrawable.getIntrinsicWidth() / 4;
            // 计算上边位置
            int top = h/4 - cropDrawable.getIntrinsicHeight() / 4;
            Rect mDestRect = new Rect(0, top,w, w+top);
            Paint mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBitPaint.setFilterBitmap(true);
            mBitPaint.setDither(true);
            canvas.drawBitmap(photo,r,mDestRect,mBitPaint);
//            canvas.drawBitmap(mBitmap, r, r, null);
//            Drawable qTag = cropDrawable;
            //可以在此绘制框的标签
//            int x1  = r.left;
//            int y1  = r.top;
//            qTag.setBounds(5, 5,x1 + (qTag.getIntrinsicWidth())/2,y1 + (qTag.getIntrinsicHeight())/2);
//            qTag.draw(canvas);
            //保存名称
//            Calendar calendar = Calendar.getInstance();//获取当前日历对象
//            Date d = calendar.getTime();
//            SimpleDateFormat time = new SimpleDateFormat("yyMMddHHmmssSSS");
//            String timestr = time.format(d);
//            final String cropPath =OUTPUT_PIC_PATH + timestr + ".jpg";
            imgHead.setImageBitmap(croppedImage);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
