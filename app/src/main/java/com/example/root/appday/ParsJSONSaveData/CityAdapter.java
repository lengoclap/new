package com.example.root.appday.ParsJSONSaveData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.root.appday.R;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {

    Context context;
    ArrayList<DataCity> listData;

    public CityAdapter(Context context,ArrayList<DataCity> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {
        private TextView textViewCityName;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.city_item,null);
            viewHolder = new ViewHolder();
            viewHolder.textViewCityName = (TextView) view.findViewById(R.id.tv_city);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        DataCity city = listData.get(position);
        String cityName = city.getName();
        viewHolder.textViewCityName.setText(cityName);
        return view;
    }

}
