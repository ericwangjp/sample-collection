package com.my.mywebview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.mywebview.R;

import java.util.List;

/**
 * Created by WJP-ADMIN on 2017/3/20.
 */

public class RCViewAdapter extends RecyclerView.Adapter {
    //先定义两个ItemViewType，0代表头，1代表表格中间的部分
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    //数据源
    private List<String> dataList;
    //构造函数
    public RCViewAdapter(List<String> dataList) {
        this.dataList = dataList;
    }
    /**
     * 判断当前position是否处于第一个
     * @param position
     * @return
     */
    public boolean isHeader(int position) {
        return position == 0;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //在onCreateViewHolder方法中，我们要根据不同的ViewType来返回不同的ViewHolder
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            //对于Header，我们应该返回填充有Header对应布局文件的ViewHolder（在此我们返回的都是一个布局文件，请根据不同的需求做相应的改动）
            return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_header_layout, null));
        } else {
            //对于Body中的item，我们也返回所对应的ViewHolder
            return new BodyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rcv_header_layout, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (isHeader(position)) {
            //大家在这里面处理头，这里只有一个TextView，大家可以根据自己的逻辑做修改
            ((HeaderViewHolder)viewHolder).getTextView().setText("我是生活页面"+"\n"+"(RecyclerView使用方式之一)");
        }else {
            //其他条目中的逻辑在此
            ((BodyViewHolder) viewHolder).getTextView().setText(dataList.get(position - 1));
        }
    }
    /**
     * 总条目数量是数据源数量+1，因为我们有个Header
     * @return
     */
    @Override
    public int getItemCount() {
        return dataList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //如果是0，就是头，否则则是其他的item
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }
    /**
     * 给头部专用的ViewHolder，大家根据需求自行修改
     */
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    /**
     * 给GridView中的条目用的ViewHolder，里面只有一个TextView
     */
    public class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public BodyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
        public TextView getTextView() {
            return textView;
        }
    }
}
