package com.twisty.superclient.view.filter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.twisty.superclient.R;
import com.twisty.superclient.adapter.AMKindAdapter;
import com.twisty.superclient.adapter.AccountAdapter;
import com.twisty.superclient.bean.AMKind;
import com.twisty.superclient.bean.Account;

import java.util.List;

/**
 * Created by twisty on 2014-8-18.
 */
public class AccountPop extends PopupWindow {

    AccountPop.onItemClickListener itemClickListener;
    public AccountPop(Context context,List<Account> data,AccountPop.onItemClickListener itemClickListener) {
        this.itemClickListener =itemClickListener;
        View view = LayoutInflater.from(context).inflate(R.layout.amkind_list,null);
        setContentView(view);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int h = point.x;
        int w = point.y;
        ListView listView = (ListView) view.findViewById(R.id.listView);
//        this.setWidth(100);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable();
        dw.setAlpha(255);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        final AccountAdapter adapter = new AccountAdapter(context,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AccountPop.this.itemClickListener.onItemClick((Account) adapterView.getItemAtPosition(i));
                AccountPop.this.dismiss();
            }
        });
    }
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.setWidth(parent.getWidth());
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }
    public  interface onItemClickListener{
        public void onItemClick(Account account);
    }
}
