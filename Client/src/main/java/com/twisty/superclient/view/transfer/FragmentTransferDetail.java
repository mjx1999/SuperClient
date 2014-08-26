package com.twisty.superclient.view.transfer;



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
public class FragmentTransferDetail extends Fragment {


    public FragmentTransferDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer_detail, container, false);
    }


}
