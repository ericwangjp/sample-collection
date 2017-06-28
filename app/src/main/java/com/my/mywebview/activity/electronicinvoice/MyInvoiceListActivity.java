package com.my.mywebview.activity.electronicinvoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.ListPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInvoiceListActivity extends CommonBaseActivity {

    @BindView(R.id.llayout_root)
    RelativeLayout llayoutRoot;
    @BindView(R.id.title)
    RelativeLayout llayoutTitle;
    private TitleHelper title;
    private ListPopupWindow popupWindow;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_invoice_list);
        ButterKnife.bind(this);
//        rootLayout = f(R.id.title);
//		bankNameTxt = f(R.id.txt_bank_name);
//		cardUserNameEdt = f(R.id.edt_card_user_name);
//		addSureBtn = f(R.id.btn_add_sure);
//		bankNameLlayout = f(R.id.llayout_bank_name);
//		bankNameRightRlayout = f(R.id.rlayout_bank_name_right);
//		takeBankCardImg = f(R.id.img_take_bank_card);
//		takeIdCardImg = f(R.id.img_take_id_card);
//		supportCrdTxt = f(R.id.txt_support_crd);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initTitleBar() {
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_invoice_manager);
        title.getLeft().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setPicrRight(R.drawable.ic_addto);
        title.getRight().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {
                    popupWindow = new ListPopupWindow(MyInvoiceListActivity.this);
                }
                popupWindow.showPopupWindow(llayoutTitle);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onListener() {
    }

    @Override
    protected void handler(Message msg) {

    }

    /**
     * 信用卡信息维护
     */
    private void CrCrdInfSet() {
//		CrCrdInfSet = new CrCrdInfSet();
//		CrCrdInfSet.request.userNo = ApplicationController.userInfo.userNo;// 内部用户号必输项
//		CrCrdInfSet.request.cardNo = cardNum;// 待还款信用卡号必输项
//		CrCrdInfSet.request.cardName = cardUserName;// 卡户名添加时为必输项
//		CrCrdInfSet.request.capCorg = bnkNo;// 银行编号 添加时为必输项
//		CrCrdInfSet.request.capCorgName = bankName;// 银行简称添加时为必输项
//		CrCrdInfSet.request.billDate = "";// 账单日
//		CrCrdInfSet.request.repayDate = "";// 还款日
//		CrCrdInfSet.request.operType = "0";// 操作类型 0-添加信用卡,1-删除信用卡
//		CrCrdInfSet.request.rcvIdNo = ApplicationController.userInfo.idNoFull;// 收款人身份证
//		// 添加时为必输
//		loadingDialog.show();
//		// 上送请求数据
//		getData(Service.MR_CR_CRD_INFO_SET, CrCrdInfSet.request.toMap(), this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

}
