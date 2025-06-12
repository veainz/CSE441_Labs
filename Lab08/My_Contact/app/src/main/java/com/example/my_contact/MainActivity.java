package com.example.my_contact;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnCall, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btncallphone);
        btnSend = findViewById(R.id.btnsendms);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo mới một đối tượng intent
                Intent intent1 = new Intent(MainActivity.this, CallPhoneActivity.class);
                // Thực thi Intent!
                startActivity(intent1);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo mới một đối tượng intent
                Intent intent2 = new Intent(MainActivity.this, SendSMSActivity.class);
                // Thực thi Intent!
                startActivity(intent2);
            }
        });
    }
}