package com.my.mywebview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.my.mywebview.R;

import java.util.List;
import java.util.Map;

/**
 * 这是一个关于ListView中的单选按钮的DEMO。
 * 
 * @author 山鹰1985
 * 
 *         http://blog.csdn.net/u012137924
 */
public class ListViewAdapter extends BaseAdapter {
	private List<Map<String, String>> list;
	private Context context;
	private int selectID;
	private OnMyCheckChangedListener mCheckChange;

	// 构造函数，用作初始化各项数据
	public ListViewAdapter(Context context, List<Map<String, String>> list) {
		this.context = context;
		this.list = list;
	}

	// 获取ListView的item总数
	public int getCount() {
		return list.size();
	}

	// 获取ListView的item
	public Object getItem(int position) {
		return getItem(position);
	}

	// 获取ListView的item的ID
	public long getItemId(int position) {
		return position;
	}

	// 自定义的选中方法
	public void setSelectID(int position) {
		selectID = position;
	}

	// 获取item的视图及其中含有的操作
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewCache viewCache;

		/**
		 * 这个是网上流行的适配器缓存View写法(软引用原理)，就不多说了。
		 */
		if (convertView == null) {
			viewCache = new ViewCache();
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			viewCache.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_layout);
			viewCache.itemID = (TextView) convertView.findViewById(R.id.id);
			viewCache.itemName = (TextView) convertView.findViewById(R.id.name);
			viewCache.radioBtn = (RadioButton) convertView.findViewById(R.id.radioBtn);

			convertView.setTag(viewCache);
		} else {
			viewCache = (ViewCache) convertView.getTag();
		}
		
		viewCache.itemID.setText(list.get(position).get("ID"));
		viewCache.itemName.setText(list.get(position).get("Name"));

		//核心方法，判断单选按钮被按下的位置与之前的位置是否相等，然后做相应的操作。
		if (selectID == position) {
			viewCache.linearLayout.setBackgroundColor(Color.parseColor("#ABCCFF"));
			viewCache.radioBtn.setChecked(true);
		} else {
			viewCache.linearLayout.setBackgroundColor(0);
			viewCache.radioBtn.setChecked(false);
		}

		// 单选按钮的点击事件监听
		viewCache.radioBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//这一句的意思跟下面的一样，不过这个是itemClick的点击监听，而下面的是RadioButton的点击监听。
				selectID = position;
				
				/**
				 * 在MyListView中使用mListViewAdapter.setOncheckChanged
				 * 来监听RadioButton的点击事件，（当然，首先要判空）
				 * 当我们按下单选按钮时，我们把按下的item的位置赋值给selectID
				 * ，然后在上面的if语句中判断当前点击的位置与selectID的位置
				 * 是否相等，如果不相等，那么说明按下了新的位置，那么就把原来位置上的选择取消掉， 
				 * 在新的位置让单选按钮显示选中状态就可以了。
				 */
				if (mCheckChange != null)
					mCheckChange.setSelectID(selectID);
			}
		});

		return convertView;
	}

	// 回调函数，很类似OnClickListener吧，呵呵
	public void setOncheckChanged(OnMyCheckChangedListener l) {
		mCheckChange = l;
	}

	// 回調接口
	public interface OnMyCheckChangedListener {
		void setSelectID(int selectID);
	}

	// 缓存类
	class ViewCache {
		LinearLayout linearLayout;
		TextView itemID, itemName;
		RadioButton radioBtn;
	}
}
