package com.my.mywebview.activity.anims;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.my.mywebview.R;
import com.my.mywebview.activity.AllAnimsActivity;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllAnimShowActivity extends CommonBaseActivity {

    @BindView(R.id.llayout_tween_anim_xml)
    LinearLayout llayoutTweenAnimXml;
    @BindView(R.id.llayout_tween_anim)
    LinearLayout llayoutTweenAnim;
    @BindView(R.id.llayout_tween_anim_view)
    LinearLayout llayoutTweenAnimView;
    @BindView(R.id.llayout_tween_anim_surfaceView)
    LinearLayout llayoutTweenAnimSurfaceView;
    @BindView(R.id.llayout_frame_anim)
    LinearLayout llayoutFrameAnim;
    @BindView(R.id.llayout_property_anim)
    LinearLayout llayoutPropertyAnim;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.llayout_property_anim_xml)
    LinearLayout llayoutPropertyAnimXml;
    @BindView(R.id.llayout_layout_animations)
    LinearLayout llayoutLayoutAnimations;

    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_all_anim_show);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_all_anims_show);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.llayout_tween_anim_xml, R.id.llayout_tween_anim, R.id.llayout_tween_anim_view,
            R.id.llayout_tween_anim_surfaceView, R.id.llayout_frame_anim, R.id.llayout_property_anim,
            R.id.scroll_view,R.id.llayout_property_anim_xml,R.id.llayout_layout_animations,R.id.llayout_view_animate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_tween_anim_xml:
                JumpUtils.invokeActivity(mContext, AllAnimsActivity.class);
                break;
            case R.id.llayout_tween_anim:
                JumpUtils.invokeActivity(mContext, AnimLoadingActivity.class);
                break;
            case R.id.llayout_tween_anim_view:
                JumpUtils.invokeActivity(mContext, WifiAnimActivity.class);
                break;
            case R.id.llayout_tween_anim_surfaceView:
                JumpUtils.invokeActivity(mContext, WaterFlowAnimActivity.class);
                break;
            case R.id.llayout_frame_anim:
                JumpUtils.invokeActivity(mContext, MoneyAnimActivity.class);
                break;
            case R.id.llayout_property_anim:
                JumpUtils.invokeActivity(mContext, AllPropertyActivity.class);
                break;
            case R.id.scroll_view:
                break;
            case R.id.llayout_property_anim_xml:
                JumpUtils.invokeActivity(mContext, PropertyAnimXmlActivity.class);
                break;
            case R.id.llayout_layout_animations:
                JumpUtils.invokeActivity(mContext, LayoutAnimationsActivity.class);
                break;
            case R.id.llayout_view_animate:
                JumpUtils.invokeActivity(mContext, ViewAnimateActivity.class);
                break;
        }
    }
}
