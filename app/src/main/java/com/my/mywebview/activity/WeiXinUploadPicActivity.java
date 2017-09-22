package com.my.mywebview.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.adapter.WeChatUploadAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.OnRecyclerViewItemTouchListener;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.WeiXinUploadPicCallBack;
import com.my.mywebview.utils.log.LogManager;
import com.my.mywebview.view.DividerStaggeredGridDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 仿微信发动态上传图片，有bug待修复
 */
public class WeiXinUploadPicActivity extends CommonBaseActivity {
    @BindView(R.id.tv_delete)
    TextView tvDelete;  //删除区域
    @BindView(R.id.rcv_img)
    RecyclerView rcvImg;
    @BindView(R.id.et_content)
    EditText etContent; //内容编辑区域
    private TitleHelper title;
    private ItemTouchHelper itemTouchHelper;
    private WeiXinUploadPicCallBack myCallBack;
    private static final int REQUEST_IMAGE = 1001;
    public static final int MAX_IMG_SIZE = 9;
    private ArrayList<String> originImages;//原始图片
    private ArrayList<String> dragImages;//压缩长宽后图片
    private WeChatUploadAdapter weChatUploadAdapter;
    private String plusPath;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_upload_pic);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_weinxin_upload_pic);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setTextRightR(R.string.txt_choose_pic);
        title.getRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create(mContext.getApplicationContext())
                        .showCamera(true) // 是否显示相机. 默认为显示
                        .count(9) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//                        .single() // 单选模式
                        .multi() // 多选模式, 默认模式;
//                        .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                        .start(WeiXinUploadPicActivity.this, REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void onListener() {
        super.onListener();
        rcvImg.addOnItemTouchListener(new OnRecyclerViewItemTouchListener(rcvImg) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if (originImages.get(vh.getAdapterPosition()).contains(getString(R.string.glide_plus_icon_string))) {//打开相册
                    MultiImageSelector.create()
                            .showCamera(true)
                            .count(MAX_IMG_SIZE - originImages.size() + 1)
                            .multi()
                            .start(WeiXinUploadPicActivity.this, REQUEST_IMAGE);
                } else {
                    ToastUtils.show(WeiXinUploadPicActivity.this,"预览图片");
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
//如果item不是最后一个，则执行拖拽
                if (vh.getLayoutPosition() != originImages.size() - 1) {
                    itemTouchHelper.startDrag(vh);
                }
            }
        });


    }

    @OnClick({R.id.tv_delete, R.id.et_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                break;
            case R.id.et_content:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                originImages = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                //添加按钮图片资源
                try {
                    plusPath = getString(R.string.glide_plus_icon_string) + getPackageManager().getPackageInfo(getPackageName(),0).packageName + "/mipmap/" + R.drawable.mine_btn_plus;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();//添加按键，超过9张时在adapter中隐藏
                }
                originImages.add(plusPath);
                LogManager.i("返回图片结果：",originImages);
                if(weChatUploadAdapter==null){
                    weChatUploadAdapter=new WeChatUploadAdapter(originImages,mContext.getApplicationContext());
                }
                rcvImg.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                rcvImg.addItemDecoration(new DividerStaggeredGridDecoration(this,5));
                // 设置item动画
                rcvImg.setItemAnimator(new DefaultItemAnimator());
                rcvImg.setAdapter(weChatUploadAdapter);
                if(myCallBack==null)
                    myCallBack=new WeiXinUploadPicCallBack(weChatUploadAdapter,originImages,originImages,mContext.getApplicationContext());
                itemTouchHelper = new ItemTouchHelper(myCallBack);
                itemTouchHelper.attachToRecyclerView(rcvImg);//绑定RecyclerView
                myCallBack.setDragListener(new WeiXinUploadPicCallBack.DragListener() {
                    @Override
                    public void deleteState(boolean delete) {
                        if (delete) {
                            tvDelete.setBackgroundResource(R.color.colorFA3E3D);
                            tvDelete.setText(getResources().getString(R.string.post_delete_tv_s));
                        } else {
                            tvDelete.setText(getResources().getString(R.string.post_delete_tv_d));
                            tvDelete.setBackgroundResource(R.color.colorF5F5F5);
                        }
                    }

                    @Override
                    public void dragState(boolean start) {
                        if (start) {
                            tvDelete.setVisibility(View.VISIBLE);
                        } else {
                            tvDelete.setVisibility(View.GONE);
                        }
                    }
                });


            }
        }
    }
}
