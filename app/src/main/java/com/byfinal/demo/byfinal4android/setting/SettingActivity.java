package com.byfinal.demo.byfinal4android.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.byfinal.demo.byfinal4android.R;
import com.byfinal.demo.byfinal4android.base.slide.SlidingActivity;

/**
 * @author wangqin.lwq
 * @date 2016-03-03
 */
public class SettingActivity extends SlidingActivity {
    private static final String TAG = SettingActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting, true);
        //setTitle(getResources().getString(R.string.title_setting));

        findViewById(R.id.setting_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
