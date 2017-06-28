package com.my.mywebview.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.view.CircularImage;

/**
 * Created by LXZ on 2016-08-12.
 */
public class TitleHelper {

    @SuppressWarnings("unused")
	private final String TAG = "TitleHelper";

    private RelativeLayout titleRootRlayout,titleLeftTextLayoutR,txtRightTopRlayout;
    private LinearLayout leftTitleLlayout, rightTitleLlayout;
    private ImageView leftTitleImg, centerTitleImg, rightTitleImg, rightTitleRImg;
    private TextView leftTitleTxt, leftTitleTxtR, centerTitleTxt, rightTitleLTxt, rightTitleRTxt,leftTitleTextR,rightTopTxt;
    private View root;
    private Activity context;
    private CircularImage headerImg,headerImgL;
    private View view;

    public TitleHelper( Activity context ) {
        this.root = context.getWindow().getDecorView();
        initView();
    }

    public TitleHelper( View root ) {
        this.root = root;
        initView();
    }

    private void initView() {
        titleRootRlayout = (RelativeLayout) root.findViewById( R.id.rlayout_title_root );
        if ( titleRootRlayout == null )
            titleRootRlayout = (RelativeLayout) LayoutInflater.from( context ).inflate( R.layout.title_common, null )
                    .findViewById( R.id.rlayout_title_root );
        leftTitleLlayout = (LinearLayout) root.findViewById( R.id.llayout_left_title );
        rightTitleLlayout = (LinearLayout) root.findViewById( R.id.llayout_right_title );
        leftTitleImg = (ImageView) root.findViewById( R.id.img_left_title );
        centerTitleImg = (ImageView) root.findViewById( R.id.img_center_title );
        rightTitleImg = (ImageView) root.findViewById( R.id.img_right_title );
        rightTitleRImg = (ImageView) root.findViewById( R.id.img_right_titleR );
        headerImg = (CircularImage) root.findViewById( R.id.img_header );
        leftTitleTxt = (TextView) root.findViewById( R.id.txt_left_titleL );
        leftTitleTxtR = (TextView) root.findViewById( R.id.txt_left_titleR );
        centerTitleTxt = (TextView) root.findViewById( R.id.txt_center_title );
        rightTitleLTxt = (TextView) root.findViewById( R.id.txt_right_titleL );
        rightTitleRTxt = (TextView) root.findViewById( R.id.txt_right_titleR );
        headerImgL = (CircularImage) root.findViewById( R.id.img_header_L );
        view = root.findViewById( R.id.title_view_line );
        titleLeftTextLayoutR = (RelativeLayout) root.findViewById( R.id.ll_left_title_layoutR );
        leftTitleTextR = (TextView) root.findViewById( R.id.ll_txt_left_titleR );
        rightTopTxt = (TextView) root.findViewById( R.id.txt_right_num );
        txtRightTopRlayout = (RelativeLayout) root.findViewById( R.id.rlayout_root_text_right );
        
        
    }

    private void changeItem( View target, int resourceID ) {
        if ( resourceID == 0 )
            target.setVisibility( View.GONE );
        else {
            target.setVisibility( View.VISIBLE );
            if ( target instanceof ImageView )
                ( (ImageView) target ).setImageResource( resourceID );
            else if ( target instanceof TextView )
                ( (TextView) target ).setText( resourceID );
        }
    }

    private void changeItem( TextView target, String string ) {
        if ( string == null )
            return;
        target.setText( string );
        target.setVisibility( View.VISIBLE );
    }

    public void setLineView( int resourceID ) {
        changeItem( view, resourceID );
    }

    public void setLineViewGone(  ) {
    	view.setVisibility(View.INVISIBLE);
    }
    public void setLineViewGoneG(  ) {
    	view.setVisibility(View.GONE);
    }
    
