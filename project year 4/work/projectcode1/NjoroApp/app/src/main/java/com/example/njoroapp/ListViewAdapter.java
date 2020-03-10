package com.example.njoroapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.njoroapp.Courses.AnimActivity;
import com.example.njoroapp.Courses.BlocActivity;
import com.example.njoroapp.Courses.CloudComputing;
import com.example.njoroapp.Courses.CompnetworksActivity;
import com.example.njoroapp.Courses.MobileComputing;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {


    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;


    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView mTitleTv, mDescTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_example, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);
            holder.mIconIv = view.findViewById(R.id.mainIcon);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //set the results into textview
        holder.mTitleTv.setText(modellist.get(postition).getTitle());
        holder.mDescTv.setText(modellist.get(postition).getDesc());
        //set the result in imageview
        holder.mIconIv.setImageResource(modellist.get(postition).getIcon());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later


                if (modellist.get(postition).getTitle().equals("Artificial Intelligence")){

                    Intent intent = new Intent(mContext, ItActivity.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Networking")){
                    Intent intent = new Intent(mContext, CompnetworksActivity.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Cloud Computing")){
                    Intent intent = new Intent(mContext, CloudComputing.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("BlockChain")){
                    Intent intent = new Intent(mContext, BlocActivity.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Animation")){
                    Intent intent = new Intent(mContext, AnimActivity.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Mobile Computing")){
                    Intent intent = new Intent(mContext, MobileComputing.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("IoT(Internet of things)")){
                    Intent intent = new Intent(mContext, BlocActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });


        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}