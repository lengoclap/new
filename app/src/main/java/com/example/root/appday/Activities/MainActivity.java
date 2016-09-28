package com.example.root.appday.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.root.appday.ActivityTest.NavigationDrawerActivity;
import com.example.root.appday.Adapter.MyAdapter;
import com.example.root.appday.Models.MyData;
import com.example.root.appday.JSON.ParserDataJSON;
import com.example.root.appday.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MyAdapter mMyAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout loadMore;
    private String path = "https://alpha-api.app.net/stream/0/posts/stream/global";
    private DownLoadData downLoadData;
    private ProgressDialog mProgressDialog;
    private List<MyData> listManager;
    private boolean isLoading = false;
    private boolean isCheckConnection = true;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load Data refresh khi dau trang
        loadMore = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        loadMore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DownLoadData().execute(path);
                mProgressDialog.dismiss();
            }
        });

        //Load data khi croll cuoi trang
        mRecyclerView = (RecyclerView) findViewById(R.id.rvTest);
        mRecyclerView.setHasFixedSize(true);
        //kiem tra khi scroll het trang se load data
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //kiem tra layout cuoi man hinh chua
                if (dy > 0) {
                    if ((mLayoutManager.getChildCount() + mLayoutManager.findFirstVisibleItemPosition())
                            >= mLayoutManager.getItemCount()) {
                        if (isLoading == false) {
                            new DownLoadData().execute(path);
                            mProgressDialog.dismiss();
                        }
                    }
                }
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        listManager = new ArrayList<>();

        mMyAdapter = new MyAdapter(getApplication(), listManager);
        mRecyclerView.setAdapter(mMyAdapter);

//        //Test RecyclerView
//        list = new ArrayList<>();
//        MyData myData = new MyData("1111","aaaa");
//        MyData myData1 = new MyData("2222","bbbb");
//        MyData myData3 = new MyData("3333","cccc");
//        list.add(myData);
//        list.add(myData1);
//        list.add(myData3);


        mRecyclerView = (RecyclerView) findViewById(R.id.rvTest);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        downLoadData = new DownLoadData();
        downLoadData.execute(path);
    }

    public void startNavigationDrawer(View view) {
        Intent intent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
        startActivity(intent);
    }


    public class DownLoadData extends AsyncTask<String, Void, String> {

        StringBuilder dataLoad;

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                dataLoad = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    dataLoad.append(line);
                }
//                //print log check
//                Log.d("data", dataLoad.toString());
                inputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                isCheckConnection = false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "no connection", Toast.LENGTH_SHORT).show();
                    }
                });
                return e.toString();
            }
            return dataLoad.toString();
        }

        @Override
        protected void onPreExecute() {
            isLoading = true;
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressDialog.dismiss();
            loadMore.setRefreshing(false);
            isLoading = false;

            if (isRefresh && isCheckConnection){
                listManager.clear();
            }

            ParserDataJSON parserDataJSON = new ParserDataJSON(s);

            List<String> listContentResult = parserDataJSON.getListContent();

            List<String> listName = parserDataJSON.getListName();

            List<String> listImageView = parserDataJSON.getListImage();

            for (int i = 0; i < listContentResult.size(); i++) {

                MyData myData = new MyData(listImageView.get(i),
                        listName.get(i),
                        listContentResult.get(i));
                listManager.add(myData);
            }
            mMyAdapter.saveData(listManager);
            mMyAdapter.notifyDataSetChanged();
//            mAdapter = new MyAdapter(getApplication(),listImageView,listContentResult,listName);

        }
    }
}
