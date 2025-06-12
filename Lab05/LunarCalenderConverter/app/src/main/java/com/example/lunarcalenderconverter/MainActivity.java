package com.example.lunarcalenderconverter;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editNamDuongLich;
    private Button btnChuyenDoi;
    private TextView textViewKetQua;

    // Mảng Can và Chi theo đề bài
    private String[] can = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
    private String[] chi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        editNamDuongLich = findViewById(R.id.editnamduonglich);
        btnChuyenDoi = findViewById(R.id.button1);
        textViewKetQua = findViewById(R.id.textView5);

        // Xử lý sự kiện click button
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenDoiNamAmLich();
            }
        });
    }

    private void chuyenDoiNamAmLich() {
        String namDuongLichStr = editNamDuongLich.getText().toString().trim();

        if (namDuongLichStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập năm dương lịch!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int namDuongLich = Integer.parseInt(namDuongLichStr);

            // Tính Can và Chi theo công thức từ đề bài
            // Can = Năm dương % 10
            int canIndex = namDuongLich % 10;

            // Chi = Năm dương % 12
            int chiIndex = namDuongLich % 12;

            String canChi = can[canIndex] + " " + chi[chiIndex];

            // Hiển thị kết quả lên TextView
            textViewKetQua.setText(canChi);

            // Hiển thị Toast
            Toast.makeText(this, "Năm âm lịch: " + canChi, Toast.LENGTH_LONG).show();

            // Hiển thị AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Kết quả chuyển đổi");
            builder.setMessage("Năm dương lịch: " + namDuongLich + "\nNăm âm lịch: " + canChi);
            builder.setPositiveButton("OK", null);
            builder.show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }
}