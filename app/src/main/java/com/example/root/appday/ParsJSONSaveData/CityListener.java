package com.example.root.appday.ParsJSONSaveData;

import com.example.root.appday.ParsJSONSaveData.DataCity;

import java.util.ArrayList;

/**
 * Created by root on 28/09/2016.
 */
public interface CityListener {

    public void addCity(DataCity city);
    public ArrayList<DataCity> getAllCity();
    public int getCityCount();

}
