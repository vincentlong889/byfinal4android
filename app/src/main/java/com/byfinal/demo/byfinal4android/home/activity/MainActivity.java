package com.byfinal.demo.byfinal4android.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.byfinal.demo.byfinal4android.R;
import com.byfinal.demo.byfinal4android.base.slide.IntentUtils;
import com.byfinal.demo.byfinal4android.home.fragment.ContactsFragment;
import com.byfinal.demo.byfinal4android.home.fragment.DiscoveryFragment;
import com.byfinal.demo.byfinal4android.home.fragment.MeFragment;
import com.byfinal.demo.byfinal4android.home.fragment.MessageFragment;
import com.byfinal.demo.byfinal4android.home.view.BXLTab;
import com.byfinal.demo.byfinal4android.home.view.BXLTabbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<BXLTab> tabs = new ArrayList<>();
    private BXLTabbar tabbar;

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateFragments();
        populateTabs();

        viewPager = (ViewPager) findViewById(R.id.content_main);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.addOnPageChangeListener(onPageChangeListener);

        tabbar = (BXLTabbar) findViewById(R.id.tabbar);
        tabbar.setOnTabClickListener(onTabClickListener);
        tabbar.setTabs(tabs);
        tabbar.shouldSelectItem(0);

    }

    private void populateTabs() {
        tabs.add(new BXLTab.Builder().setNormalIconResId(R.drawable.tabbar_mainframe)
                .setHighlightIconResId(R.drawable.tabbar_mainframe_hl)
                .setLabel(getResources().getString(R.string.tabbar_msg))
                .build());
        tabs.add(new BXLTab.Builder().setNormalIconResId(R.drawable.tabbar_contacts)
                .setHighlightIconResId(R.drawable.tabbar_contacts_hl)
                .setLabel(getResources().getString(R.string.tabbar_contacts))
                .build());
        tabs.add(new BXLTab.Builder().setNormalIconResId(R.drawable.tabbar_discover)
                .setHighlightIconResId(R.drawable.tabbar_discover_hl)
                .setLabel(getResources().getString(R.string.tabbar_discovery))
                .build());
        tabs.add(new BXLTab.Builder().setNormalIconResId(R.drawable.tabbar_me)
                .setHighlightIconResId(R.drawable.tabbar_me_hl)
                .setLabel(getResources().getString(R.string.tabbar_me))
                .build());
    }

    private void populateFragments() {
        fragments.add(new MessageFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new MeFragment());

        titles.add(getResources().getString(R.string.tabbar_msg));
        titles.add(getResources().getString(R.string.tabbar_contacts));
        titles.add(getResources().getString(R.string.tabbar_discovery));
        titles.add(getResources().getString(R.string.tabbar_me));
    }

    private class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragments;
        private List<String> titles;

        public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            tabbar.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private View.OnClickListener onTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            viewPager.setCurrentItem(position, false);
            tabbar.shouldSelectItem(position);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IntentUtils.getInstance().clear();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                moveTaskToBack(false);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
