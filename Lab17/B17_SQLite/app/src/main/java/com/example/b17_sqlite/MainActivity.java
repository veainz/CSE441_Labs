package com.example.b17_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    String DATABASE_NAME = "qlsach.db";
    SQLiteDatabase database = null;

    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processCopy();

        try {
            String dbPath = getDatabasePath(DATABASE_NAME).getPath();
            database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Toast.makeText(this, "Error opening database: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        try {
            Cursor c = database.query("tbsach", null, null, null, null, null, null);
            if (c != null && c.moveToFirst()) {
                do {
                    String data = c.getInt(0) + " - " + c.getString(1) + " - " + c.getString(2);
                    mylist.add(data);
                } while (c.moveToNext());
                c.close();
            }
            myadapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(this, "Error reading database: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAssets();
                Toast.makeText(this, "Copy database success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Copy failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void copyDatabaseFromAssets() throws IOException {
        InputStream myInput = getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();

        File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists()) f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
