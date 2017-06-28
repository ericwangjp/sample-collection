package com.my.mywebview.activity.dropdownbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DropDownActivity extends CommonBaseActivity {

    @BindView(R.id.btn_city_select)
    Button btnCitySelect;
    @BindView(R.id.btn_custom_spinner)
    Button btnCustomSpinner;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_drop_down);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_custome_spinner);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onListener() {
        super.onListener();
        btnCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(DropDownActivity.this,CitySelectDropDownActivity.class);
            }
        });
        btnCustomSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(DropDownActivity.this,CustomSpinnerActivity.class);
            }
        });
    }
}
