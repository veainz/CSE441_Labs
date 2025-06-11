package com.example.temperatureconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    EditText edtdoC, edtdoF;
    Button btncf, btnfc, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtdoC = findViewById(R.id.edtdoC);
        edtdoF = findViewById(R.id.edtdoF);
        btncf = findViewById(R.id.btncf);
        btnfc = findViewById(R.id.btnfc);
        btnClear = findViewById(R.id.btnClear);

        // Convert To Celsius (F -> C)
        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DecimalFormat dcf = new DecimalFormat("#.00");
                    String doF = edtdoF.getText().toString();
                    if (!doF.isEmpty()) {
                        double F = Double.parseDouble(doF);
                        double C = (F - 32) * 5.0 / 9.0;
                        edtdoC.setText(dcf.format(C));
                    }
                } catch (NumberFormatException e) {
                    edtdoC.setText("Invalid input");
                }
            }
        });

        // Convert To Fahrenheit (C -> F)
        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DecimalFormat dcf = new DecimalFormat("#.00");
                    String doC = edtdoC.getText().toString();
                    if (!doC.isEmpty()) {
                        double C = Double.parseDouble(doC);
                        double F = C * 9.0 / 5.0 + 32;
                        edtdoF.setText(dcf.format(F));
                    }
                } catch (NumberFormatException e) {
                    edtdoF.setText("Invalid input");
                }
            }
        });

        // Clear both fields
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtdoC.setText("");
                edtdoF.setText("");
            }
        });
    }
}