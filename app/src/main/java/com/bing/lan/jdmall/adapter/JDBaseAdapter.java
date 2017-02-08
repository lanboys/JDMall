package com.bing.lan.jdmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class JDBaseAdapter<T> extends BaseAdapter {

	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	
	public JDBaseAdapter(Context context){
		mInflater=LayoutInflater.from(context);
	}
	
	public void setDatas(List<T> datas) {
		this.mDatas = datas;
	}
	
	@Override
	public int getCount() {
		return mDatas!=null?mDatas.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}
