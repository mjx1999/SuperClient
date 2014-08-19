package com.twisty.superclient.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twisty.superclient.R;

public class FragmentOrderDetail extends BaseFragment {

    private ListView listView;
    private static final int ADDGOODS = 1;


    private OnSaveListener mListener;


    public static FragmentOrderDetail newInstance() {
        FragmentOrderDetail fragment = new FragmentOrderDetail();
        return fragment;
    }
    public FragmentOrderDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.getItem(0).setVisible(false);
        menu.getItem(1).setVisible(false);
        inflater.inflate(R.menu.order_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID  = item.getItemId();
        switch (itemID){
            case R.id.add:
                Intent intent = new Intent(getActivity(), AddGoodsActivity.class);
                startActivityForResult(intent, ADDGOODS);
                return  true;
        }
        return super.onOptionsItemSelected(item);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==ADDGOODS){
                log.i("~~~");
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
