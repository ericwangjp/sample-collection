package com.my.mywebview.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EasyPermissionActivity extends CommonBaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.btn_request_single)
    Button btnRequestSingle;
    @BindView(R.id.btn_request_multi)
    Button btnRequestMulti;

    private TitleHelper title;
    private static final int SINGLE_REQUEST_CODE = 1;
    private static final int MULTI_REQUEST_CODE = 2;
    //所要申请的单个权限
    private String[] singlePerms = {Manifest.permission.CAMERA};
    //所要申请的多个权限
    private String[] multiPerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS};

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_easy_permission);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_easy_permission);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.btn_request_single, R.id.btn_request_multi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_request_single:
                if (EasyPermissions.hasPermissions(this, singlePerms)) {//检查是否获取该权限
                    ToastUtils.show(this, "已获取所有必须权限!");
                } else {
                    //第二个参数是被拒绝后再次申请该权限的解释
                    //第三个参数是请求码
                    //第四个参数是要申请的权限
                    EasyPermissions.requestPermissions(this, "该权限均为必要的权限，拒绝授权将导致该功能不可用！",
                            SINGLE_REQUEST_CODE, singlePerms);
                }
                break;
            case R.id.btn_request_multi:
                if (EasyPermissions.hasPermissions(this, multiPerms)) {//检查是否获取该权限
                    ToastUtils.show(this, "已获取所有必须权限!");
                } else {
                    //第二个参数是被拒绝后再次申请该权限的解释
                    //第三个参数是请求码
                    //第四个参数是要申请的权限
                    EasyPermissions.requestPermissions(this, "该权限均为必要的权限，拒绝授权将导致该功能不可用！",
                            MULTI_REQUEST_CODE, multiPerms);
                }
                break;
        }
    }

    //重写onRequestPermissionsResult将值传递给EasyPermissions.onRequestPermissionsResult();
    //固定写法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //请求后的回掉，授权成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //判断请求成功的权限数量和需要请求的权限数量是否相同，相同表示全部权限都请求成功
        if (perms.size() == singlePerms.length) {
            //获得或有权限后的逻辑
            ToastUtils.show(this, "申请的所有权限均已成功获得！");
        }
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            以下两种方法均调到APP信息设置界面（并非直接进入权限设置）
            new AppSettingsDialog.Builder(this).build().show();
//            showSettingDialog();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "用户在设置里设置权限返回", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private void showSettingDialog(){
        //如果选择了不再提示，这样就会进入这里，弹出提示框，让他直接去系统设置中打开权限
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("没有该权限将导致软件功能异常，现在就去设置里设置权限？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri packageURI = Uri.parse("package:" + getPackageName().toString());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}