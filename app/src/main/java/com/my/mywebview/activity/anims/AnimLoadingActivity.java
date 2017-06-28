package com.my.mywebview.activity.anims;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimLoadingActivity extends CommonBaseActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.llayout_btn)
    LinearLayout llayoutBtn;

    private TitleHelper title;
    private AnimationDrawable animationDrawable;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_anim_loading);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_frame_anims_loading);
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
        img.setImageResource(R.drawable.anim_loading_list);
        animationDrawable= (AnimationDrawable) img.getDrawable();
    }

    @OnClick({R.id.img, R.id.btn_start, R.id.btn_stop, R.id.llayout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.btn_start:
                animationDrawable.start();
                break;
            case R.id.btn_stop:
                animationDrawable.stop();
                break;
            case R.id.llayout_btn:
                break;
        }
    }
}
