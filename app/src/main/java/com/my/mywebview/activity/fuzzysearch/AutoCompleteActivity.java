package com.my.mywebview.activity.fuzzysearch;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.autocomplete.SearchAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoCompleteActivity extends CommonBaseActivity {
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.search)
    AutoCompleteTextView search;
    private TitleHelper title;
    private String[] str = {"大大大", "大大小", "大小大", "大小小", "小大大", "小大小", "小大小", "小小小"};


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_fuzzy_search_tip);
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
        // 自动提示适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        // 支持拼音检索
        SearchAdapter<String> adapter = new SearchAdapter<String>(this,
                android.R.layout.simple_list_item_1, str, SearchAdapter.ALL);
        search.setAdapter(adapter);
    }

    @Override
    protected void onListener() {
        super.onListener();
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });
    }
}
