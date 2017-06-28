package com.my.mywebview.activity.cutout;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectNoCutActivity extends CommonBaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.btn_pick_photo)
    Button btnPickPhoto;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.dialog_layout)
    LinearLayout dialogLayout;
    private TitleHelper title;
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    /**
     * 开启相机
     */
    private Button btn_take_photo;
    /**
     * 开启图册
     */
    private Button btn_pick_photo;
    /**
     * 取消
     */
    private Button btn_cancel;
    /**
     * 获取到的图片路径
     */
    private String picPath;
    private Intent lastIntent;
    private Uri photoUri;
    /**
     * 从Intent获取图片路径的KEY
     */
    public static final String KEY_PHOTO_PATH = "photo_path";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_select_no_cut);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        lastIntent = getIntent();
        btnTakePhoto.setOnClickListener(this);
        btnPickPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_pic_no_cut);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_take_photo : // 开启相机
                takePhoto();
                break;
            case R.id.btn_pick_photo : // 开启图册
                pickPhoto();
                break;
            case R.id.btn_cancel : // 取消操作
                this.finish();
                break;
            default :
                break;
        }
    }
    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
            /***
             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
             * 如果不使用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
             */
            ContentValues values = new ContentValues();
            photoUri = this.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        } else {
            Toast.makeText(getApplicationContext(), "内存卡不存在",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            doPhoto(requestCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, Intent data) {
        if (requestCode == SELECT_PIC_BY_PICK_PHOTO) {// 从相册取图片，有些手机有异常情况，请注意
            if (data == null) {
                Toast.makeText(getApplicationContext(), "选择图片文件出错",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                Toast.makeText(getApplicationContext(), "选择图片文件出错",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            cursor.close();
        }
        if (picPath != null
                && (picPath.endsWith(".png") || picPath.endsWith(".PNG")
                || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
//            在需要获取图片的页面可以调用startActivity方法，然后在onActivityResult方法中获取图片路径
            lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
//            setResult(Activity.RESULT_OK, lastIntent);
            Toast.makeText(getApplicationContext(), "图片路径是："+picPath,
                    Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "选择图片文件不正确",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
