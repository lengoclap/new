package com.example.root.appday;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout loadMore;
    private String path = "";
    private DownLoadData downLoadData;
    private ProgressDialog mProgressDialog;
    private List<MyData> listManager;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMore = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        loadMore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DownLoadData().execute(path);
                mProgressDialog.dismiss();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.rvTest);
        mRecyclerView.setHasFixedSize(true);
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

        mAdapter = new MyAdapter(getApplication(), listManager);
        mRecyclerView.setAdapter(mAdapter);

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
                Log.d("data", dataLoad.toString());
                inputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            return null;
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
                mAdapter.backupData(listManager);
            mAdapter.notifyDataSetChanged();
//            mAdapter = new MyAdapter(getApplication(),listImageView,listContentResult,listName);

        }
    }
}
