package com.byfinal.demo.byfinal4android.base.slide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;

import com.byfinal.demo.byfinal4android.R;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenjishi on 14-3-19.
 */
public class IntentUtils {

    private static final IntentUtils INSTANCE = new IntentUtils();

    private LinkedHashMap<String, BitmapItem> mCachedBitmaps;

    private IntentUtils() {
        mCachedBitmaps = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void clear() {
        for (Map.Entry<String, BitmapItem> entry : mCachedBitmaps.entrySet()) {
            entry.getValue().clear();
        }
        mCachedBitmaps.clear();
    }

    public void setIsDisplayed(String id, boolean isDisplayed) {
        BitmapItem item = mCachedBitmaps.get(id);
        if (null != item) {
            item.setIsDisplayed(isDisplayed);
        }
    }

    private BitmapItem getBitmapItem(int width, int height) {
        int size = mCachedBitmaps.size();

        if (size > 0) {
            BitmapItem reuseItem = null;
            for (Map.Entry<String, BitmapItem> entry : mCachedBitmaps.entrySet()) {
                BitmapItem item = entry.getValue();
                if (item.getReferenceCount() <= 0) {
                    reuseItem = item;
                }
            }

            if (null != reuseItem) {
                return reuseItem;
            } else {
                return createItem(width, height);
            }
        } else {
            return createItem(width, height);
        }
    }

    private BitmapItem createItem(int width, int height) {
        BitmapItem item = BitmapItem.create(width, height);
        String id = "id_" + System.currentTimeMillis();
        item.setId(id);
        mCachedBitmaps.put(id, item);
        return item;
    }

    public static IntentUtils getInstance() {
        return INSTANCE;
    }

    public Bitmap getBitmap(String id) {
        return mCachedBitmaps.get(id).getBitmap();
    }

    public void startActivity(final Context context, final Intent intent) {
        final View v = ((Activity) context).findViewById(android.R.id.content);

        BitmapItem item = getBitmapItem(v.getWidth(), v.getHeight());
        final Bitmap bitmap = item.getBitmap();
        intent.putExtra("bitmap_id", item.getId());

        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                Canvas canvas = new Canvas(bitmap);
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                v.draw(canvas);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
            }
        }, 100);
    }



}
