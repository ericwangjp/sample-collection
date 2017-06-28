package com.my.mywebview.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.IndexableListView;
import com.my.mywebview.utils.StringMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 经测试该开源方法不好，不识别汉字，以及小写字母，只对大写英文以及数字特殊字符进行识别处理
 * 不建议使用
 */
public class SideLetterNavActivity extends CommonBaseActivity {
    private ArrayList<String> mItems;
    private IndexableListView mListView;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_side_letter_nav);
    }

    @Override
    protected void initData() {
        super.initData();
        mItems = new ArrayList<String>();
        mItems.add("Diary of a Wimpy Kid 6: Cabin Fever");
        mItems.add("Steve Jobs");
        mItems.add("Inheritance (The Inheritance Cycle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Explosive Eighteen: A Stephanie Plum Novel");
        mItems.add("Catching Fire (The Second Book of the Hunger Games)");
        mItems.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        mItems.add("Death Comes to Pemberley");
        mItems.add("Diary of a Wimpy Kid 6: Cabin Fever");
        mItems.add("Steve Jobs");
        mItems.add("Inheritance (The Inheritance Cycle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Explosive Eighteen: A Stephanie Plum Novel");
        mItems.add("Catching Fire (The Second Book of the Hunger Games)");
        mItems.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        mItems.add("Death Comes to Pemberley");
        mItems.add("air Comes to Pemberley");
        mItems.add("because Comes to Pemberley");
        mItems.add("come Comes to Pemberley");
        mItems.add("can kill you");
        mItems.add("from you");
        mItems.add("glide you are");
        mItems.add("must to be");
        mItems.add("not you are");
        mItems.add("★ you are");
        mItems.add("찾은 단어가 없습니다.");
        mItems.add("일본어 번역기");
        mItems.add("おじいさんのところに行きます");
        mItems.add("孫君おじいさんまた来たね");
        for(int i=0;i<5;i++){
            mItems.add("我是来测试的！");
        }
        for(int i=0;i<5;i++){
            mItems.add("WOSHIHAOREN");
        }
        for(int i=0;i<5;i++){
            mItems.add("view不识别");
        }
        Collections.sort(mItems);
        ContentAdapter adapter = new ContentAdapter(this,
                android.R.layout.simple_list_item_1, mItems);

        mListView = (IndexableListView) findViewById(R.id.listview);
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);
    }
    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

        private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ★";

        public ContentAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public int getPositionForSection(int section) {
            // If there is no item for current section, previous section will be selected
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {
                        // For numeric section
                        for (int k = 0; k <= 9; k++) {
                            if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
                                return j;
                        }
                    } else {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }

        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i = 0; i < mSections.length(); i++)
                sections[i] = String.valueOf(mSections.charAt(i));
            return sections;
        }
    }
}
