package com.my.mywebview.activity.cutout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicCutActivity extends CommonBaseActivity {
    @BindView(R.id.btn_pick_img)
    Button btnPickImg;
    @BindView(R.id.btn_save)
    Button btnSave;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_pic_cut);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_pic_cut);
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
                JumpUtils.invokeActivity(PicCutActivity.this,SelectNoCutActivity.class);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(PicCutActivity.this,SelectCutActivity.class);
            }
        });
    }
}
