package com.example.mk.otogalerim.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk.otogalerim.Models.IlanlarPojo;
import com.example.mk.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mk on 17.02.2018.
 */

public class IlanlarAdapter extends BaseAdapter {

    List<IlanlarPojo> ılanlarPojoList;
    Context context;

    public IlanlarAdapter(List<IlanlarPojo> ılanlarPojoList, Context context) {
        this.ılanlarPojoList = ılanlarPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ılanlarPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return ılanlarPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarlayout, parent, false);
        TextView baslik, fiyat, adres;
        ImageView resim;
        baslik = (TextView) convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat = (TextView) convertView.findViewById(R.id.ilanlarimIlanFİyat);
        adres = (TextView) convertView.findViewById(R.id.ilanlarIlanAdres);
        resim = (ImageView) convertView.findViewById(R.id.ilanlarIlanResim);
        baslik.setText(ılanlarPojoList.get(position).getBaslik());
        fiyat.setText(ılanlarPojoList.get(position).getFiyat());
        adres.setText(ılanlarPojoList.get(position).getIl() + " / " + ılanlarPojoList.get(position).getIlce() + " / " + ılanlarPojoList.get(position).getMahalle());
        Picasso.with(context).load("http://shopalisveris.com/" + ılanlarPojoList.get(position).getResim()).resize(100, 100).into(resim);


        return convertView;


    }
}
