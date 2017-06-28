package com.my.mywebview.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.adapter.RcvGalleryAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RcvGalleryActivity extends CommonBaseActivity {

    @BindView(R.id.img_content)
    ImageView imgContent;
    @BindView(R.id.recyclerview_horizontal)
    GalleryRecyclerView recyclerviewHorizontal;
    private TitleHelper title;
    private List<Integer> mDatas;
    private RcvGalleryAdapter mAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_rcv_gallery);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.img1,
                R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
                R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9,
                R.drawable.img10, R.drawable.img11));
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewHorizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new RcvGalleryAdapter(this, mDatas);
        recyclerviewHorizontal.setAdapter(mAdapter);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_rcvgallery);
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
        mAdapter.setOnItemClickLitener(new RcvGalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RcvGalleryActivity.this, "点击了" + position, Toast.LENGTH_SHORT)
                        .show();
                imgContent.setImageResource(mDatas.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RcvGalleryActivity.this, "长按了" + position, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        recyclerviewHorizontal.setOnItemScrollChangeListener(new GalleryRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                imgContent.setImageResource(mDatas.get(position));
            }
        });
    }

}
