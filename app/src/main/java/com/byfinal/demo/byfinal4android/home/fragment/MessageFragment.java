package com.byfinal.demo.byfinal4android.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.byfinal.demo.byfinal4android.R;
import com.byfinal.demo.byfinal4android.base.slide.IntentUtils;
import com.byfinal.demo.byfinal4android.home.biz.model.MsgDTO;
import com.byfinal.demo.byfinal4android.home.view.BXLUtil;
import com.byfinal.demo.byfinal4android.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class MessageFragment extends Fragment {
    private static final String TAG = MessageFragment.class.getSimpleName();

    private PtrFrameLayout ptrFrameLayout;
    private ListView listView;

    public MessageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        ptrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.msg_ptr_frame);

        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setTextColor(Color.parseColor("#303135"));
        header.setLineWidth(BXLUtil.dp2px(1, getContext()));
        header.setPadding(0, BXLUtil.dp2px(15, getContext()), 0, 0);
        header.initWithStringArray(R.array.storehouse);

        ptrFrameLayout.setKeepHeaderWhenRefresh(false);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, listView, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.refreshComplete();
            }
        });

        listView = (ListView) view.findViewById(R.id.msg_list);
        listView.setAdapter(new MsgAdapter(getContext(), mockMsgList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoSettingPage();
            }
        });

        return view;
    }

    private class MsgAdapter extends BaseAdapter {
        private List<MsgDTO> msgs;
        private Context mContext;
        private LayoutInflater mInflater;

        public MsgAdapter(Context context, List<MsgDTO> msgs) {
            this.mContext = context;
            mInflater = LayoutInflater.from(mContext);
            this.msgs = msgs;
        }

        @Override
        public int getCount() {
            return msgs != null ? msgs.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return msgs != null ? msgs.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return -1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.msg_list_item, null);
            }
            return convertView;
        }
    }

    private List<MsgDTO> mockMsgList() {
        List<MsgDTO> msgs = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            msgs.add(new MsgDTO());
        }
        return msgs;
    }

    private void gotoSettingPage() {
        Intent intent = new Intent(getContext(), SettingActivity.class);
        IntentUtils.getInstance().startActivity(getContext(), intent);
    }

}
