package com.my.mywebview.activity.anims;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoneyAnimActivity extends CommonBaseActivity {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    private TitleHelper title;
    private AnimationDrawable drawable;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_water_flow_anim);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_anims_red_packge);
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
        img.setImageResource(R.drawable.money_anim_open);
        drawable = (AnimationDrawable) img.getDrawable();
    }


    @OnClick({R.id.start, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                drawable.start();
                break;
            case R.id.stop:
                drawable.stop();
                break;
        }
    }
}
