package com.my.mywebview.activity.electronicinvoice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.my.mywebview.R;
import com.my.mywebview.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个关于ListView中的单选按钮的DEMO。
 * 该文档可以实现两种方式的listview单选
 * 两种模式均不需要设置listview的choicemode为singleChoice，也无需特意设置radiobutton的属性
 * 
 * @author 山鹰1985 
 * 
 * 			http://blog.csdn.net/u012137924
 */
public class MyListView extends Activity implements OnItemClickListener {
	private ListView mListView;
	private List<Map<String, String>> list;
	private ListViewAdapter mListViewAdapter;
	/**
	 * 下面两行是模拟数据，当然可以由自己决定传什么值。
	 */
	private String[] itemID = { "1", "2", "3","4","5","6","7","8","9","10","11","12" ,"13","14","15"};
	private String[] itemName = { "张三", "李四", "王二" ,"李白","杜甫","那谁","刘能","赵四","老村长",
            "狗剩","挫人","二愣子","大傻子","逗你玩","开玩笑"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 15; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", itemID[i]);
			map.put("Name", itemName[i]);
			list.add(map);
		}

		mListView = (ListView) this.findViewById(R.id.listView);
		mListViewAdapter = new ListViewAdapter(this, list);
		mListView.setAdapter(mListViewAdapter);
		mListView.setOnItemClickListener(this);

		//自定义回调函数
		mListViewAdapter.setOncheckChanged(new ListViewAdapter.OnMyCheckChangedListener() {

			@Override
			public void setSelectID(int selectID) {
				mListViewAdapter.setSelectID(selectID);				//选中位置
				mListViewAdapter.notifyDataSetChanged();		//刷新适配器
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		注释掉以下两句即可实现只有点击radiobutton才可以实现选中，此时点击item可以处理别的需求
//		打开以下注释则无论是点击listview的item还是点击radiobutton均可以实现单选，此时二者绑定一致，点击item不可以处理别的事物

//		mListViewAdapter.setSelectID(position);				//选中位置
//		mListViewAdapter.notifyDataSetChanged();		//刷新适配器
	}

}
