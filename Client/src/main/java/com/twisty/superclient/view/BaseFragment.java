package com.twisty.superclient.view;


import android.app.Fragment;
import android.content.Intent;

import com.twisty.superclient.R;
import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class BaseFragment extends Fragment {
    protected CommonLog log = LogFactory.createLog();

    public BaseFragment() {
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.push_left_in_sba, R.anim.fade_out_sba);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Crouton.cancelAllCroutons();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) Crouton.cancelAllCroutons();

    }
}
