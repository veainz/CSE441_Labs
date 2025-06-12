package com.example.my_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SendSMSActivity extends AppCompatActivity {
    EditText edtsms;
    ImageButton btnsendms;
    Button btnback2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        edtsms = (EditText) findViewById(R.id.edtsms);
        btnsendms = (ImageButton) findViewById(R.id.btnsendms);
        btnback2 = (Button) findViewById(R.id.btnback2);

        btnsendms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("smsto:" + edtsms.getText().toString()));
                startActivity(callIntent);
            }
        });

        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}