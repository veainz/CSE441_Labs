package com.example.sharedpreferencesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb, edtkq;
    Button btntong, btnclear;
    TextView txtlichsu;
    String lichsu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtkq = findViewById(R.id.edtkq);
        btntong = findViewById(R.id.btntong);
        btnclear = findViewById(R.id.btnclear);
        txtlichsu = findViewById(R.id.txtlichsu);

        //------Lấy lại dữ liệu trong SharedPreferences--------
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        lichsu = myprefs.getString("ls", "");
        txtlichsu.setText(lichsu);
        //------------------------------------------

        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edta.getText().toString());
                    int b = Integer.parseInt(edtb.getText().toString());
                    int kq = a + b;
                    edtkq.setText(kq + "");
                    lichsu += a + " + " + b + " = " + kq;
                    txtlichsu.setText(lichsu);
                    lichsu += "\n"; // Xuống dòng
                } catch (NumberFormatException e) {
                    // Xử lý trường hợp nhập không phải số
                }
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu = "";
                txtlichsu.setText(lichsu);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls", lichsu);
        myedit.commit();
    }
}