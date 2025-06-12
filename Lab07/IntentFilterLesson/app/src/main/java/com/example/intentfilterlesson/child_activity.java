package com.example.intentfilterlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class child_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity);

        Button btn = (Button) findViewById(R.id.button1);
        TextView txt1 = (TextView) findViewById(R.id.textView1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(child_activity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}