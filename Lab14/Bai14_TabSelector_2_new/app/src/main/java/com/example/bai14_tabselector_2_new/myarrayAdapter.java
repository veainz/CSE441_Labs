package com.example.bai14_tabselector_2_new;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutId;

    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);

        Item myItem = myArray.get(position);

        ImageView btnlike = convertView.findViewById(R.id.btnlike);
        int thich = myItem.getThich();
        if (thich == 1) {
            btnlike.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btnlike.setImageResource(android.R.drawable.btn_star_big_off);
        }

        TextView tieude = convertView.findViewById(R.id.txttieude);
        tieude.setText(myItem.getTieude());

        TextView maso = convertView.findViewById(R.id.txtmaso);
        maso.setText(myItem.getMaso());

        return convertView;
    }
}