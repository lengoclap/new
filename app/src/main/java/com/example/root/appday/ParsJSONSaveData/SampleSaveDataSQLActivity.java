package com.example.root.appday.ParsJSONSaveData;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.appday.NetworkUtils;
import com.example.root.appday.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SampleSaveDataSQLActivity extends AppCompatActivity {

    private CityAdapter mCityAdapter;
    private DBHandlerSample mHandler;
    private ArrayList<DataCity> cityArrayList;

    private String path = "http://beta.json-generator.com/api/json/get/GAqnlDN";
    private DataFetcherTask mDatafetcherTask;
    private ListView listView;
    private boolean isNetworkConnection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_save_data_sql);

        listView = (ListView) findViewById(R.id.list_view_city);
        mHandler = new DBHandlerSample(this);



        mDatafetcherTask = new DataFetcherTask();
        mDatafetcherTask.execute(path);
    }


    private class DataFetcherTask extends AsyncTask<String, Void, String> {

        StringBuilder dataApp;


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
//                 Internet connection and load data
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();
                dataApp = new StringBuilder();
                while (line != null) {
                    dataApp.append(line);
                }

                httpURLConnection.disconnect();
                inputStream.close();
                inputStreamReader.close();
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                isNetworkConnection = false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SampleSaveDataSQLActivity.this, "no connection...", Toast.LENGTH_SHORT).show();
                    }
                });
                return e.toString();
//                e.printStackTrace();
            }
            return dataApp.toString();
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
//            String dataPars = null;
//            try {
//                cityArrayList = new ArrayList<DataCity>();
//                JSONObject jsonObject = new JSONObject(dataPars);
//                JSONArray jsonArray = jsonObject.getJSONArray("cities");
//                for (int i=0;i<jsonArray.length();i++)
//                {
//                    JSONObject jsonObjectCity = jsonArray.getJSONObject(i);
//                    String cityName = jsonObjectCity.getString("name");
//                    String cityState = jsonObjectCity.getString("state");
//                    String cityDescription = jsonObjectCity.getString("description");
//                    DataCity city = new DataCity();
//                    city.setName(cityName);
//                    city.setState(cityState);
//                    city.setDescription(cityDescription);
//                    handler.addCity(city);// Inserting into DB
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            ParserData data = new ParserData();
            data.getDataCity();
            cityArrayList = new ArrayList<>();
            mCityAdapter = new CityAdapter(SampleSaveDataSQLActivity.this, cityArrayList);
            listView.setAdapter(mCityAdapter);
//            mCityAdapter.getView()
            super.onPostExecute(s);
        }
    }
}
