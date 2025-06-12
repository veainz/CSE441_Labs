package com.example.my_contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CallPhoneActivity extends AppCompatActivity {
    EditText edtcall;
    ImageButton btncallphone;
    Button btnback1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_layout);

        edtcall = (EditText) findViewById(R.id.edtcall);
        btncallphone = (ImageButton) findViewById(R.id.btncallphone);
        btnback1 = (Button) findViewById(R.id.btnback1);

        btncallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + edtcall.getText().toString()));
                // Yêu cầu người dùng đồng ý quyền truy cập vào tính năng gọi điện
                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallPhoneActivity.this, new
                            String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(callIntent);
            }
        });

        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}