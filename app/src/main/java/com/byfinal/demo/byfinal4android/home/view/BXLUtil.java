package com.byfinal.demo.byfinal4android.home.view;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class BXLUtil {

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

}
