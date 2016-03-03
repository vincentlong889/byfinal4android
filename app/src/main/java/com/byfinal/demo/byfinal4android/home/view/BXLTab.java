package com.byfinal.demo.byfinal4android.home.view;

/**
 * @author wangqin.lwq
 * @date 2016-03-02
 */
public class BXLTab {

    public enum DispalyMode {

        /**
         * 标准模式，0不显示，1-99显示数字，大于99显示小红点
         */
        STANDARD(1),
        /**
         * 0不显示，大于0都只显示小红点
         */
        ALWAYS_TIP(2),
        /**
         * 永远不显示
         */
        ALWAYS_HIDE(3);

        private int code;

        private DispalyMode(int code) {
            this.code = code;
        }

    }

    private int normalIconResId;
    private int highlightIconResId;
    private String label;
    private int count = 0;
    private DispalyMode mode = DispalyMode.ALWAYS_HIDE;

    public int getNormalIconResId() {
        return normalIconResId;
    }

    public void setNormalIconResId(int normalIconResId) {
        this.normalIconResId = normalIconResId;
    }

    public int getHighlightIconResId() {
        return highlightIconResId;
    }

    public void setHighlightIconResId(int highlightIconResId) {
        this.highlightIconResId = highlightIconResId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DispalyMode getMode() {
        return mode;
    }

    public void setMode(DispalyMode mode) {
        this.mode = mode;
    }

    public static class Builder {
        private BXLTab tab = new BXLTab();

        public Builder setNormalIconResId(int normalIconResId) {
            tab.setNormalIconResId(normalIconResId);
            return this;
        }

        public Builder setHighlightIconResId(int highlightIconResId) {
            tab.setHighlightIconResId(highlightIconResId);
            return this;
        }

        public Builder setIconResIds(int[] resIds) {
            if (resIds != null && resIds.length >= 2) {
                tab.setNormalIconResId(resIds[0]);
                tab.setHighlightIconResId(resIds[1]);
            }
            return this;
        }

        public Builder setLabel(String label) {
            tab.setLabel(label);
            return this;
        }

        public Builder setCount(int count) {
            tab.setCount(count);
            return this;
        }

        public Builder setMode(DispalyMode mode) {
            tab.setMode(mode);
            return this;
        }

        public BXLTab build() {
            return tab;
        }

    }

}
