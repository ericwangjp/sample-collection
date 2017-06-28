package com.my.mywebview.activity.anims;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.ScreenUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AllPropertyActivity extends CommonBaseActivity {

    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_all_property);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_anims_property);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.img1, R.id.img2,R.id.img3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img1:
//                ObjectAnimator.ofFloat(img1,"rotationX",0.0f,380f).setDuration(500).start();

                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                        0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                        0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                        0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
                break;
            case R.id.img2:
//                自由落体
                verticalFall(img2);
//                抛物线
//                paoWuXian(img2);
                break;
            case R.id.img3 :
//AnimatorSet使用
                moveTogether(img3);
                break;
        }
    }
    //多动画同时运动
    private void moveTogether(final ImageView img3) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(img3, "scaleX", 0.2f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(img3, "scaleY", 0.5f, 2f);
        AnimatorSet animationSet=new AnimatorSet();
        animationSet.setDuration(3000);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.playTogether(anim1,anim2);
        animationSet.start();
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                moveOneByOne(img3);
            }
        });
    }
    //多动画按顺序执行
    private void moveOneByOne(ImageView img3){
        float mx = img3.getX();
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(img3,"scaleX",1.0f,2f);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(img3,"scaleY",1.0f,2f);
        ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(img3,"x",mx,0f);
        ObjectAnimator objectAnimator4=ObjectAnimator.ofFloat(img3,"X",mx);
        ObjectAnimator objectAnimator5=ObjectAnimator.ofFloat(img3,"scaleX",1.0f,2f);
        ObjectAnimator objectAnimator6=ObjectAnimator.ofFloat(img3,"scaleY",1.0f,2f);
        ObjectAnimator objectAnimator7=ObjectAnimator.ofFloat(img3,"y",mx,0f);
        ObjectAnimator objectAnimator8=ObjectAnimator.ofFloat(img3,"y",mx);
        /**
         * anim1,anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animatorSet=new AnimatorSet();
//        animatorSet支持链式设置，但是必须按照以下写法，否则执行顺序不对
        animatorSet.play(objectAnimator1).with(objectAnimator2);
        animatorSet.play(objectAnimator2).with(objectAnimator3);
        animatorSet.play(objectAnimator4).after(objectAnimator3);
        animatorSet.play(objectAnimator4).with(objectAnimator5);
        animatorSet.play(objectAnimator5).with(objectAnimator6);
        animatorSet.play(objectAnimator6).with(objectAnimator7);
        animatorSet.play(objectAnimator8).after(objectAnimator7);

        animatorSet.setDuration(2000);
        animatorSet.start();

    }
    //自由落体运动
    private void verticalFall(final View view) {
        final ValueAnimator valueAnimator=ValueAnimator.ofFloat(0, ScreenUtils.getScreenHeight(this)
                -view.getHeight());
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(1000).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                paoWuXian(img2);
            }
        });
    }
    //    抛物线
    private void paoWuXian(final View view){
        final ValueAnimator valueAnimator=new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0,0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator() {
            // fraction = t / duration
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                // x方向200px/s ，则y方向0.5 * 10 * t *t
//              fraction为一个分数，fraction=当前运动时间/总运动时间
                //给出了终点位置是屏幕右下角
                PointF f=new PointF();
                f.x=200*fraction*3;
                f.y=0.5f*200*(fraction*3)*(fraction*3);
                return f;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF f= (PointF) valueAnimator.getAnimatedValue();
                view.setX(f.x);
                view.setY(f.y);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
                fadeOut(view);
            }
        });

    }
    //    监听动画过程 淡出 再 消失
    private void fadeOut(final View view){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"alpha",0.5f);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd");
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null)
                    parent.removeView(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "onAnimationRepeat");
            }
        });
        objectAnimator.start();
//        动画结束简短监听方式
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//            }
//        });
    }
}
