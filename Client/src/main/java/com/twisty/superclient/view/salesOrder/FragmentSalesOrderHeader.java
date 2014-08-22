package com.twisty.superclient.view.salesOrder;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twisty.superclient.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentSalesOrderHeader extends Fragment {


    public FragmentSalesOrderHeader() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales_order_header, container, false);
    }


}
