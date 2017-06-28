package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.activity.anims.AllAnimShowActivity;
import com.my.mywebview.activity.cutout.CutoutViewsActivity;
import com.my.mywebview.activity.dropdownbox.DropDownActivity;
import com.my.mywebview.activity.electronicinvoice.MyInvoiceListActivity;
import com.my.mywebview.activity.electronicinvoice.MyListView;
import com.my.mywebview.activity.eventbus.EventBusMainActivity;
import com.my.mywebview.activity.fuzzysearch.FuzzySearchSelectActivity;
import com.my.mywebview.activity.magicindicatormyself.MagicIndicatorExampleActivity;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.CircularImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionSelectActivity extends CommonBaseActivity {
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
    @BindView(R.id.llayout_picture)
    LinearLayout llayoutPicture;
    @BindView(R.id.tv_init_push)
    TextView tvInitPush;
    @BindView(R.id.llayout_srcoll_view)
    LinearLayout llayoutSrcollView;
    @BindView(R.id.llayout_shape_view)
    LinearLayout llayoutShapeView;
    @BindView(R.id.llayout_txt_nav)
    LinearLayout llayoutTxtNav;
    @BindView(R.id.activity_function_select)
    LinearLayout activityFunctionSelect;
    @BindView(R.id.llayout_magic_indicator)
    LinearLayout llayoutMagicIndicator;
    @BindView(R.id.llayout_anim_guide)
    LinearLayout llayoutAnimGuide;
    @BindView(R.id.llayout_circle_count)
    LinearLayout llayoutCircleCount;
    @BindView(R.id.llayout_recycle_view)
    LinearLayout llayoutRecycleView;
    @BindView(R.id.llayout_photo_cutout)
    LinearLayout llayoutPhotoCutout;
    @BindView(R.id.llayout_fuzzy_search)
    LinearLayout llayoutFuzzySearch;
    @BindView(R.id.llayout_city_select)
    LinearLayout llayoutCitySelect;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.llayout_primissiom_dispatcher)
    LinearLayout llayoutPrimissiomDispatcher;
    @BindView(R.id.llayout_easy_primissiom)
    LinearLayout llayoutEasyPrimissiom;
    @BindView(R.id.llayout_event_bus)
    LinearLayout llayoutEventBus;
    @BindView(R.id.llayout_changeskin)
    LinearLayout llayoutChangeskin;
    @BindView(R.id.llayout_singleselect)
    LinearLayout llayoutSingleselect;
    @BindView(R.id.llayout_singlechoice)
    LinearLayout llayoutSinglechoice;
    @BindView(R.id.llayout_all_anims)
    LinearLayout llayoutAllAnims;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_function_select);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_function_select);
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
        llayoutPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, FuzzyPictureActivity.class);
            }
        });
        llayoutSrcollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, GradientScrollViewActivity.class);
            }
        });
        llayoutShapeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, ShapeActivity.class);
            }
        });
        llayoutTxtNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, SideLetterNavActivity.class);
            }
        });
        llayoutMagicIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MagicIndicatorExampleActivity.class);
            }
        });
        llayoutAnimGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, AnimGuideActivity.class);
            }
        });
        llayoutCircleCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, CircleCountStepActivity.class);
            }
        });
        llayoutRecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, RecyclerViewActivity.class);
            }
        });
        llayoutPhotoCutout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, CutoutViewsActivity.class);
            }
        });
        llayoutFuzzySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, FuzzySearchSelectActivity.class);
            }
        });
        llayoutCitySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, DropDownActivity.class);
            }
        });
        llayoutPrimissiomDispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, PermissionsDispatcherActivity.class);
            }
        });
        llayoutEasyPrimissiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, EasyPermissionActivity.class);
            }
        });
        llayoutEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, EventBusMainActivity.class);
            }
        });
        llayoutChangeskin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, ChangeSkinActivity.class);
            }
        });
        llayoutSingleselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MyInvoiceListActivity.class);
            }
        });
        llayoutSinglechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, MyListView.class);
            }
        });
        llayoutAllAnims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, AllAnimShowActivity.class);
            }
        });
    }

}
