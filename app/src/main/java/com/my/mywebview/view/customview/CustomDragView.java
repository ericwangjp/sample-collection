package com.my.mywebview.view.customview;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.my.mywebview.utils.log.LogManager;

/**
 * [类功能说明]
 *
 * @author WJP
 * @version Vx.x.x
 * @date 2017/9/13 16:08
 * @email wang_jp@murongtech.com
 */
public class CustomDragView extends LinearLayout{

    private ViewDragHelper viewDragHelper;
    private View dragView;
    private View autoBackView;
    private View edgeTraceView;
    private Point mAutoBackOriginPos = new Point();

    public CustomDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
//                表示可以捕获该view，可以根据传入的第一个view参数决定哪些可以捕获
//                return true;
                //edgeTraceView禁止直接移动,只能通过边界拖动
                return child == dragView || child == autoBackView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                对child移动的边界进行控制
//                return super.clampViewPositionHorizontal(child, left, dx);
//                return left;
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth() - getPaddingRight();
                final int newLeft = Math.min(Math.max(left,leftBound),rightBound);
                LogManager.i("leftBound",leftBound);
                LogManager.i("rightBound",rightBound);
                LogManager.i("newLeft",newLeft);
                LogManager.i("left",left);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
//                对child移动的边界进行控制
//                return super.clampViewPositionVertical(child, top, dy);
                return top;
            }

//            在边界拖动开始时调用
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                super.onEdgeDragStarted(edgeFlags, pointerId);
                viewDragHelper.captureChildView(edgeTraceView,pointerId);
            }

//            手指释放时调用
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                super.onViewReleased(releasedChild, xvel, yvel);
//                释放手指第二个view自动回到最初始位置
                if (releasedChild==autoBackView){
                    viewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x,mAutoBackOriginPos.y);
                    invalidate();
                }
            }
        });
//        设置在屏幕左边界拖动时生效
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
//        通过使用mDragger.shouldInterceptTouchEvent(event)来决定我们是否应该拦截当前的事件
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//        通过mDragger.processTouchEvent(event)处理事件
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = getChildAt(1);
        autoBackView = getChildAt(2);
        edgeTraceView = getChildAt(3);
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if(viewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x=autoBackView.getLeft();
        mAutoBackOriginPos.y=autoBackView.getTop();
    }
    /**
     * TextView全部加上clickable=true，意思就是子View可以消耗事件。再次运行，你会发现本来可以拖动的View不动（或者直接用button）
     * 因为，如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，在onTouchEvent的DOWN的时候就确定了
     * captureView。如果消耗事件，那么就会先走onInterceptTouchEvent方法，判断是否可以捕获，而在判断的过程中会去判断另外两个回调
     * 的方法：getViewHorizontalDragRange和getViewVerticalDragRange，只有这两个方法返回大于0的值才能正常的捕获
     * 如果你用Button测试，或者给TextView添加了clickable = true ，都记得重写下面这两个方法：
     * @Override
    public int getViewHorizontalDragRange(View child)
    {
    return getMeasuredWidth()-child.getMeasuredWidth();
    }

     @Override
     public int getViewVerticalDragRange(View child)
     {
     return getMeasuredHeight()-child.getMeasuredHeight();
     }

     方法的返回值应当是该childView横向或者纵向的移动的范围，当前如果只需要一个方向移动，可以只复写一个
     */

}
