package com.my.mywebview.activity.anims;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutAnimationsActivity extends CommonBaseActivity implements CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.checkbox_appear)
    CheckBox checkboxAppear;
    @BindView(R.id.checkbox_change_appear)
    CheckBox checkboxChangeAppear;
    @BindView(R.id.checkbox_disapper)
    CheckBox checkboxDisapper;
    @BindView(R.id.checkbox_change_disappear)
    CheckBox checkboxChangeDisappear;
    @BindView(R.id.llayout_container)
    LinearLayout llayoutContainer;
    private TitleHelper title;
    private GridLayout gridLayout;
    private LayoutTransition layoutTransition;
    private int count;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_layout_animations);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_layout_anim);
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
//        创建一个gridlayout
        gridLayout=new GridLayout(this);
        gridLayout.setOrientation(GridLayout.HORIZONTAL);
//        设置每行有5个按钮
        gridLayout.setColumnCount(5);
//        添加到布局中
        llayoutContainer.addView(gridLayout);
//        默认动画全部开启
        layoutTransition=new LayoutTransition();
        gridLayout.setLayoutTransition(layoutTransition);

        checkboxAppear.setOnCheckedChangeListener(this);
        checkboxChangeAppear.setOnCheckedChangeListener(this);
        checkboxDisapper.setOnCheckedChangeListener(this);
        checkboxChangeDisappear.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.btn_add, R.id.checkbox_appear, R.id.checkbox_change_appear, R.id.checkbox_disapper, R.id.checkbox_change_disappear, R.id.llayout_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                final Button button=new Button(this);
                button.setText(++count+"");
                ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(200,150);
                gridLayout.addView(button,Math.min(1,gridLayout.getChildCount()),params);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridLayout.removeView(button);
                    }
                });
                break;
            case R.id.checkbox_appear:
                break;
            case R.id.checkbox_change_appear:
                break;
            case R.id.checkbox_disapper:
                break;
            case R.id.checkbox_change_disappear:
                break;
            case R.id.llayout_container:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        layoutTransition=new LayoutTransition();
//        layoutTransition.setAnimator(LayoutTransition.APPEARING,
//                (checkboxAppear.isChecked()?layoutTransition.getAnimator(LayoutTransition.APPEARING):null));
//        可实现切换动画自定义
        layoutTransition.setAnimator(LayoutTransition.APPEARING,
                (checkboxAppear.isChecked()? ObjectAnimator.ofFloat(buttonView, "scaleX", 0f, 1f):null));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                (checkboxChangeAppear.isChecked()?layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING):null));
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,
                (checkboxDisapper.isChecked()?layoutTransition.getAnimator(LayoutTransition.DISAPPEARING):null));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                (checkboxChangeDisappear.isChecked()?layoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING):null));
        gridLayout.setLayoutTransition(layoutTransition);
    }
}
