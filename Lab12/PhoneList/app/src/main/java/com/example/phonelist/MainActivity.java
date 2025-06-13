package com.example.phonelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.selection);

        final String arr1[] = {"Iphone 7", "Samsung Galaxy S7",
                "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"};

        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1);

        ListView lv1 = findViewById(R.id.lv1);
        lv1.setAdapter(adapter1);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
                txt1.setText("Vị trí " + i + " : " + arr1[i]);
            }
        });
    }
}