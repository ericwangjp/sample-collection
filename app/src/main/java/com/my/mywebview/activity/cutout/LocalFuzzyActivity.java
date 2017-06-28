package com.my.mywebview.activity.cutout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.my.mywebview.R;

public class LocalFuzzyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_fuzzy);
        final View il_add = findViewById(R.id.il_add);
        il_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                il_add.setVisibility(View.GONE);
            }
        });
    }
}
