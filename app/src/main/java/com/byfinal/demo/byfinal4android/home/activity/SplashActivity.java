package com.byfinal.demo.byfinal4android.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.byfinal.demo.byfinal4android.BXLApplication;
import com.byfinal.demo.byfinal4android.Globals;
import com.byfinal.demo.byfinal4android.R;

/**
 * @author wangqin.lwq
 * @date 2016-03-01
 */
public class SplashActivity extends AppCompatActivity implements Handler.Callback {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private static final long SPLASH_DURATION = 1500L;

    private static final int MSG_CONSUME_FINISH = 11;
    private static final int MSG_CONSUME_TIMEOUT = 13;

    private Handler mHandler;

    private boolean hasFocusedBefore = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler(this);

        BXLApplication application = (BXLApplication) Globals.getApplication();
        if (application.isLoaded) {
            mHandler.removeMessages(MSG_CONSUME_TIMEOUT);
            mHandler.sendEmptyMessage(MSG_CONSUME_FINISH);
        }
        setContentView(R.layout.activity_splash);
        application.isLoaded = true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !hasFocusedBefore) {
            hasFocusedBefore = true;
            mHandler.sendEmptyMessageDelayed(MSG_CONSUME_TIMEOUT, SPLASH_DURATION);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    private void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_CONSUME_FINISH:
                Log.v(TAG, "MSG_CONSUME_FINISH");
                gotoMainActivity();
                break;
            case MSG_CONSUME_TIMEOUT:
                Log.v(TAG, "MSG_CONSUME_TIMEOUT");
                mHandler.removeMessages(MSG_CONSUME_TIMEOUT);
                gotoMainActivity();
                break;
        }
        return true;
    }
}
