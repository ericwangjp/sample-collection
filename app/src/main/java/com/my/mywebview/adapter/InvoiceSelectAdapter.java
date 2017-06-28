package com.my.mywebview.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.model.FalseData;
import com.my.mywebview.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJP on 2017/6/13.
 */

public class InvoiceSelectAdapter extends BaseAdapter{
    private Context context;
    private List<FalseData>datas;
    private LayoutInflater layoutInflater;
    private int selectPosition;
    /** 存储ListView 的 item中的View */
    private SparseArray<View> mViews;
    /** 存放convertView */
    private View item;
    private int visibily=View.GONE;
    private setDataultCallBack setDataultCallBack;
    private deleteCallBack deleteCallBack;

    public InvoiceSelectAdapter(Context context, List<FalseData> datas,
                                setDataultCallBack setDataultCallBack,deleteCallBack deleteCallBack) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViews = new SparseArray<>();
        this.setDataultCallBack=setDataultCallBack;
        this.deleteCallBack=deleteCallBack;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelectedPosition(int selectedPosition){
        this.selectPosition=selectedPosition;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_invoice_manager,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tvName= (TextView) convertView.findViewById(R.id.txt_cust_name);
            viewHolder.tvFlag= (TextView) convertView.findViewById(R.id.txt_invoice_flag);
            viewHolder.tvRbtn= (TextView) convertView.findViewById(R.id.txt_default_title);
            viewHolder.imgDef= (ImageView) convertView.findViewById(R.id.img_default);
            viewHolder.selectBtn= (RadioButton) convertView.findViewById(R.id.rbtn_select);
            viewHolder.llayoutDefault= (LinearLayout) convertView.findViewById(R.id.llayout_set_default);
            viewHolder.llayoutRoot= (LinearLayout) convertView.findViewById(R.id.llayout_item);
            convertView.setTag(viewHolder);
            item = convertView;
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(datas.get(position).bnkName);
        viewHolder.llayoutDefault.setVisibility(visibily);
        if(visibily==View.VISIBLE){
            viewHolder.imgDef.setVisibility(View.GONE);
        }else{
            viewHolder.imgDef.setVisibility(View.VISIBLE);
        }
        viewHolder.tvFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"点击了个人/删除！");
                deleteCallBack.onDeleteClickListener(position);
            }
        });
        viewHolder.llayoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"点击了条目！");
            }
        });
        viewHolder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataultCallBack.onSetDefaultClickListener(position);
            }
        });
        if(selectPosition == position){
            viewHolder.selectBtn.setChecked(true);
        }else{
            viewHolder.selectBtn.setChecked(false);
        }
        return convertView;
    }
    public class ViewHolder{
        TextView tvName,tvFlag,tvRbtn;
        ImageView imgDef;
        RadioButton selectBtn;
        LinearLayout llayoutDefault,llayoutRoot;
    }

    /**
     * 获取view
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t == null) {
            t = (T) item.findViewById(id);
            mViews.put(id, t);
        }
        return t;
    }
    /**
     * 设置可见
     */
    public void setVisibility( int visible) {
        this.visibily=visible;
    }
    /**
     * 更新适配器数据源
     */
    public void refresh(ArrayList<FalseData> mData){
        if (mData == null) {
            mData = new ArrayList<>();
        }
        this.datas = mData;
        notifyDataSetChanged();
    }

    /**
     * 设置默认回调
     */
    public interface  setDataultCallBack{
        void onSetDefaultClickListener(int position);
    }

    /**
     * 删除回调
     */
    public interface  deleteCallBack{
        void onDeleteClickListener(int position);
    }
}
