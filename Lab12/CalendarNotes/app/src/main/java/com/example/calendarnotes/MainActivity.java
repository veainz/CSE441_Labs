package com.example.calendarnotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {
    //Khai báo các biến
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtm);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);

        //Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/MM/yyyy
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Hiển thị lên TextView
        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate));

        //Khởi tạo mảng ArrayList kiểu String rỗng
        arraywork = new ArrayList<String>();

        //Khai báo ArrayAdapter, đưa mảng dữ liệu vào ArrayAdapter
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraywork);

        //Đưa Adapter có dữ liệu lên ListView
        lv.setAdapter(arrAdapter);

        //Viết sự kiện khi Click vào Button btnwork
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Nếu 1 trong 3 Edit Text không có nội dung thì hiển thị thông báo bằng hộp thoại
                //Dialog
                if(edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    });
                    builder.show();
                }
                //Lấy nội dung công việc và thời gian ra từ Edit Text và đưa vào Mảng arraywork, cập
                //nhật lại Adapter
                else {
                    String str = edtwork.getText().toString() + " - " +
                            edth.getText().toString() + ":" + edtm.getText().toString();
                    //Để Thêm dữ liệu cho ListView, chúng ta cần 2 bước:
                    //Cập Thêm liệu cho mảng
                    arraywork.add(str);
                    //Xóa: arraywork.remove(i)
                    //Cập nhật lại Adapter
                    arrAdapter.notifyDataSetChanged();
                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });
    }
}