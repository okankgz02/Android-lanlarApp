package com.example.mk.otogalerim.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mk.otogalerim.AlertDialogClass;
import com.example.mk.otogalerim.Models.IlanlarimPojo;
import com.example.mk.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mk on 16.02.2018.
 */

public class IlanlarimAdapter extends BaseAdapter {
    List<IlanlarimPojo> list;
    Context context;
    Activity activity;


    public IlanlarimAdapter(List<IlanlarimPojo> list, Context context,Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarimlayout, parent, false);
        ImageView resim;
        TextView baslik, fiyat;




        resim = (ImageView) convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik = (TextView) convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat = (TextView) convertView.findViewById(R.id.ilanlarimIlanFİyat);




        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());

        Picasso.with(context).load("http://shopalisveris.com/" + list.get(position).getResim()).resize(100,100).into(resim);

        return convertView;
    }
}
