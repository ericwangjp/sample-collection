package com.my.mywebview.activity.dropdownbox;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.my.mywebview.R;
import com.my.mywebview.adapter.CustomSpinnerSelectAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomSpinnerActivity extends CommonBaseActivity {
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    private TitleHelper title;
    private ArrayAdapter<?> adapter;
    private CustomSpinnerSelectAdapter customAdapter;
    private static final String str[]=new String[]{"请选择年级","小学","初中一年级（七年级）",
            "初中二年级（八年级）","初中三年级（九年级）","高中一年级","高中二年级（文科）",
            "高中二年级（理科）","高考理科班","高考文科班","大一","大二","大三"};
//    private List<String>datas= Arrays.asList(str);
    private List<String>datas;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_custome_spinner_drop);
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
//       spinner1完全使用系统的布局样式，快速实现spinner样式
        adapter= ArrayAdapter.createFromResource(this, R.array.all_nations, android.R.layout.simple_spinner_item);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter2 添加到spinner中
        spinner1.setAdapter(adapter);
        //设置默认值
        spinner1.setVisibility(View.VISIBLE);
 //       spinner2完全使用自定义的布局样式，快速实现spinner样式
        datas=new ArrayList<>();
        datas.add("请选择年级");
        datas.add("小学");
        datas.add("初中一年级（七年级）");
        datas.add( "初中二年级（八年级）");
        datas.add("初中三年级（九年级）");
        datas.add("高中一年级");
        datas.add("高中二年级（文科）");
        datas.add("高中二年级（理科）");
        datas.add("高考理科班");
        datas.add("高考文科班");
        datas.add("大一");
        datas.add("大二");
        datas.add("大三");
        customAdapter= new CustomSpinnerSelectAdapter(this,R.layout.spinner_checked_text,R.id.txt_check, datas,spinner2);
        customAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        spinner2.setAdapter(customAdapter);
        spinner2.setVisibility(View.VISIBLE);
    }
}