    public void setBGColor( int resourceID ) {
        titleRootRlayout.setBackgroundResource( resourceID );
    }
    public RelativeLayout getTitleRoot( ) {
        return titleRootRlayout;
    }
    public void setBGByColor( int resourceID ) {
    	titleRootRlayout.setBackgroundColor(resourceID);
    }
    public void setTextBGColor( int resourceID ) {
    	titleLeftTextLayoutR.setBackgroundResource( resourceID );
    }
    public void setRightTopTextBGColor( int resourceID ) {
    	txtRightTopRlayout.setBackgroundResource( resourceID );
    }

    public void setPicLeft( int resourceID ) {
        changeItem( leftTitleImg, resourceID );
    }
    
    public void setPicLeftHead( int resourceID ) {
        changeItem( headerImgL, resourceID );
    }

    public void setPicCenter( int resourceID ) {
        changeItem( centerTitleImg, resourceID );
    }

    public void setPicrRight( int resourceID ) {
        changeItem( rightTitleImg, resourceID );
    }

    public void setPicrRightR( int resourceID ) {
        changeItem( rightTitleRImg, resourceID );
    }

    public void setPicrRightHead( int resourceID ) {
        changeItem( headerImg, resourceID );
    }

    public void setTextLeft( int resourceID ) {
        changeItem( leftTitleTxt, resourceID );
    }

    public void setTextLeft( String string ) {
        changeItem( leftTitleTxt, string );
    }

    public void setTextLeftR( int resourceID ) {
        changeItem( leftTitleTxtR, resourceID );
    }
    public void setLeftTitleTextR( int resourceID ) {
    	changeItem( leftTitleTextR, resourceID );
    }

    public void setTextLeftR( String string ) {
        changeItem( leftTitleTxtR, string );
    }
    public void setLeftTitleTextR( String string ) {
    	changeItem( leftTitleTextR, string );
    }

    public void setTextCenter( int resourceID ) {
        changeItem( centerTitleTxt, resourceID );
    }

    public void setTextCenter( String string ) {
        changeItem( centerTitleTxt, string );
    }

    public void setTextRightL( int resourceID ) {
        changeItem( rightTitleLTxt, resourceID );
    }

    public void setTextRightR( int resourceID ) {
        changeItem( rightTitleRTxt, resourceID );
    }
    public void setRightTopText( int resourceID ) {
    	changeItem( rightTopTxt, resourceID );
    }
    public void setRightTopText( String string ) {
    	changeItem( rightTopTxt, string );
    }

    public void setRightTopTextColor( int resourceID ) {
    	rightTopTxt.setTextColor( resourceID );
    }
    public void setTextColorLeft( int resourceID ) {
        leftTitleTxt.setTextColor( resourceID );
    }

    public void setTextColorCenter( int resourceID ) {
        centerTitleTxt.setTextColor( resourceID );
    }

    public void setTextColorRightL( int resourceID ) {
        rightTitleLTxt.setTextColor( resourceID );
    }

    public void setTextColorRightR( int resourceID ) {
        rightTitleRTxt.setTextColor( resourceID );
    }
    public void setLeftTitleTextColorR( int resourceID ) {
    	leftTitleTextR.setTextColor( resourceID );
    }

    public LinearLayout getLeft() {
        return leftTitleLlayout;
    }

    public LinearLayout getRight() {
        return rightTitleLlayout;
    }
    public RelativeLayout getRightTopTxtRlayout() {
    	return txtRightTopRlayout;
    }

    public ImageView getLeftImg() {
        return leftTitleImg;
    }

    public ImageView getRightTitleImg() {
        return rightTitleImg;
    }

    public ImageView getRightTitleRImg() {
        return rightTitleRImg;
    }

    
    public CircularImage getLeftHeadImg() {
        return headerImgL;
    }
    
    public CircularImage getRightHeadImg() {
        return headerImg;
    }

    public TextView getLeftTextView() {
        return leftTitleTxt;
    }

    public TextView getLeftRTextView() {
        return leftTitleTxtR;
    }

    public TextView getRightTextView() {
        return rightTitleLTxt;
    }
    public TextView getRightTopTextView() {
    	return rightTopTxt;
    }

}
