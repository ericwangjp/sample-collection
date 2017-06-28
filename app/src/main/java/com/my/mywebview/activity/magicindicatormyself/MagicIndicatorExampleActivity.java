package com.my.mywebview.activity.magicindicatormyself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.activity.magicindicator.BadgeTabExampleActivity;
import com.my.mywebview.activity.magicindicator.CustomNavigatorExampleActivity;
import com.my.mywebview.activity.magicindicator.DynamicTabExampleActivity;
import com.my.mywebview.activity.magicindicator.FixedTabExampleActivity;
import com.my.mywebview.activity.magicindicator.FragmentContainerExampleActivity;
import com.my.mywebview.activity.magicindicator.LoadCustomLayoutExampleActivity;
import com.my.mywebview.activity.magicindicator.NoTabOnlyIndicatorExampleActivity;
import com.my.mywebview.activity.magicindicator.ScrollableTabExampleActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.CircularImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MagicIndicatorExampleActivity extends CommonBaseActivity {
    @BindView(R.id.llayout_magic_indicator2)
    LinearLayout llayoutMagicIndicator2;
    @BindView(R.id.llayout_magic_indicator3)
    LinearLayout llayoutMagicIndicator3;
    @BindView(R.id.scrollable_tab)
    LinearLayout scrollableTab;
    @BindView(R.id.fixed_tab)
    LinearLayout fixedTab;
    @BindView(R.id.dynamic_tab)
    LinearLayout dynamicTab;
    @BindView(R.id.no_tab_only_indicator)
    LinearLayout noTabOnlyIndicator;
    @BindView(R.id.work_with_fragment_container)
    LinearLayout workWithFragmentContainer;
    @BindView(R.id.tab_with_badge_view)
    LinearLayout tabWithBadgeView;
    @BindView(R.id.load_custom_layout)
    LinearLayout loadCustomLayout;
    @BindView(R.id.custom_navigator)
    LinearLayout customNavigator;
    private TitleHelper title;

    @BindView(R.id.rlayout_title_root)
    RelativeLayout rlayoutTitleRoot;
    @BindView(R.id.img_header_L)
    CircularImage imgHeaderL;
    @BindView(R.id.img_left_title)
    ImageView imgLeftTitle;
    @BindView(R.id.txt_left_titleL)
    TextView txtLeftTitleL;
    @BindView(R.id.txt_left_titleR)
    TextView txtLeftTitleR;
    @BindView(R.id.ll_txt_left_titleR)
    TextView llTxtLeftTitleR;
    @BindView(R.id.ll_left_title_layoutR)
    RelativeLayout llLeftTitleLayoutR;
    @BindView(R.id.llayout_left_title)
    LinearLayout llayoutLeftTitle;
    @BindView(R.id.img_center_title)
    ImageView imgCenterTitle;
    @BindView(R.id.txt_center_title)
    TextView txtCenterTitle;
    @BindView(R.id.llayout_center_title)
    LinearLayout llayoutCenterTitle;
    @BindView(R.id.txt_right_titleL)
    TextView txtRightTitleL;
    @BindView(R.id.img_right_title)
    ImageView imgRightTitle;
    @BindView(R.id.txt_right_num)
    TextView txtRightNum;
    @BindView(R.id.rlayout_root_text_right)
    RelativeLayout rlayoutRootTextRight;
    @BindView(R.id.img_right_titleR)
    ImageView imgRightTitleR;
    @BindView(R.id.img_header)
    CircularImage imgHeader;
    @BindView(R.id.txt_right_titleR)
    TextView txtRightTitleR;
    @BindView(R.id.llayout_right_title)
    LinearLayout llayoutRightTitle;
    @BindView(R.id.title_view_line)
    View titleViewLine;
    @BindView(R.id.llayout_magic_indicator1)
    LinearLayout llayoutMagicIndicator1;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_magic_indicator_example);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    protected void initTitleBar() {
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_magic_indicator);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onListener() {
        super.onListener();
        llayoutMagicIndicator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MagicIndicatorActivity.class, "", paras);
            }
        });
        llayoutMagicIndicator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MagicIndicatorScaleActivity.class, "", paras);
            }
        });
        llayoutMagicIndicator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MagicClipIndicatorActivity.class, "", paras);
            }
        });
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scrollable_tab:
                startActivity(new Intent(this, ScrollableTabExampleActivity.class));
                break;
            case R.id.fixed_tab:
                startActivity(new Intent(this, FixedTabExampleActivity.class));
                break;
            case R.id.dynamic_tab:
                startActivity(new Intent(this, DynamicTabExampleActivity.class));
                break;
            case R.id.no_tab_only_indicator:
                startActivity(new Intent(this, NoTabOnlyIndicatorExampleActivity.class));
                break;
            case R.id.tab_with_badge_view:
                startActivity(new Intent(this, BadgeTabExampleActivity.class));
                break;
            case R.id.work_with_fragment_container:
                startActivity(new Intent(this, FragmentContainerExampleActivity.class));
                break;
            case R.id.load_custom_layout:
                startActivity(new Intent(this, LoadCustomLayoutExampleActivity.class));
                break;
            case R.id.custom_navigator:
                startActivity(new Intent(this, CustomNavigatorExampleActivity.class));
                break;
            default:
                break;
        }
    }
}
