package com.byfinal.demo.byfinal4android.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byfinal.demo.byfinal4android.R;
import com.byfinal.demo.byfinal4android.base.slide.IntentUtils;
import com.byfinal.demo.byfinal4android.home.activity.TestActivity;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class ContactsFragment extends Fragment {
    private static final String TAG = ContactsFragment.class.getSimpleName();

    public ContactsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_contacts, container, false);

        contentView.findViewById(R.id.tv_contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TestActivity.class);
                IntentUtils.getInstance().startActivity(getContext(), intent);
            }
        });

        return contentView;
    }

}
