package com.example.root.appday.ParsJSONSaveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 03/10/2016.
 */
public class ParserData {
    List<DataCity> listData;
    private String dataPars;
    private DBHandlerSample handler;

    public ParserData(String dataPars) {
        this.dataPars = dataPars;
    }

    public ParserData() {
    }

    public List<DataCity> getDataCity (){

        listData = new ArrayList<DataCity>();
        try {
            JSONObject object = new JSONObject(dataPars);
            JSONArray cities = object.getJSONArray("cities");
            for (int i = 0 ; i<cities.length(); i++){
                JSONObject result = cities.getJSONObject(i);
                String cityDescription =  result.getString("description");
                String cityName=result.getString("name");
                String cityState=result.getString("state");
                DataCity dataCity = new DataCity();
                dataCity.setName(cityName);
                dataCity.setState(cityState);
                dataCity.setDescription(cityDescription);
                handler.addCity(dataCity);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listData;
    }
}
