package com.example.sayan.mis571.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import com.example.sayan.mis571.R;
import com.example.sayan.mis571.util.Conf_Rooms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEGDEA15 on 11/15/2016.
 */

public class conf_search_results  extends Fragment{

    private Cursor cursor;
    private ListView listView;
    private int userID;
    private List<Conf_Rooms> confList;
    public conf_search_results() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_conf_results, container, false);
        confList = new ArrayList<Conf_Rooms>();
        while(cursor.moveToNext()){
            confList.add(new Conf_Rooms(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2)));
        }
        listView = (ListView)rootView.findViewById(R.id.confRoom_listview);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity().getApplicationContext(), R.layout.fragment_confroom_listview, cursor,
                new String[] { "Floor", "Name"}, new int[] {
                R.id.txtfloorNo, R.id.txtFloorName },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        return rootView;
    }

    public void SetCursor(Cursor c){
        cursor = c;
    }

    public void SetUserID(int userid){
        userID = userid;
    }
}
