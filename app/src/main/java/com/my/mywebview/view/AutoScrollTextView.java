package com.my.mywebview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by WJP on 2017-3-7 .
 * 
 * 文字自动滚动实现跑马灯效果
 * 使用方法如下(此时在XML里设置的部分相关属性会无效):
 * 启动公告滚动条
 *      autoScrollTextView = (AutoScrollTextView)findViewById(R.id.TextViewNotice);
 *      autoScrollTextView.init(getWindowManager());
 *      autoScrollTextView.startScroll();
 * 如果想改变跑马灯的文字内容或者文字效果，则在调用完setText方法之后，需要再调用一下init方法，重新进行初始化和相关参数的计算
 */
public class AutoScrollTextView extends TextView implements OnClickListener{
	
		public final static String TAG = AutoScrollTextView.class.getSimpleName();
	
		private float textLength = 0f;//文本长度
		private float viewWidth = 0f;
		private float step = 0f;//文字的横坐标
		private float y = 0f;//文字的纵坐标
		private float temp_view_plus_text_length = 0.0f;//用于计算的临时变量
		private float temp_view_plus_two_text_length = 0.0f;//用于计算的临时变量
		public boolean isStarting = false;//是否开始滚动
		private Paint paint = null;//绘图样式
		private String text = "";//文本内容
	
		@SuppressLint("NewApi") 
		public AutoScrollTextView(Context context, AttributeSet attrs,
				int defStyleAttr, int defStyleRes) {
			super(context, attrs, defStyleAttr, defStyleRes);
			initView();
		}
	
		public AutoScrollTextView(Context context, AttributeSet attrs,
				int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			initView();
		}
	
		public AutoScrollTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
			initView();
		}
	
		public AutoScrollTextView(Context context) {
			super(context);
			initView();
		}
	
		private void initView() {
			setOnClickListener(this);
		}
		public void init(WindowManager windowManager){
			paint = getPaint();
			//这里可以通过修改画笔的颜色来更改字体颜色，也可以在activity中通过代码来设置字体颜色
	//		paint.setColor(Color.RED);
			text = getText().toString();
			textLength = paint.measureText(text);
			viewWidth = getWidth();
			if(viewWidth == 0){
				if(windowManager != null){
					Display display = windowManager.getDefaultDisplay();
					viewWidth = display.getWidth();
				}
			}
			step = textLength;
			temp_view_plus_text_length = viewWidth + textLength;
			temp_view_plus_two_text_length = viewWidth + textLength * 2;
			y = getTextSize() + getPaddingTop();
		}
		@Override
		public Parcelable onSaveInstanceState() {
			Parcelable superState =  super.onSaveInstanceState();
			SavedState ss = new SavedState(superState);
			ss.step = step;
			ss.isStarting = isStarting;
			return ss;
		}
		@Override
		public void onRestoreInstanceState(Parcelable state) {
			if (!(state instanceof SavedState)) {
				super.onRestoreInstanceState(state);
				return;
			}
			SavedState ss = (SavedState)state;
			super.onRestoreInstanceState(ss.getSuperState());
	
			step = ss.step;
			isStarting = ss.isStarting;
		}
		public static class SavedState extends BaseSavedState {
			public boolean isStarting = false;
			public float step = 0.0f;
			SavedState(Parcelable superState) {
				super(superState);
			}
			@Override
			public void writeToParcel(Parcel out, int flags) {
				super.writeToParcel(out, flags);
				out.writeBooleanArray(new boolean[]{isStarting});
				out.writeFloat(step);
			}
	
			public static final Creator<SavedState> CREATOR
			= new Creator<SavedState>() {
	
				public SavedState[] newArray(int size) {
					return new SavedState[size];
				}
				@Override
				public SavedState createFromParcel(Parcel in) {
					return new SavedState(in);
				}
			};
			private SavedState(Parcel in) {
				super(in);
				boolean[] b = null;
				in.readBooleanArray(b);
				if(b != null && b.length > 0)
					isStarting = b[0];
				step = in.readFloat();
			}
		}
	
		public void startScroll()
		{
			isStarting = true;
			invalidate();
		}
	
	
		public void stopScroll()
		{
			isStarting = false;
			invalidate();
		}
	
		@Override
		public void onDraw(Canvas canvas) {
	//		super.onDraw(canvas);加上次句属性正常，但是会出现文字覆盖现象
			canvas.drawText(text, temp_view_plus_text_length - step, y, paint);
			if(!isStarting){
				return;
			}
			step += 0.5;//0.5为文字滚动速度
			if(step > temp_view_plus_two_text_length)
				step = textLength;
			invalidate();
		}
		@Override
		public void onClick(View v) {
			if(isStarting)
				stopScroll();
			else
				startScroll();
	
		}
		@Override
		public void setText(CharSequence text, BufferType type) {
			super.setText(text, type);
			startScroll();
		}
		@Override
		public void destroyDrawingCache() {
			super.destroyDrawingCache();
			stopScroll();
		}
}