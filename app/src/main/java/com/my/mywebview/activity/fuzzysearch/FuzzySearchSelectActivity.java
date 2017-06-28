package com.my.mywebview.activity.fuzzysearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuzzySearchSelectActivity extends CommonBaseActivity {

    @BindView(R.id.btn_fuzzy_search)
    Button btnFuzzySearch;
    @BindView(R.id.btn_auto_complete)
    Button btnAutoComplete;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_search_select);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_fuzzy_search_select);
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
        btnFuzzySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(FuzzySearchSelectActivity.this,FuzzySearchActivity.class);
            }
        });
        btnAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(FuzzySearchSelectActivity.this,AutoCompleteActivity.class);
            }
        });
    }
}
