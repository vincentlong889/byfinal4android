package com.byfinal.demo.byfinal4android.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.byfinal.demo.byfinal4android.R;
import com.byfinal.demo.byfinal4android.base.slide.SlidingActivity;

/**
 * @author wangqin.lwq
 * @date 2016-03-07
 */
public class TestActivity extends SlidingActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test, true);



    }
}
