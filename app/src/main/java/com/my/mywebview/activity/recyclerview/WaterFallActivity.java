package com.my.mywebview.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.adapter.RcvListViewAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.DividerStaggeredGridDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterFallActivity extends CommonBaseActivity {
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private TitleHelper title;
    private List<String> mDatas;
    private RcvListViewAdapter mAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_water_fall);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_waterfall, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.reduce:
                mAdapter.removeData(1);
                break;
        }
        return true;
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++){
            mDatas.add("" + (char) i);
        }
        recycleview.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recycleview.addItemDecoration(new DividerStaggeredGridDecoration(this,5));
        // 设置item动画
        recycleview.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new RcvListViewAdapter(mDatas);
        recycleview.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new RcvListViewAdapter.OnItemClickLitener(){

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(WaterFallActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(WaterFallActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_rcvwaterfall_style);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
