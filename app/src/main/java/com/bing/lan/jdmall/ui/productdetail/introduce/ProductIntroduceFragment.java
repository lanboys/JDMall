package com.bing.lan.jdmall.ui.productdetail.introduce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.jdmall.R;

public class ProductIntroduceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_productdetails, container, false);
        // return inflater.inflate(R.layout.fragment_product_introduce, container, false);
    }
}
