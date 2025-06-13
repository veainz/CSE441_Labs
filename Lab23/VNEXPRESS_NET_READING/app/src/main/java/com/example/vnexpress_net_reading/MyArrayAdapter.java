package com.example.vnexpress_net_reading;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<List> {
    private Activity context;
    private ArrayList<List> arr;
    private int layoutID;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<List> arr) {
        super(context, layoutID, arr);
        this.context = context;
        this.layoutID = layoutID;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        final List lst = arr.get(position);
        ImageView imgItem = convertView.findViewById(R.id.imgView);
        imgItem.setImageBitmap(lst.getImg());

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(lst.getTitle());

        TextView txtInfo = convertView.findViewById(R.id.txtInfo);
        txtInfo.setText(lst.getInfo());

        return convertView;
    }
}