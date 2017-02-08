package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.listener.IBottomBarClickListener;

public class BottomBar extends LinearLayout implements OnClickListener{

	private TextView mMainTv;
	private TextView mCategoryTv;
	private TextView mShopCarTv;
	private TextView mMineTv;
	private ImageView mMainIv;
	private ImageView mCategoryIv;
	private ImageView mShopCarIv;
	private ImageView mMineIv;
	private IBottomBarClickListener mListener;
	
	public BottomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setIBottomBarClickListener(IBottomBarClickListener listener) {
		mListener=listener;
	}
	
	
	@Override
	protected void onFinishInflate() {
		findViewById(R.id.frag_main_ll).setOnClickListener(this);
		findViewById(R.id.frag_category_ll).setOnClickListener(this);
		findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
		findViewById(R.id.frag_mine_ll).setOnClickListener(this);
		mMainIv=(ImageView) findViewById(R.id.frag_main_iv);
		mCategoryIv=(ImageView) findViewById(R.id.frag_category_iv);
		mShopCarIv=(ImageView) findViewById(R.id.frag_shopcar_iv);
		mMineIv=(ImageView) findViewById(R.id.frag_mine_iv);
		mMainTv=(TextView) findViewById(R.id.frag_main);
		mCategoryTv=(TextView) findViewById(R.id.frag_category);
		mShopCarTv=(TextView) findViewById(R.id.frag_shopcar);
		mMineTv=(TextView) findViewById(R.id.frag_mine);
		
		defaultIndicator(mMainTv,mMainIv);
	}
	
	private void defaultIndicator(TextView tv,ImageView iv){
		mMainIv.setSelected(false);
		mCategoryIv.setSelected(false);
		mShopCarIv.setSelected(false);
		mMineIv.setSelected(false);
		mMainTv.setSelected(false);
		mCategoryTv.setSelected(false);
		mShopCarTv.setSelected(false);
		mMineTv.setSelected(false);
		tv.setSelected(true);
		iv.setSelected(true);
	}

	@Override
	public void onClick(View v) {
		mListener.onItemClick(v.getId());
		switch (v.getId()) {
			case R.id.frag_main_ll:
				defaultIndicator(mMainTv,mMainIv);
				break;
			case R.id.frag_category_ll:
				defaultIndicator(mCategoryTv,mCategoryIv);
				break;
			case R.id.frag_shopcar_ll:
				defaultIndicator(mShopCarTv,mShopCarIv);
				break;
			case R.id.frag_mine_ll:
				defaultIndicator(mMineTv,mMineIv);
				break;
		}
	}


}
