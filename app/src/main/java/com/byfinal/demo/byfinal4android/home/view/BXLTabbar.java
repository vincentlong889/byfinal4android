package com.byfinal.demo.byfinal4android.home.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.byfinal.demo.byfinal4android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class BXLTabbar extends RelativeLayout {
    private static final String TAG = BXLTabbar.class.getSimpleName();

    private List<BXLTab> tabs;
    private List<BXLTabView> tabViews = new ArrayList<>();

    private int bgColor = Color.parseColor("#fcfcfc");

    private View topLine;
    private LinearLayout contentView;

    private boolean isInit = false;

    private OnClickListener onClickListener;
    public void setOnTabClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public BXLTabbar(Context context) {
        super(context);
        setBackgroundColor(bgColor);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_layout, this);
    }

    public BXLTabbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(bgColor);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_layout, this);
    }

    public BXLTabbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(bgColor);
        LayoutInflater.from(context).inflate(R.layout.bxl_tabbar_layout, this);
    }

    public void setTabs(List<BXLTab> tabs) {
        this.tabs = tabs;
        init();
    }

    private void init() {
        if (tabs != null && tabs.size() > 0) {
            tabViews.clear();
            for(int i = 0 ; i < tabs.size(); ++i) {
                BXLTab tab = tabs.get(i);
                BXLTabView tabView = new BXLTabView(getContext());
                tabView.init(tab.getLabel(), tab.getNormalIconResId(), tab.getHighlightIconResId());
                tabView.setTag(i);
                tabView.setOnClickListener(onClickListener);
                tabViews.add(tabView);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInit) {
            isInit = true;
            topLine = findViewById(R.id.bxl_tabbar_top_line);
            contentView = (LinearLayout) findViewById(R.id.bxl_tabbar_content);
            LinearLayout.LayoutParams childLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
            childLayoutParams.weight = 1;
            for (BXLTabView tabView : tabViews) {
                contentView.addView(tabView, childLayoutParams);
            }
        }
    }

    /**
     * 切换tab
     *
     * @param index
     */
    public void shouldSelectItem(int index) {
        if (index >= 0 && index < tabViews.size()) {
            for (BXLTabView tabView : tabViews) {
                tabView.setAlpha(0.0f);
            }
            tabViews.get(index).setAlpha(1.0f);
        }
    }

    /**
     * 配合ViewPager切换tab
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position >= 0 && position < tabViews.size() - 1) {
            int outIndex = position;
            int inIndex = position + 1;

            BXLTabView outTab = tabViews.get(outIndex);
            BXLTabView inTab = tabViews.get(inIndex);

            outTab.setAlpha(1.0f - positionOffset);
            inTab.setAlpha(positionOffset);
        }
    }


}
