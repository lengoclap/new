package com.example.root.appday.ParsJSONSaveData;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.root.appday.NetworkUtils;
import com.example.root.appday.R;

import java.util.ArrayList;

public class SampleSaveDataSQLActivity extends AppCompatActivity {

   private ListView listView;
   private CityAdapter adapter;
   private ArrayList<DataCity> cityArrayList;
   private DBHandlerSample handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_save_data_sql);

        listView = (ListView) findViewById(R.id.list_view_city);
        handler = new DBHandlerSample(this);

        NetworkUtils utils = new NetworkUtils(SampleSaveDataSQLActivity.this);
        if(handler.getCityCount() == 0 && utils.isConnectingToInternet())
        {
            new DataFetcherTask().execute();
        }
        else {
            ArrayList<DataCity> cityList = handler.getAllCity();
            adapter = new CityAdapter(SampleSaveDataSQLActivity.this, cityList);
            listView.setAdapter(adapter);
        }
    }

    private class DataFetcherTask extends AsyncTask<String,Void,String> {

        StringBuilder dataApp ;
        @Override
        protected String doInBackground(String... strings) {

            return null;
        }
    }
}
