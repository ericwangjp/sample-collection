package com.my.mywebview.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.utils.log.LogManager;

import java.util.List;

/**
 * Created by WJP on 2017/5/10.
 */

public class CustomSpinnerSelectAdapter extends ArrayAdapter {
    private List<String> dataSource;
    private Spinner spinner;
    private Context context;
    private LayoutInflater infalter;
    private int resource;
    private int textViewResourceId;

    public CustomSpinnerSelectAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, List dataSource, Spinner spinner) {
        super(context, resource, textViewResourceId);
        this.dataSource = dataSource;
        this.spinner = spinner;
        this.context = context;
        this.resource=resource;
        this.textViewResourceId=textViewResourceId;
        infalter = LayoutInflater.from(context);
        LogManager.i("构造数组长度",dataSource.size());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = infalter.inflate(resource, null);
        TextView text = (TextView) convertView
                .findViewById(textViewResourceId);
        text.setText(dataSource.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = infalter.inflate(R.layout.spinner_item_layout, null);
        }
            TextView label = (TextView) convertView.findViewById(R.id.spinner_item_label);
            ImageView check = (ImageView) convertView.findViewById(R.id.spinner_item_checked_image);
            label.setText(dataSource.get(position));
        if (spinner.getSelectedItemPosition() == position) {
            convertView.setBackgroundColor(context.getResources().getColor(
                    R.color.color2DC868));
            check.setImageResource(R.drawable.btn_circula_sel);
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(
                    R.color.colorE6EEA3));
            check.setImageResource(R.drawable.btn_circula_nor);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }
}
