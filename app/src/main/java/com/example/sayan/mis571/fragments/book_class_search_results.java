package com.example.sayan.mis571.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.sayan.mis571.R;
import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.Conf_Rooms;
import com.example.sayan.mis571.util.DBOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEGDEA15 on 12/6/2016.
 */

public class book_class_search_results extends Fragment {
    private String term,day,start_time,end_time;
    private int course_id,user_id,year,buildingID,hascomp,hasMicro,hasProj,capacity;
    private Cursor cursor;
    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classbook_results, container, false);
        listView = (ListView)rootView.findViewById(R.id.classRoom_listview);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity().getApplicationContext(), R.layout.fragment_classbook_listview, cursor,
                new String[] {"Floor", "Name"}, new int[] {
                R.id.txtClassfloorNo, R.id.txtClassFloorName },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new book_class_search_results.ItemClickListener());

        return rootView;
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            int classID = Integer.parseInt(cursor.getString(0));
            try {
                Cursor c = DBOperator.getInstance().execQuery(SQLCommand.GetInsertClassBook(user_id,classID,term,year,course_id,day,start_time,end_time));

                c = DBOperator.getInstance().execQuery(SQLCommand.GetClassBookings(start_time,end_time,day,term,year,buildingID,capacity,hascomp,hasMicro,hasProj));
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getActivity().getApplicationContext(), R.layout.fragment_confroom_listview, c,
                        new String[] {"Floor", "Name"}, new int[] {
                        R.id.txtClassfloorNo, R.id.txtClassFloorName },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
                listView.setAdapter(adapter);
                Toast.makeText(getActivity().getApplicationContext(),"Class Booked!", Toast.LENGTH_LONG).show();

            }
            catch (Exception e){

            }

        }
    }

    public void SetValues(String start_time, String end_time,String day,String term, int year,int courseId,Cursor c,int userID,int buildingID,int hasComp,int hasProj,int hasMicro,int capacity){
        this.start_time = start_time;
        this.end_time = end_time;
        this.term = term;
        this.day = day;
        course_id = courseId;
        user_id = userID;
        this.year = year;
        cursor = c;
        this.buildingID = buildingID;
        this.hascomp = hasComp;
        this.hasMicro=hasMicro;
        this.hasProj = hasProj;
        this.capacity = capacity;
    }
}
