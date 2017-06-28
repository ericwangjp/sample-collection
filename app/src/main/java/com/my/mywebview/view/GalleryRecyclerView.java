package com.my.mywebview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WJP on 2017/4/21.
 * 自定义recyclerView实现图片联动的gallery效果
 */

public class GalleryRecyclerView extends RecyclerView {
    //    记录当前第一个view
    private View mCurrentView;
    //     滚动时回调的接口
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(new galleryScrollListener());
    }

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);

        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildPosition(mCurrentView));
        }
    }


    class galleryScrollListener extends OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View newView = getChildAt(0);

            if (mItemScrollChangeListener != null) {
                if (newView != null && newView != mCurrentView) {
                    mCurrentView = newView;
                    mItemScrollChangeListener.onChange(mCurrentView,
                            getChildPosition(mCurrentView));

                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }
}