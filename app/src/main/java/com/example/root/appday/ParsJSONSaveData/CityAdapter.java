package com.example.root.appday.ParsJSONSaveData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.root.appday.R;

import java.util.ArrayList;

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
        ViewHolder mViewHolder= null;
        if (mViewHolder==null){
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.city_item,null);
             mViewHolder = new ViewHolder();
            mViewHolder.tvListCity= (TextView) view.findViewById(R.id.tv_city);
            view.setTag(mViewHolder);
        } else {
            mViewHolder= (ViewHolder) view.getTag();
            DataCity dataCity = listData.get(i);
            String cityName =  dataCity.getName();
            mViewHolder.tvListCity.setText(cityName);
        }



        return null;
    }

    public static class ViewHolder  {
        private TextView tvListCity;

    }

}
