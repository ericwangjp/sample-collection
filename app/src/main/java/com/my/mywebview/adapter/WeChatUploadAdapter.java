package com.my.mywebview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.my.mywebview.R;
import com.my.mywebview.activity.WeiXinUploadPicActivity;

import java.util.List;

/**
 * 微信图片上传适配器
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/8/31 14:02
 * @email wang_jp@murongtech.com
 */
public class WeChatUploadAdapter extends RecyclerView.Adapter<WeChatUploadAdapter.MyViewHolder>{

    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public WeChatUploadAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.item_wechat_pic,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position >= WeiXinUploadPicActivity.MAX_IMG_SIZE) {//图片已选完时，隐藏添加按钮
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
        }
        Glide.with(mContext).load(mDatas.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
