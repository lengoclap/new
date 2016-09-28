package com.example.root.appday.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 21/09/2016.
 */
public class ParserDataJSON {

    private String dataAPI;
    private List<String> listContent;
    private List<String> listName;
    private List<String> listImage;


    public ParserDataJSON(String data) {
        this.dataAPI = data;
    }


    public List<String> getListContent() {

        listContent = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(dataAPI);
            JSONArray result = object.getJSONArray("data");
            for (int i = 0; i < result.length(); i++) {
                JSONObject objectResult = result.getJSONObject(i);
                objectResult.optString("text");
//                Log.d("result", objectResult.getString("text").toString());
                listContent.add(objectResult.optString("text"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listContent;
    }

    public List<String> getListName() {

        listName = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dataAPI);
            JSONArray result = jsonObject.getJSONArray("data");

            for (int i = 0; i < result.length(); i++) {

                JSONObject source = result.getJSONObject(i);
                JSONObject name = source.getJSONObject("source");
                listName.add(name.optString("name"));

//                JSONObject source = result.getJSONObject(i);
//                source.optString("source");
//
//                JSONObject objectName = new JSONObject(source.optString("source"));
//                objectName.optString("name");
//                listName.add( objectName.optString("name"));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listName;
    }

    public List<String> getListImage() {
        listImage = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dataAPI);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1User = jsonArray.getJSONObject(i);

                JSONObject user = jsonObject1User.getJSONObject("user");

                JSONObject avatar_image = user.getJSONObject("avatar_image");

                listImage.add(avatar_image.optString("url"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listImage;
    }
}
