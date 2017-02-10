package com.bing.lan.jdmall.ui.productdetail.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.jdmall.R;

/**
 * Created by lean on 16/10/28.
 */

public class ProductDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }
}
