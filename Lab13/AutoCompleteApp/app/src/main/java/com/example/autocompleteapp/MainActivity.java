package com.example.autocompleteapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.selection);

        // Khởi tạo mảng dữ liệu tỉnh thành
        String arr[] = {"hà nội", "huế", "sài gòn",
                "hà giang", "hội an", "cần thơ",
                "lâm đồng", "long khánh"};

        // Thiết lập ArrayAdapter
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );

        // Thiết lập AutoCompleteTextView
        singleComplete = (AutoCompleteTextView) findViewById(R.id.singleComplete);
        singleComplete.setAdapter(myadapter);

        // Thiết lập MultiAutoCompleteTextView
        multiComplete = (MultiAutoCompleteTextView) findViewById(R.id.multiComplete);
        multiComplete.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        ));

        // Đối với MultiAutoCompleteTextView bắt buộc phải gọi dòng lệnh này
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                selection.setText(singleComplete.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}