package com.my.mywebview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.model.GroupInfo;

/**
 * Created by WJP on 2017/4/19.
 */

public class SectionDividerDecoration extends RecyclerView.ItemDecoration{
    private static final String TAG = "DividerDecoration";

    private GroupInfoCallback mCallback;
    private int mHeaderHeight;
    private int mDividerHeight;
    //用来绘制Header上的文字
    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private float mTextOffsetX;

    public SectionDividerDecoration(Context context,GroupInfoCallback callback) {
        this.mCallback = callback;
//        正常分割线的高度
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.dimens2dp);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.dimens35dp);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.dimens50dp);

        mHeaderHeight = (int) Math.max(mHeaderHeight,mTextSize);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mFontMetrics = mTextPaint.getFontMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(R.color.colorAccent));

    }

//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(view);
//            long groupId = callback.getGroupId(position);
//            if (groupId < 0) return;
//            String textLine = callback.getGroupFirstLine(position).toUpperCase();
//            if (position == 0 || isFirstInGroup(position)) {
//                float top = view.getTop() - topGap;
//                float bottom = view.getTop();
//                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
//                c.drawText(textLine, left, bottom, textPaint);//绘制文本
//            }
//        }
//    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();

        for ( int i = 0; i < childCount; i++ ) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);


            if ( mCallback != null ) {

                GroupInfo groupinfo = mCallback.getGroupInfo(index);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                //屏幕上第一个可见的 ItemView 时，i == 0;
                if ( i != 0 ) {


                    //只有组内的第一个ItemView之上才绘制
                    if ( groupinfo.isFirstViewInGroup() ) {

                        int top = view.getTop() - mHeaderHeight;

                        int bottom = view.getTop();
                        drawHeaderRect(c, groupinfo, left, top, right, bottom);

                    }

                } else {

                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View

                    int top = parent.getPaddingTop();


                    if ( groupinfo.isLastViewInGroup() ) {
                        int suggestTop = view.getBottom() - mHeaderHeight;
                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if ( suggestTop < top ) {
                            top = suggestTop;
                        }
                    }

                    int bottom = top + mHeaderHeight;

                    drawHeaderRect(c, groupinfo, left, top, right, bottom);
                }

            }
        }

    }
    private void drawHeaderRect(Canvas c, GroupInfo groupinfo, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left,top,right,bottom,mPaint);

        float titleX =  left + mTextOffsetX;
        float titleY =  bottom - mFontMetrics.descent;
        //绘制Title
        c.drawText(groupinfo.getTitle(),titleX,titleY,mTextPaint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        if ( mCallback != null ) {
            GroupInfo groupInfo = mCallback.getGroupInfo(position);

            //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
            if ( groupInfo != null && groupInfo.isFirstViewInGroup() ) {
                outRect.top = mHeaderHeight;
            } else {
                outRect.top = mDividerHeight;
            }
        }
    }

    public interface GroupInfoCallback {

        GroupInfo getGroupInfo(int position);
    }
    public GroupInfoCallback getCallback() {
        return mCallback;
    }

    public void setCallback(GroupInfoCallback callback) {
        this.mCallback = callback;
    }
}
