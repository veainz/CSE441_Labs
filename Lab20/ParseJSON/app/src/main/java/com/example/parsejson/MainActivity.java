package com.example.parsejson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsejson();
            }
        });
    }

    private void parsejson() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("computer.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            JSONObject reader = new JSONObject(json);
            mylist.clear();
            mylist.add(reader.getString("MaDM"));
            mylist.add(reader.getString("TenDM"));

            JSONArray myarray = reader.getJSONArray("Sanphams");
            for (int i = 0; i < myarray.length(); i++) {
                JSONObject object = myarray.getJSONObject(i);
                mylist.add(object.getString("MaSP") + " - " + object.getString("TenSP"));
                mylist.add(object.getInt("SoLuong") + " * " + object.getInt("DonGia") + " = " + object.getInt("ThanhTien"));
                mylist.add(object.getString("Hinh"));
            }

            myadapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}