package com.my.mywebview.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.view.ObservableScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaoBaoDetailsActivity extends CommonBaseActivity {

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.lv_header)
    LinearLayout lvHeader;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spite_line)
    View spiteLine;
    @BindView(R.id.lv_bottom)
    LinearLayout lvBottom;
    @BindView(R.id.activity_tao_bao_details)
    RelativeLayout activityTaoBaoDetails;
    @BindView(R.id.tv_content)
    TextView tvContent;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_tao_bao_details);
        ButterKnife.bind(this);
    }
    String j="我是测试内容";
    @Override
    protected void initData() {
        super.initData();
        for(int i=0;i<50;i++){
            j="我是测试内容"+i+"\n"+j;
        }
        tvContent.setText(j);
        //获取dimen属性中 标题和头部图片的高度
        final float title_height = getResources().getDimension(R.dimen.title_height);
        final float head_height = getResources().getDimension(R.dimen.head_height);
        //滑动事件回调监听（一次滑动的过程一般会连续触发多次）
        scrollView.setOnScrollListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScroll(int oldy, int dy, boolean isUp) {
               /* DensityUtil Density = new DensityUtil();
                int mHeaderHeight_px = Density.dip2px(ScrollViewActivity.this, 200.0f);*/

                float move_distance = head_height - title_height;
                if (!isUp && dy <= move_distance) {//手指往上滑,距离未超过200dp
                    //标题栏逐渐从透明变成不透明
                    toolbar.setBackgroundColor(ContextCompat.getColor(TaoBaoDetailsActivity.this, R.color.color_white));
                    TitleAlphaChange(dy, move_distance);//标题栏渐变
                    HeaderTranslate(dy);//图片视差平移

                } else if (!isUp && dy > move_distance) {//手指往上滑,距离超过200dp
                    TitleAlphaChange(1, 1);//设置不透明百分比为100%，防止因滑动速度过快，导致距离超过200dp,而标题栏透明度却还没变成完全不透的情况。

                    HeaderTranslate(head_height);//这里也设置平移，是因为不设置的话，如果滑动速度过快，会导致图片没有完全隐藏。

                    ivBack.setImageResource(R.drawable.ic_back_dark);
                    ivMore.setImageResource(R.drawable.ic_more_dark);
                    ivShoppingCart.setImageResource(R.drawable.ic_shopping_dark);
                    spiteLine.setVisibility(View.VISIBLE);

                } else if (isUp && dy > move_distance) {//返回顶部，但距离头部位置大于200dp
                    //不做处理

                } else if (isUp && dy <= move_distance) {//返回顶部，但距离头部位置小于200dp
                    //标题栏逐渐从不透明变成透明
                    TitleAlphaChange(dy, move_distance);//标题栏渐变
                    HeaderTranslate(dy);//图片视差平移

                    ivBack.setImageResource(R.drawable.ic_back);
                    ivMore.setImageResource(R.drawable.ic_more);
                    ivShoppingCart.setImageResource(R.drawable.ic_shopping_cart);
                    spiteLine.setVisibility(View.GONE);
                }
            }
        });

    }

    private void HeaderTranslate(float distance) {
        lvHeader.setTranslationY(-distance);
        ivHeader.setTranslationY(distance / 2);
    }

    private void TitleAlphaChange(int dy, float mHeaderHeight_px) {//设置标题栏透明度变化
        float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
        //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
        //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
        //这里我们是设置背景透明度就好，因为设置控件透明度的话，返回ICON等也会变成透明的。
        //alpha 值越小越透明
        int alpha = (int) (percent * 255);
        toolbar.getBackground().setAlpha(alpha);//设置控件背景的透明度，传入int类型的参数（范围0~255）

        ivBack.getBackground().setAlpha(255 - alpha);
        ivMore.getBackground().setAlpha(255 - alpha);
        ivShoppingCart.getBackground().setAlpha(255 - alpha);
    }

    @OnClick({R.id.iv_back, R.id.iv_shopping_cart, R.id.iv_more})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Toast.makeText(this, "点击了返回按键", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.iv_shopping_cart:
                Toast.makeText(this, "点击了加入购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_more:
                Toast.makeText(this, "点击了更多按键", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
