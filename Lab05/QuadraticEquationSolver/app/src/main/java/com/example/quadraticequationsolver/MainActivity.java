package com.example.quadraticequationsolver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edita, editb, editc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTieptuc = findViewById(R.id.btntieptuc);
        btnGiai = findViewById(R.id.btngiaipt);
        btnThoat = findViewById(R.id.btnthoat);
        edita = findViewById(R.id.edta);
        editb = findViewById(R.id.edtb);
        editc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);

        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sa = edita.getText().toString();
                    String sb = editb.getText().toString();
                    String sc = editc.getText().toString();

                    if (sa.isEmpty() || sb.isEmpty() || sc.isEmpty()) {
                        txtkq.setText("Vui lòng nhập đầy đủ a, b, c");
                        return;
                    }

                    double a = Double.parseDouble(sa);
                    double b = Double.parseDouble(sb);
                    double c = Double.parseDouble(sc);
                    String kq = "";
                    DecimalFormat dcf = new DecimalFormat("0.00");

                    if (a == 0) {
                        if (b == 0) {
                            if (c == 0)
                                kq = "PT vô số nghiệm";
                            else
                                kq = "PT vô nghiệm";
                        } else {
                            kq = "Pt có 1 No, x=" + dcf.format(-c/b);
                        }
                    } else {
                        double delta = b*b - 4*a*c;
                        if (delta < 0) {
                            kq = "PT vô nghiệm";
                        } else if (delta == 0) {
                            kq = "Pt có No kép x1=x2=" + dcf.format(-b/(2*a));
                        } else {
                            kq = "Pt có 2 No: x1=" + dcf.format((-b + Math.sqrt(delta))/(2*a)) +
                                    "; x2=" + dcf.format((-b - Math.sqrt(delta))/(2*a));
                        }
                    }
                    txtkq.setText(kq);
                } catch (NumberFormatException e) {
                    txtkq.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });

        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edita.setText("");
                editb.setText("");
                editc.setText("");
                txtkq.setText("Pt có 2 No: x1=-1.50; x2=-1.50");
                edita.requestFocus();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}