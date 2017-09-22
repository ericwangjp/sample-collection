package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.StringUtils;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JniTestActivity extends CommonBaseActivity {
    @BindView(R.id.edt_input_content)
    EditText edtInputContent;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_start_jni)
    Button btnStartJni;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_jni_test);
        ButterKnife.bind(this);
    }

    static {
        System.loadLibrary("myJni");
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_jni_test);
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
    }

    private native String fromJniMsg(String hiJni);

    @OnClick(R.id.btn_start_jni)
    public void onViewClicked() {
        String startMsg=edtInputContent.getText().toString();
        if(StringUtils.isBlank(startMsg)){
            ToastUtils.show(this,"请输入内容再开始！");
            return;
        }
        String msg = fromJniMsg(startMsg);
        tvResult.setText(msg);
    }
}
