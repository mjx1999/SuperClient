package com.twisty.superclient.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Detail1Data;

public class FragmentAddGoods extends Fragment {


    private OnAddGoodsListener mListener;


    public static FragmentAddGoods newInstance() {
        FragmentAddGoods fragment = new FragmentAddGoods();
        return fragment;
    }
    public FragmentAddGoods() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAddGoodsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " 需要实现OnAddGoodsListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAddGoodsListener {
        // TODO: Update argument type and name
        public void onAdd(Detail1Data detailData);
    }

}
