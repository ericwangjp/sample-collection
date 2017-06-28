package com.my.mywebview.activity.anims;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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

public class PropertyAnimXmlActivity extends CommonBaseActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.start_xml)
    Button startXml;
    @BindView(R.id.start_single_anim)
    Button startSingleAnim;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_property_anim_xml);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_anims_property_xml);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.img, R.id.start_xml, R.id.start_single_anim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.start_xml:
//                加载xml属性动画
                Animator animator= AnimatorInflater.loadAnimator(this,R.animator.scalexy);
                img.setPivotX(0);//左上角为缩放中心
                img.setPivotY(0);
                //显式的调用invalidate
                img.invalidate();
                animator.setTarget(img);
                animator.start();
                break;
            case R.id.start_single_anim:
                Animator animator1= AnimatorInflater.loadAnimator(this,R.animator.scalex);
                animator1.setTarget(img);
                animator1.start();
                break;
        }
    }
}
