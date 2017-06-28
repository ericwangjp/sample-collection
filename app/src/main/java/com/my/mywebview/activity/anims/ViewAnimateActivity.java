package com.my.mywebview.activity.anims;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.DensityUtil;
import com.my.mywebview.utils.ScreenUtils;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.log.LogManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewAnimateActivity extends CommonBaseActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_view_animate);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_view_animate);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 简单的使用img.animate().alpha(0).y(mScreenHeight / 2).setDuration(1000).start()就能实现动画~~不过需要SDK11，
     此后在SDK12，SDK16又分别添加了withStartAction和withEndAction用于在动画前，和动画后执行一些操作。
     当然也可以.setListener(listener)等操作
     * @param view
     */

    @OnClick({R.id.img, R.id.start, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.start:
                img.animate().alpha(0).y(ScreenUtils.getScreenHeight(this)/2)
                        .setDuration(2000).withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        LogManager.i("START");
                    }
                }).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        LogManager.i("END");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img.setY(DensityUtil.dip2px(ViewAnimateActivity.this,50));
                                img.setAlpha(1.0f);
                            }
                        });
                    }
                }).start();
//                等同以上
//                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
//                        0f, 1f);
//                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0,
//                        mScreenHeight / 2, 0);
//                ObjectAnimator.ofPropertyValuesHolder(mBlueBall, pvhX, pvhY).setDuration(1000).start();
                break;
            case R.id.stop:
                break;
        }
    }
}
