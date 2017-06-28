package com.my.mywebview.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WJP on 2017/4/19.
 */

public class DividerStaggeredGridDecoration extends RecyclerView.ItemDecoration{
    private int dividerSize;
    public DividerStaggeredGridDecoration(Context context,int dividerSize) {
        this.dividerSize=dividerSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=dividerSize;
        outRect.right=dividerSize;
        outRect.bottom=dividerSize;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=dividerSize;
        }
    }
}
