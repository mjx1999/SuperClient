package com.twisty.superclient.view.stockCheck;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twisty.superclient.R;
import com.twisty.superclient.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentStockCheckHeader extends BaseFragment {


    public FragmentStockCheckHeader() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock_check_header, container, false);
    }


}
