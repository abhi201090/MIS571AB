package com.example.sayan.mis571.view;

import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by HEGDEA15 on 11/15/2016.
 */

public class ConfRoomListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;

    public ConfRoomListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            //convertView=inflater.inflate(R.layout.colmn_row, null);

            //txtFirst=(TextView) convertView.findViewById(R.id.name);
            //txtSecond=(TextView) convertView.findViewById(R.id.gender);
            //txtThird=(TextView) convertView.findViewById(R.id.age);
            //txtFourth=(TextView) convertView.findViewById(R.id.status);

        }

        HashMap<String, String> map=list.get(position);
        //txtFirst.setText(map.get(FIRST_COLUMN));
        //txtSecond.setText(map.get(SECOND_COLUMN));
        //txtThird.setText(map.get(THIRD_COLUMN));
        //txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }
}
