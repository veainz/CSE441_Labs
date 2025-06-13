package com.example.vnexpress_net_reading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    static ListView lv1;
    ArrayList<List> mylist;
    MyArrayAdapter myadapter;
    String nodeName;
    String title = "";
    String link = "";
    String description = "";
    String des = "";
    String urlStr = "";
    Bitmap mIcon_val = null;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<List>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);

        LoadExampleTask task = new LoadExampleTask();
        task.execute();

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mylist.get(i).getLink()));
                startActivity(intent);
            }
        });
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            mylist = new ArrayList<List>();
            try {
                XMLParser parser = new XMLParser();
                String xml = parser.getXmlFromUrl(URL);

                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser myparser = fc.newPullParser();
                myparser.setInput(new StringReader(xml));

                int eventType = -1;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    eventType = myparser.next();
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = myparser.getName();
                            if (nodeName.equals("title")) {
                                title = myparser.nextText();
                            } else if (nodeName.equals("link")) {
                                link = myparser.nextText();
                            } else if (nodeName.equals("description")) {
                                description = myparser.nextText();
                                try {
                                    // Tìm URL hình ảnh trong description
                                    if (description.contains("src=")) {
                                        int startIndex = description.indexOf("src=\"") + 5;
                                        int endIndex = description.indexOf("\"", startIndex);
                                        if (startIndex > 4 && endIndex > startIndex) {
                                            urlStr = description.substring(startIndex, endIndex);
                                        }
                                    }
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                                // Lấy text mô tả, loại bỏ HTML tags
                                des = description.replaceAll("<[^>]*>", "").trim();
                                if (des.length() > 200) {
                                    des = des.substring(0, 200) + "...";
                                }

                                // Tải hình ảnh
                                try {
                                    if (urlStr != null && !urlStr.isEmpty()) {
                                        URL newurl = new URL(urlStr);
                                        mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                    }
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                    mIcon_val = null;
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = myparser.getName();
                            if (nodeName.equals("item")) {
                                // Chỉ thêm nếu có đủ thông tin
                                if (title != null && !title.isEmpty() && link != null && !link.isEmpty()) {
                                    mylist.add(new List(mIcon_val, title, des, link));
                                }
                                // Reset các biến
                                title = "";
                                link = "";
                                description = "";
                                des = "";
                                urlStr = "";
                                mIcon_val = null;
                            }
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mylist;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            myadapter.clear();
            if (result != null) {
                myadapter.addAll(result);
            }
            myadapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}