package com.example.bai14_tabselector_2_new;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText edttim;
    ListView lv1, lv2, lv3;
    TabHost tab;
    ArrayList<Item> list1, list2, list3;
    myarrayAdapter myarray1, myarray2, myarray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        tab.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    list1.clear();
                    list1.add(new Item("52300", "Em là ai Tôi là ai", 0));
                    list1.add(new Item("52600", "Chén Đắng", 1));
                    list1.add(new Item("52567", "Buồn của Anh", 1));
                    myarray1.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t2")) {
                    list2.clear();
                    list2.add(new Item("57236", "Gởi em ở cuối sông hồng", 1));
                    list2.add(new Item("51548", "Quê hương tuổi thơi tôi", 0));
                    list2.add(new Item("51748", "Em gì ới", 0));
                    myarray2.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t3")) {
                    list3.clear();
                    list3.add(new Item("57689", "Hát với dòng sông", 0));
                    list3.add(new Item("58716", "Say tình _ Remix", 0));
                    list3.add(new Item("58916", "Người hãy quên em đi", 1));
                    myarray3.notifyDataSetChanged();
                }
            }
        });
    }

    private void addControl() {
        tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();

        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(android.R.drawable.ic_menu_search));
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(android.R.drawable.btn_star_big_on));
        tab.addTab(tab3);

        edttim = (EditText) findViewById(R.id.edttim);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);

        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();

        myarray1 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list1);
        myarray2 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list2);
        myarray3 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list3);

        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);

        // Load initial data for tab1
        list1.add(new Item("52300", "Em là ai Tôi là ai", 0));
        list1.add(new Item("52600", "Chén Đắng", 1));
        list1.add(new Item("52567", "Buồn của Anh", 1));
        myarray1.notifyDataSetChanged();
    }
}