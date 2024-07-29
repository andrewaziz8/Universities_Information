package com.example.universities_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;


public class CountryAdapter extends BaseAdapter {

    private Context context;
    private String[] countries;
    private int[] Images;

    public CountryAdapter(Context context, String[] countries, int[] Images) {
        this.context = context;
        this.countries = countries;
        this.Images = Images;
    }

    @Override
    public int getCount(){
        return countries.length;
    }

    @Override
    public Object getItem(int position) {
        return countries[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String cou = countries[position];
        int img = Images[position];

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        TextView name = (TextView) convertView.findViewById(R.id.textView2);

        name.setText(cou);
        image.setImageResource(img);

        return convertView;
    }
}
