package com.twisty.superclient.view;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.twisty.superclient.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentOrderDetail extends Fragment {

    private ListView listView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnSaveListener mListener;


    public static FragmentOrderDetail newInstance(String param1, String param2) {
        FragmentOrderDetail fragment = new FragmentOrderDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public FragmentOrderDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_order_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        return  view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSaveListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " 需要实现OnSaveListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Map<String,String>> maps = new ArrayList<Map<String, String>>();
        Map<String,String> map ;
        for(int i=0;i<100;i++){
            map = new HashMap<String, String>();
            map.put("text1","jsljdflsajdflsjf");
            map.put("text2","jsljdflsajdfgfgefwflsjf");
            map.put("text3","jsljdflsaj34r34dflsjf");
            map.put("text4","jsljdflsa3f434f34f34fjdflsjf");
            map.put("text5","jsljdf`````lsajdflsjf");
            map.put("text6","jsljdfl121212sajdflsjf");
            map.put("text7","jsljdfl456565sajdflsjf");
            map.put("text8","jsljdfl76hghgsajdflsjf");
            map.put("text9","jsljdflghngbgbgbgsajdflsjf");
            map.put("text10","jsljdfgggggggggggggggggggggggglsajdflsjf");
            maps.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),maps,R.layout.order_detail_item,new String[]{
                "text1","text2","text3","text4","text5","text6","text7","text8","text9","text10"
        },new int[]{
                R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7,R.id.text8,R.id.text9,R.id.text10
        });
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView)parent).setItemChecked(position,true);
            }
        });
    }

    public interface OnSaveListener {
        // TODO: Update argument type and name
        public void onSaveDetail(Uri uri);
    }

}
