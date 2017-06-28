package com.my.mywebview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.mywebview.R;

import java.util.List;

/**
 * Created by WJP on 2017/4/21.
 */

public class RcvGalleryAdapter extends RecyclerView.Adapter<RcvGalleryAdapter.rcvGalleryViewHolder> {
    private List<Integer> mDatas;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    public RcvGalleryAdapter(Context context, List<Integer> datats) {
        this.mContext=context;
        this.mDatas=datats;
    }

    @Override
    public rcvGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_rcv_gallery,parent,false);
        rcvGalleryViewHolder rcvGalleryViewHolder=new rcvGalleryViewHolder(view);
        rcvGalleryViewHolder.img= (ImageView) view.findViewById(R.id.gallery_item_image);
        rcvGalleryViewHolder.tvTitle= (TextView) view.findViewById(R.id.gallery_item_text);
        return rcvGalleryViewHolder;
    }

    @Override
    public void onBindViewHolder(final rcvGalleryViewHolder holder, final int position) {
        holder.img.setImageResource(mDatas.get(position));
        holder.itemView.setBackgroundResource(R.drawable.btn_square_corner_selector);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickLitener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public class  rcvGalleryViewHolder extends RecyclerView.ViewHolder{

        public rcvGalleryViewHolder(View itemView) {
            super(itemView);
        }
        ImageView img;
        TextView tvTitle;
    }
    /**
     * ItemClick的回调接口
     * @author zhy
     *
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
