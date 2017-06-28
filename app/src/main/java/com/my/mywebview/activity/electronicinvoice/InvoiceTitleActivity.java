package com.my.mywebview.activity.electronicinvoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.my.mywebview.R;
import com.my.mywebview.adapter.InvoiceSelectAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.FalseData;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.view.ListPopupWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InvoiceTitleActivity extends CommonBaseActivity {

    @BindView(R.id.lv_invoice)
    ListView lvInvoice;
    private TitleHelper title;
    private ListPopupWindow popupWindow;
    private RelativeLayout rootLayout;
    private ListView listView;
    private boolean isShow = false;
    //    假数据源
    private InvoiceSelectAdapter adapter;
    //    private CommonBaseAdapter<FalseData> adapter;
    private ArrayList<FalseData> dataList; // 数据源
    private FalseData falseData = new FalseData();

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_invoice_title);
        ButterKnife.bind(this);
//        listView = f(R.id.lv_invoice);
//		llayoutDefault = f(R.id.llayout_set_default);
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
        title.setTextCenter(R.string.pop_title_invoice);
        title.getLeft().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setTextRightR(R.string.pop_title_invoice_manage);
        title.getRight().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                    adapter.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                } else {
                    isShow = true;
                    adapter.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            falseData.bnkName = i + "name";
            falseData.bnkNo = i + "111";
            falseData.dayAmtLimit = i + "222";
            falseData.stokAmtLimit = i + "333";
            falseData.imgUrl = "";
            falseData.hotline = i + "12358";
            dataList.add(falseData);
        }
        adapter = new InvoiceSelectAdapter(this, dataList, new InvoiceSelectAdapter.setDataultCallBack() {
            @Override
            public void onSetDefaultClickListener(int position) {
                ToastUtils.show(InvoiceTitleActivity.this, "点击了设为默认！" + position);
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        }, new InvoiceSelectAdapter.deleteCallBack() {
            @Override
            public void onDeleteClickListener(int position) {

            }
        });
//        adapter = new CommonBaseAdapter<FalseData>( dataList, R.layout.item_invoice_manager ) {
//
//            @Override
//            public void bindView(final CommonViewHolder holder, final FalseData obj ) {
//                holder.setText( R.id.txt_cust_name, obj.bnkName );
//                GlideUtils.loadUrlPic( mContext, Config.getConfigWebUrlBnkicon() + obj.bnkNo.toLowerCase( Locale.getDefault() )
//                        + Config.getConfigWebUrlBnkiconSuffix(), (ImageView) holder.getView( R.id.img_default ), R.drawable.ic_notloaded, R.drawable.ic_notloaded );
//                    holder.setOnClickListener(R.id.llayout_item, new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            ToastUtils.show(InvoiceTitleActivity.this, "点击了条目");
//                        }
//                    });
//                    holder.setOnClickListener(R.id.txt_invoice_flag, new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            ToastUtils.show(InvoiceTitleActivity.this,"点击了个人/删除！");
//                        }
//                    });
//                    holder.setOnClickListener(R.id.img_default, new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            ToastUtils.show(InvoiceTitleActivity.this, "点击了设为默认");
//                        }
//                    });
//                    holder.setOnClickListener(R.id.rbtn_select, new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            ToastUtils.show(InvoiceTitleActivity.this, "点击了rbtn");
//                            for(int i=0;i<dataList.size();i++){
//                            }
//                            holder.setViewSelected(R.id.rbtn_select,true);
//                            adapter.refresh(dataList);
//                        }
//                    });
//                if(isShow){
//                    holder.setVisibility(R.id.llayout_set_default,View.VISIBLE);
//                }else{
//                    holder.setVisibility(R.id.llayout_set_default,View.GONE);
//                }
//            }
//        };
        lvInvoice.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.setSelectedPosition(position);
//                adapter.notifyDataSetChanged();
//            }
//        });
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
