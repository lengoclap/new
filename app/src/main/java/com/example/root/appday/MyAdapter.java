package com.example.root.appday;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 20/09/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
//    private List<String> listImageView;
//    private List<String> listContent;
//    private List<String> listName;
    private List<MyData> listManager;


//
//    public MyAdapter(Context context, List<String> listImageView, List<String> listContent, List<String> listName) {
//        this.context = context;
//        this.listImageView = listImageView;
//        this.listContent = listContent;
//        this.listName = listName;
//    }
//
//    public MyAdapter(Context context, List<String> listContent, List<String> listName) {
//        this.context = context;
//        this.listContent = listContent;
//        this.listName = listName;
//    }

    public MyAdapter(Context context, List<MyData> listManager) {
        this.context = context;
        this.listManager = listManager;
    }

    public void backupData (List<MyData> listManager){

        this.listManager = listManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_manger, parent, false);
        MyAdapter.ViewHolder rView = new MyAdapter.ViewHolder(view);
        return rView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.imAvata.setImageResource(R.drawable.ic_launcher);
//        holder.mName.setText(myDataList.get(position).getmName());
//        holder.imAvata.setImageResource(position);
//        holder.mContent.setText(listContent.get(position));
        holder.mContent.setText(listManager.get(position).getmContent());
//        holder.mName.setText(listName.get(position));
        holder.mName.setText(listManager.get(position).getmName());
//        Glide.with(context).load(listImageView.get(position)).into(((ViewHolder) holder).imAvata);
        Glide.with(context).load(listManager.get(position).getmImageView()).into(((ViewHolder) holder).imAvatar);

//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i            }
//        });
    }

    @Override
    public int getItemCount() {
        return listManager.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imAvatar;
        public TextView mName;
        public TextView mContent;
        public CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imAvatar = (ImageView) itemView.findViewById(R.id.ivAvata);
            mName = (TextView) itemView.findViewById(R.id.tvName);
            mContent = (TextView) itemView.findViewById(R.id.tvContent);
            mCardView= (CardView) itemView.findViewById(R.id.card_view);

        }
    }
}
