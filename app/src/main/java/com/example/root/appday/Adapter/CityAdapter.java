package com.example.root.appday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.root.appday.Models.DataCity;
import com.example.root.appday.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by root on 29/09/2016.
 */
public class CityAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataCity> listData;

    public CityAdapter(Context context, ArrayList<DataCity> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder= null;
        if (viewHolder==null){
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.city_item,null);
             viewHolder = new ViewHolder();
            viewHolder.textView= (TextView) view.findViewById(R.id.tv_city);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
            DataCity dataCity = listData.get(i);
            String cityName =  dataCity.getName();
            viewHolder.textView.setText(cityName);
        }



        return null;
    }

    public static class ViewHolder{
        private TextView textView;
    }

}
