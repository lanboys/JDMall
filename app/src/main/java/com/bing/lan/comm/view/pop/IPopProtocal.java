package com.bing.lan.comm.view.pop;

import android.view.View;

public interface IPopProtocal {

    // 初始化弹出框
    void initUI();

    // 显示		锚点
    void onShow(View anchorView);

    // 隐藏
    void onDismiss();

    interface IProductSortChangeListener {

        void onSortChanged(String text);
    }
}

