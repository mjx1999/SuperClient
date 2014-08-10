package com.twisty.superclient.view;

import android.os.Bundle;
import android.widget.GridView;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.ModuleAdapter;
import com.twisty.superclient.bean.Module;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private GridView moduleView;
    private ModuleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moduleView = (GridView) findViewById(R.id.module);
        List<Module> modules = new ArrayList<Module>();
        Module module1 = new Module();
        module1.setModuleIcon(android.R.drawable.ic_dialog_alert);
        module1.setModuleName("单据管理");
        module1.setModuleID(1);
        Module module2 = new Module();
        module2.setModuleIcon(android.R.drawable.stat_sys_download);
        module2.setModuleName("下载数据");
        module2.setModuleID(2);

        modules.add(module1);
        modules.add(module2);

        adapter = new ModuleAdapter(modules,this);
        moduleView.setAdapter(adapter);
    }
}
