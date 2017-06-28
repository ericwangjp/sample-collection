package com.my.mywebview.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.adapter.RcvListViewAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RcvGridViewActivity extends CommonBaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private List<String> mDatas;
    private RcvListViewAdapter mAdapter;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_rcv_grid_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++){
            mDatas.add("" + (char) i);
        }
        recycleview.setLayoutManager(new GridLayoutManager(this,4));
        recycleview.addItemDecoration(new DividerGridItemDecoration(this));
        mAdapter=new RcvListViewAdapter(mDatas);
        recycleview.setAdapter(mAdapter);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_rcvgridview_style);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
