package com.my.mywebview.activity.anims;

import android.os.Bundle;

import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.WaterFlowSurfaceView;

public class WaterFlowAnimActivity extends CommonBaseActivity {

//    @BindView(R.id.water_flow_surfaceview)
//    WaterFlowSurfaceView waterFlowSurfaceview;

    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
//        setContentView(R.layout.activity_water_flow_anim);
//        ButterKnife.bind(this);
        setContentView(new WaterFlowSurfaceView(this));
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
//        title = new TitleHelper(this);
//        title.setPicLeft(R.mipmap.ic_back);
//        title.setTextLeft(R.string.common_back);
//        title.setTextCenter(R.string.title_anims_water_flow);
//        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
