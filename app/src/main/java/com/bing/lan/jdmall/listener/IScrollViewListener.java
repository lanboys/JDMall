package com.bing.lan.jdmall.listener;

import com.bing.lan.comm.view.ObservableScrollView;

public interface IScrollViewListener {
	public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
			int oldx, int oldy);
}
