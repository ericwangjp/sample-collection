package com.my.mywebview.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.mywebview.R;

import java.util.List;

/**
 * Created by WJP on 2017/4/20.
 */

public class StickyHeaderAdapter extends Adapter<StickyHeaderAdapter.StickyHeaderViewHolder>{
    List<String> data;

    public StickyHeaderAdapter(List<String> data) {
        this.data = data;
    }
    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public StickyHeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_listview, parent, false);
        StickyHeaderViewHolder stickyHeaderViewHolder=new StickyHeaderViewHolder(view);
        return stickyHeaderViewHolder;
    }

    @Override
    public void onBindViewHolder(StickyHeaderViewHolder holder, int position) {
        if (data != null && data.size() > 0 ) {
            String text = data.get(position);
            holder.textView.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    public class StickyHeaderViewHolder extends ViewHolder{
        public TextView textView;
        public StickyHeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_num);
            textView.setTextSize(38.0f);
        }
    }
}
