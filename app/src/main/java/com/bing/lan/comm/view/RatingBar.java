package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bing.lan.jdmall.R;

public class RatingBar extends LinearLayout {
	
	public RatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	/**
	 * max为 0
	 */
	public void setRating(int count){
		for (int i = 0; i < count; i++) {
			ImageView iv=(ImageView) getChildAt(i);
			iv.setImageResource(R.drawable.start_selected);
		}
	}

}
