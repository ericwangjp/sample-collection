package com.my.mywebview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.my.mywebview.R;
import com.my.mywebview.activity.electronicinvoice.InvoiceTitleActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.ToastUtils;


/**
 * Created by WJP on 2017/6/9.
 */

public class ListPopupWindow extends PopupWindow{
    private final int h;
    private final int w;
    private View conentView;
    private Activity activity;
    protected Bundle paras;
    public ListPopupWindow( Activity context){
        this.activity=context;
        if(paras==null){
            paras=new Bundle();
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.list_popup_window, null);
        h = context.getWindowManager().getDefaultDisplay().getHeight();
        w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 2-30);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.ListPopupWindow);

        conentView.findViewById(R.id.tv_invoice_title).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //do something you need here
                ToastUtils.show(arg0.getContext(),"点击了“发票抬头”！");
                ListPopupWindow.this.dismiss();
                JumpUtils.invokeActivity(arg0.getContext(), InvoiceTitleActivity.class,"",paras);
            }
        });
        conentView.findViewById(R.id.tv_sweeep_invoice).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // do something before signing out
                ToastUtils.show(arg0.getContext(),"点击了“扫码开票”！");
                ListPopupWindow.this.dismiss();
//                if (PermissionChecker.lacksPermission(arg0.getContext(), Manifest.permission.CAMERA)) {
//                    PermissionChecker.requestPermissionInner(activity, 0x111, Manifest.permission.CAMERA);
//                }else {
//                    if (PageUtil.cameraIsCanUse(arg0.getContext())){
//                        JumpUtils.invokeActivity(arg0.getContext(), CaptureActivity.class,"",paras);
//                    }
//                }
            }
        });
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, w / 2, 5);
        } else {
            this.dismiss();
        }
    }
}
