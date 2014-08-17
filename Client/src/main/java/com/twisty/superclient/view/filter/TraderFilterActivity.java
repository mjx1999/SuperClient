package com.twisty.superclient.view.filter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.twisty.superclient.R;
import com.twisty.superclient.bean.Trader;

import java.util.List;

public class TraderFilterActivity extends Activity {
    private ListView listView;
    private List<Trader> adapterData;
    private EditText filterStr;
    private Button filterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_filter);
        listView = (ListView) findViewById(R.id.listView);
        filterStr = (EditText) findViewById(R.id.filterStr);
        filterBtn = (Button) findViewById(R.id.filterBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trader_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
