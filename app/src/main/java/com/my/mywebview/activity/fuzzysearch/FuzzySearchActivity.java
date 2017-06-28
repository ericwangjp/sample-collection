package com.my.mywebview.activity.fuzzysearch;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.GroupData;
import com.my.mywebview.utils.LocalGroupSearch;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuzzySearchActivity extends CommonBaseActivity {
    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.listview)
    ListView listview;
    private TitleHelper title;
    private GroupData groupData;
    private ArrayList<GroupData> model=new ArrayList<GroupData>();
//    为了查询不到数据删除关键词重新查询时恢复数据
    private ArrayList<GroupData> oldData=new ArrayList<GroupData>();
    //定义一个String数组用来显示ListView的内容
    private static final String[] strs = new String[] {
            "first", "second", "third", "fourth", "fifth","123","526","ligang",
            "熊大","光头强","赵四","倚天","落落","唐唐","成宇","韩愈","击鼓那个"
    };
    private SearchAdapter myAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_fuzzy_search);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        groupData=new GroupData();
//        for(int i=0;i<6;i++){
            groupData.setNaturalName("李白");
            groupData.setRoomId(1);
            groupData.setRoomName("huanghuang");
        model.add(groupData);
        oldData.add(groupData);
//    }
        groupData=new GroupData();
            groupData.setNaturalName("熊大");
            groupData.setRoomId(12);
            groupData.setRoomName("lili");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("光头强");
            groupData.setRoomId(8);
            groupData.setRoomName("dadi");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("倚天");
            groupData.setRoomId(3);
            groupData.setRoomName("cdhuang");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("123");
            groupData.setRoomId(6);
            groupData.setRoomName("grw");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("598");
            groupData.setRoomId(3);
            groupData.setRoomName("detgg");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("落落");
            groupData.setRoomId(8);
            groupData.setRoomName("kuihi");
        model.add(groupData);
        oldData.add(groupData);
        groupData=new GroupData();
            groupData.setNaturalName("354");
            groupData.setRoomId(9);
            groupData.setRoomName("rere");
        model.add(groupData);
        oldData.add(groupData);
//        myAdapter = new ArrayAdapter<GroupData>(this, android.R.layout.simple_list_item_1, model);
        myAdapter=new SearchAdapter(this,model);
        /*为ListView设置Adapter来绑定数据*/
        listview.setAdapter(myAdapter);
//        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strs));
    }

    @Override
    protected void onListener() {
        super.onListener();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
//                    此处注意数据源，当用户减少搜索关键词时，过滤掉的参数要再次展示
                    ArrayList<GroupData> listG = LocalGroupSearch.searchGroup(s, oldData);
                    if (listG != null && listG.size() > 0) {
                        myAdapter.refresh(listG);
                    } else {
                        ToastUtils.show(FuzzySearchActivity.this,"搜索为空");
//                        此处当用户增加搜索词导致无匹配项时，移除之前所有的匹配项
                        myAdapter.refresh(new ArrayList<GroupData>());
                        myAdapter.notifyDataSetChanged();
                    }
                } else {
//                    myAdapter.refresh(new ArrayList<GroupData>());
                    myAdapter.refresh(oldData);
                    myAdapter.notifyDataSetChanged();
//                    ToastUtils.show(FuzzySearchActivity.this,"请输入搜索关键词");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    class SearchAdapter extends BaseAdapter{
        public List<GroupData>datas;
        public SearchAdapter(Context context,List<GroupData>datas) {
            this.datas=datas;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(holder == null){
                convertView = LayoutInflater.from(FuzzySearchActivity.this).inflate(R.layout.item_listview, null);
                holder = new ViewHolder();
                holder.naturalName= (TextView) convertView.findViewById(R.id.tv_natural_name);
                holder.roomname= (TextView) convertView.findViewById(R.id.tv_room_name);
                holder.nameId= (TextView) convertView.findViewById(R.id.tv_id);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.naturalName.setText(datas.get(position).getNaturalName());
            holder.roomname.setText(datas.get(position).getRoomName());
            holder.nameId.setText(datas.get(position).getRoomId()+"");
            return convertView;
        }
         class ViewHolder {
            TextView naturalName;
            TextView roomname;
            TextView nameId;
        }
        public void refresh(ArrayList<GroupData> list) {
            model.clear();
            model.addAll(list);
            notifyDataSetChanged();
        }
    }
}
