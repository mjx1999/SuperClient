package com.twisty.superclient.view;



import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.twisty.superclient.R;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

public class BaseFragment extends Fragment {
    protected CommonLog log = LogFactory.createLog();

    public BaseFragment() {
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in_sba,R.anim.fade_out_sba);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
    }


}
