package com.my.mywebview.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.log.LogManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

//需要动态获取权限的类名上注释
@RuntimePermissions
public class PermissionsDispatcherActivity extends CommonBaseActivity {

    @BindView(R.id.tv_permissons)
    TextView tvPermissons;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_multi)
    Button btnMulti;

    private TitleHelper title;
    private static final int CALL_CAMERA_REQUEST_CODE = 1;
    private static final int MULTI_REQUEST_CODE = 2;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_permissions_dispatcher);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_permission_dispatcher);
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

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionsDispatcherActivityPermissionsDispatcher.requsetMyPermissonsWithCheck(PermissionsDispatcherActivity.this);
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionsDispatcherActivityPermissionsDispatcher.requestMultiPermissionsWithCheck(PermissionsDispatcherActivity.this,MULTI_REQUEST_CODE);
            }
        });
    }

    /***********************************************************************************************
     * 以下注解方法前面不能带有private修饰符
     * *********************************************************************************************
     */
    //在需要获取权限的地方注释
    @NeedsPermission(Manifest.permission.CAMERA)
    void requsetMyPermissons() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, CALL_CAMERA_REQUEST_CODE);
    }

    //提示用户为什么需要此权限
    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开照相机拍照权限,确定去授权吗？", request);
    }
    @OnShowRationale({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS,Manifest.permission.READ_PHONE_STATE})
    void showRationaleForMulti(PermissionRequest request) {
        showRationaleDialog("使用此功能需要打开XXX权限,确定去授权吗？", request);
    }

    //用户拒绝了授权
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        ToastUtils.show(this, "你拒绝了权限，该功能不可用");
    }
    @OnPermissionDenied({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS,Manifest.permission.READ_PHONE_STATE})
    void onMultiDenied() {
        ToastUtils.show(this, "你拒绝了权限，该功能不可用");
    }

    //用户选择的不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        ToastUtils.show(this, "不再允许询问该权限，该功能不可用");
    }
    @OnNeverAskAgain({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS,Manifest.permission.READ_PHONE_STATE})
    void onMultiNeverAskAgain() {
        ToastUtils.show(this, "不再允许询问该权限，该功能不可用");
    }

    private void showRationaleDialog(String messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    /**
     * 动态一次申请多个权限
     */
    @NeedsPermission({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS,Manifest.permission.READ_PHONE_STATE})
    void requestMultiPermissions(int requestCode){
        String sms= getSmsInPhone();
        tvResult.setText(sms);
    }
    public String getSmsInPhone()
    {
        final String SMS_URI_ALL = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";

        StringBuilder smsBuilder = new StringBuilder();

        try{
            ContentResolver cr = getContentResolver();
            String[] projection = new String[]{"_id", "address", "person",
                    "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                String date;
                String type;

                int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");

                do{
                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);
                    smsbody = cur.getString(smsbodyColumn);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
                    date = dateFormat.format(d);

                    int typeId = cur.getInt(typeColumn);
                    if(typeId == 1){
                        type = "接收";
                    } else if(typeId == 2){
                        type = "发送";
                    } else {
                        type = "";
                    }

                    smsBuilder.append("[");
                    smsBuilder.append(name+",");
                    smsBuilder.append(phoneNumber+",");
                    smsBuilder.append(smsbody+",");
                    smsBuilder.append(date+",");
                    smsBuilder.append(type);
                    smsBuilder.append("] ");

                    if(smsbody == null) smsbody = "";
                }while(cur.moveToNext());
            } else {
                smsBuilder.append("no result!");
            }

            smsBuilder.append("getSmsInPhone has executed!");
        } catch(SQLiteException ex) {
            LogManager.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return smsBuilder.toString();
    }
}
