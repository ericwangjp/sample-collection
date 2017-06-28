package com.my.mywebview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.model.DrowSpinnerBean;

import java.util.List;

/**
 * Created by WJP on 2017/5/9.
 */

public class CustomSpinnerAdapter extends BaseAdapter{
    private List<DrowSpinnerBean> list;
    private int layoutId;
    private Context context;

    public CustomSpinnerAdapter(List<DrowSpinnerBean> list, int layoutId, Context context) {
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            //加载item布局
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
            //获取实例化
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_view);
            //保存实例化的控件
            convertView.setTag(viewHolder);
        } else {
            //重新获取实例化的控件
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        viewHolder.imageView.setBackgroundResource(list.get(position).getImgId());
        viewHolder.textView.setText(list.get(position).getTitle());

        return convertView;
    }
    public class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
