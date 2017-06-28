package com.my.mywebview.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.adapter.StickyHeaderAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.GroupInfo;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.SectionDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StickyHeaderActivity extends CommonBaseActivity {
    @BindView(R.id.title_view_line)
    View titleViewLine;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private TitleHelper title;
    private List<String> mDatas;
    private StickyHeaderAdapter mAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_sticky_header);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_rcvstickyheader);
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
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 56;i++) {
            mDatas.add(i+" test ");
        }
        mAdapter=new StickyHeaderAdapter(mDatas);
        recycleview.setAdapter(mAdapter);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(layoutmanager);
        recycleview.addItemDecoration(new SectionDividerDecoration(this, new SectionDividerDecoration.GroupInfoCallback() {
            @Override
            public GroupInfo getGroupInfo(int position) {
                /**
                 * 分组逻辑，这里为了测试每5个数据为一组。大家可以在实际开发中
                 * 替换为真正的需求逻辑
                 */
                int groupId = position / 5;
                int index = position % 5;
                GroupInfo groupInfo = new GroupInfo(groupId,groupId+"");
                groupInfo.setGroupLength(5);
                groupInfo.setPosition(index);
                return groupInfo;
            }
        }));
    }
}
