package com.byfinal.demo.byfinal4android.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byfinal.demo.byfinal4android.R;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class BXLTabView extends LinearLayout {
    private static final String TAG = BXLTabView.class.getSimpleName();

    private ImageView icon;
    private ImageView iconHL;
    private TextView tvLabel;
    private TextView tvLabelHL;

    public BXLTabView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_tab_view, this);
    }

    public BXLTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_tab_view, this);
    }

    public BXLTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_tab_view, this);
    }

    public void init(final String label, final int normalIconResId, final int highlightIconResId) {
        icon = (ImageView) findViewById(R.id.bxl_tabbar_tab_icon);
        iconHL = (ImageView) findViewById(R.id.bxl_tabbar_tab_icon_hl);
        tvLabel = (TextView) findViewById(R.id.bxl_tabbar_tab_label);
        tvLabelHL = (TextView) findViewById(R.id.bxl_tabbar_tab_label_hl);

        tvLabel.setText(label);
        tvLabelHL.setText(label);
        icon.setImageResource(normalIconResId);
        iconHL.setImageResource(highlightIconResId);

        iconHL.setAlpha(0.0f);
        tvLabelHL.setAlpha(0.0f);
    }

    public void setAlpha(float alpha) {
        tvLabel.setAlpha(1.0f - alpha);
        tvLabelHL.setAlpha(alpha);
        icon.setAlpha(1.0f - alpha);
        iconHL.setAlpha(alpha);
    }

}
