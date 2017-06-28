package com.my.mywebview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.mywebview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJP on 2017/4/18.
 */

public class RcvListViewAdapter extends RecyclerView.Adapter<RcvListViewAdapter.ListViewHolder>{
    private List<String>datas;
    private List<Integer> height;
    private OnItemClickLitener mOnItemClickLitener;

    public RcvListViewAdapter(List<String>dataList) {
        this.datas=dataList;
        getRandomHeight(datas);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListViewHolder listViewHolder=new ListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_listview,parent,false));
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        /**
         * 得到item的LayoutParams布局参数
         * 仅瀑布流时使用
         */
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = height.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置item布局
        holder.itemView.setBackgroundResource(R.drawable.rcv_select_bg);

        holder.tv.setText(datas.get(position));
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v){
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v){
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return position;
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ListViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
    /**
     * 得到随机的Item的高度
     */
    private void getRandomHeight(List<String> list) {
        height = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            height.add((int) (200 + Math.random() * 400));
        }
    }

    /**
     * 在瀑布流中添加一个
     * @param position
     */
    public void addData(int position) {
        datas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    /**
     * 在瀑布流中删除一个
     * @param position
     */
    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 添加点击监听
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
