package com.my.mywebview.activity.cutout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.activity.FaceRecognitionActivity;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CutoutViewsActivity extends CommonBaseActivity {

    @BindView(R.id.llayout_cutout)
    LinearLayout llayoutCutout;
    @BindView(R.id.llayout_picture_local)
    LinearLayout llayoutPictureLocal;
    @BindView(R.id.llayout_face_indify)
    LinearLayout llayoutFaceIndify;
    @BindView(R.id.llayout_gesture)
    LinearLayout llayoutGesture;
    @BindView(R.id.llayout_pic_cut)
    LinearLayout llayoutPicCut;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_cutout_views);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_cutout);
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
    }

    @Override
    protected void onListener() {
        super.onListener();
        llayoutPictureLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, LocalFuzzyActivity.class);
            }
        });
        llayoutFaceIndify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, FaceRecognitionActivity.class);
            }
        });
        llayoutCutout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, CutoutActivity.class);
            }
        });
        llayoutGesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, PicCutoutActivity.class);
            }
        });
        llayoutPicCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, PicCutActivity.class);
            }
        });
    }
}
