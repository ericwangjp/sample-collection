package com.my.mywebview.activity.dropdownbox;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.my.mywebview.R;
import com.my.mywebview.adapter.CustomSpinnerAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.DrowSpinnerBean;
import com.my.mywebview.utils.TitleHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitySelectDropDownActivity extends CommonBaseActivity {

    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    private List<DrowSpinnerBean> list1;
    private List<DrowSpinnerBean> list2;
    private List<DrowSpinnerBean> list3;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_city_select_drop_down);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_city_search);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        //加载省份列表
        loadProvince();
        //设置点击事件
        spinner1.setOnItemSelectedListener(new Spinner1ClickListener());
        //加载城市列表
        loadCity();
        //设置点击事件
        spinner2.setOnItemSelectedListener(new Spinner2ClickListener());
        //加载区域列表
        loadGZArea();
        //设置点击事件
        spinner3.setOnItemSelectedListener(new Spinner3ClickListener());
    }
    /**
     * 加载省份
     */
    public void loadProvince() {
        list1 = new ArrayList<>();
        DrowSpinnerBean modelOne;
        modelOne = new DrowSpinnerBean();
        modelOne.setImgId(R.drawable.image_watch_4);
        modelOne.setTitle("请选择");
        list1.add(modelOne);

        modelOne = new DrowSpinnerBean();
        modelOne.setImgId(R.drawable.image_watch_5);
        modelOne.setTitle("广东省");
        list1.add(modelOne);

        CustomSpinnerAdapter adapterOne = new CustomSpinnerAdapter(list1, R.layout.item_drop_down,this);
        spinner1.setAdapter(adapterOne);
    }
    /**
     * 加载城市列表
     */
    public void loadCity() {
        list2 = new ArrayList<>();
        DrowSpinnerBean modelTwo;
        modelTwo = new DrowSpinnerBean();
        modelTwo.setImgId(R.drawable.image_watch_4);
        modelTwo.setTitle("请选择");
        list2.add(modelTwo);

        modelTwo = new DrowSpinnerBean();
        modelTwo.setImgId(R.drawable.image_watch_5);
        modelTwo.setTitle("广州市");
        list2.add(modelTwo);

        modelTwo = new DrowSpinnerBean();
        modelTwo.setImgId(R.drawable.image_watch_5);
        modelTwo.setTitle("深圳市");
        list2.add(modelTwo);

        CustomSpinnerAdapter adapterTwo = new CustomSpinnerAdapter(list2, R.layout.item_drop_down,this);
        spinner2.setAdapter(adapterTwo);
    }
    /**
     * 加载广州区域列表
     */
    public void loadGZArea() {
        list3 = new ArrayList<>();
        DrowSpinnerBean modelThree;
        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_4);
        modelThree.setTitle("请选择");
        list3.add(modelThree);

        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_5);
        modelThree.setTitle("天河区");
        list3.add(modelThree);

        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_5);
        modelThree.setTitle("越秀区");
        list3.add(modelThree);

        CustomSpinnerAdapter adapterThree = new CustomSpinnerAdapter(list3, R.layout.item_drop_down,this);
        spinner3.setAdapter(adapterThree);
    }
    /**
     * 加载深圳区域列表
     */
    public void loadSZArea() {
        list3 = new ArrayList<>();

        DrowSpinnerBean modelThree = null;
        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_5);
        modelThree.setTitle("请选择");
        list3.add(modelThree);

        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_5);
        modelThree.setTitle("龙岗区");
        list3.add(modelThree);

        modelThree = new DrowSpinnerBean();
        modelThree.setImgId(R.drawable.image_watch_5);
        modelThree.setTitle("南山区");
        list3.add(modelThree);

        CustomSpinnerAdapter adapterThree = new CustomSpinnerAdapter( list3, R.layout.item_drop_down,this);
        spinner3.setAdapter(adapterThree);
    }
    /**
     * Spinner1点击事件
     */
    public class Spinner1ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            DrowSpinnerBean model = (DrowSpinnerBean) adapterView.getItemAtPosition(i);
            //判断是否选择城市，如果没有选择那么就隐藏Spinner2,Spinner3两个下拉框，否则显示Spinner2下拉框，继续隐藏Spinner3
            if (model.getTitle().equals("请选择")) {
                spinner2.setVisibility(View.INVISIBLE);
                spinner3.setVisibility(View.INVISIBLE);
            } else {
                spinner2.setVisibility(View.VISIBLE);

                //将第二个下拉框的选项重新设置为选中“请选择”这个选项。
                spinner2.setSelection(0);
            }

            Toast.makeText(getApplicationContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    /**
     * Spinner2点击事件
     */
    public class Spinner2ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            DrowSpinnerBean model = (DrowSpinnerBean) adapterView.getItemAtPosition(i);
            if (model.getTitle().equals("请选择")) {
                spinner3.setVisibility(View.INVISIBLE);
            } else {
                //显示第三个Spinner3
                spinner3.setVisibility(View.VISIBLE);

                if (model.getTitle().equals("深圳市")) {
                    //重新加载深圳区域列表
                    loadSZArea();
                } else if (model.getTitle().equals("广州市")) {
                    //重新加载广州区域列表
                    loadGZArea();
                }
            }
            Toast.makeText(getApplicationContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    /**
     * Spinner3点击事件
     */
    public class Spinner3ClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            DrowSpinnerBean model = (DrowSpinnerBean) adapterView.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
