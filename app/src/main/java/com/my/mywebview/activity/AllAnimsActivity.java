package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllAnimsActivity extends CommonBaseActivity {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_all_anim)
    Button btnAllAnim;
    private TitleHelper title;
    private Animation animation;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation scaleAnimation;
    private TranslateAnimation translateAnimation;
    private RotateAnimation rotateAnimation;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_all_anims);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_all_anims);
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
//        渐变动画
        alphaAnimation=new AlphaAnimation(0.1f,2f);
        alphaAnimation.setDuration(6000);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(scaleAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        缩放动画
        scaleAnimation=new ScaleAnimation(0.0f,2f,0.0f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(5000);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(translateAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        位移动画
        translateAnimation=new TranslateAnimation(10,200,5,300);
        translateAnimation.setDuration(5000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(rotateAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        旋转动画
        rotateAnimation=new RotateAnimation(30,692,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(5000);
    }

    @OnClick({R.id.img, R.id.btn_alpha, R.id.btn_scale, R.id.btn_translate, R.id.btn_rotate, R.id.btn_all_anim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.btn_alpha:
                /**
                 * 使用XML中的动画效果 第一个参数Context为程序的上下文 第二个参数id为动画XML文件的引用
                 */
                animation= AnimationUtils.loadAnimation(this,R.anim.anim_show_alpha);
                img.startAnimation(animation);
                break;
            case R.id.btn_scale:
                animation= AnimationUtils.loadAnimation(this,R.anim.anim_show_scale);
                img.startAnimation(animation);
                break;
            case R.id.btn_translate:
                animation= AnimationUtils.loadAnimation(this,R.anim.anim_show_translate);
                img.startAnimation(animation);
                break;
            case R.id.btn_rotate:
                animation= AnimationUtils.loadAnimation(this,R.anim.anim_show_rotate);
                img.startAnimation(animation);
                break;
            case R.id.btn_all_anim:
                img.startAnimation(alphaAnimation);
                break;
        }
    }
}
