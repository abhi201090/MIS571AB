package com.example.sayan.mis571.fragments;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HEGDEA15 on 11/15/2016.
 */

public class conf_search_results  extends Fragment{

    private Cursor cursor;
    private ListView listView;
    private int buildingID;
    private int userID;
    private Date startDate;
    private Date endDate;
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
            confList.add(new Conf_Rooms(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(2)),cursor.getString(1)));
        }
        listView = (ListView)rootView.findViewById(R.id.confRoom_listview);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity().getApplicationContext(), R.layout.fragment_confroom_listview, cursor,
                new String[] {"Floor", "Name"}, new int[] {
                R.id.txtfloorNo, R.id.txtFloorName },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ItemClickListener());

        return rootView;
    }

    public void SetCursor(Cursor c){
        cursor = c;
    }

    public void SetStartDate(Date d){
        startDate = d;
    }

    public void SetEndDate(Date d){
        endDate = d;
    }

    public void SetBuildingID(int bID){
        buildingID = bID;
    }
    public void SetUserID(int userid){
        userID = userid;
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String confID = cursor.getString(0);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

            try {
                String s = formatter.format(startDate);
                String start = formatter.format(startDate);
                String end = formatter.format(endDate);
                Object[] params = new Object[5];
                params[0] = Integer.parseInt(confID);
                params[1] = userID;
                params[2] = start;
                params[3] = end;
                params[4] = "Confirmed";
                //Cursor c = DBOperator.getInstance().execQuery(SQLCommand.GetInsertConfBookQuery(Integer.parseInt(confID), userID, startDate, endDate));
                DBOperator.getInstance().execSQL(SQLCommand.GetInsertConfBookQuery(),params);
                Cursor c = DBOperator.getInstance().execQuery(SQLCommand.GetConfBooks(buildingID, formatter.format(startDate),formatter.format(endDate)));
                int count = c.getCount();
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getActivity().getApplicationContext(), R.layout.fragment_confroom_listview, c,
                        new String[] {"Floor", "Name"}, new int[] {
                        R.id.txtfloorNo, R.id.txtFloorName },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
                listView.setAdapter(adapter);
            }
            catch (Exception e){
                String s = "";
            }
            Toast.makeText(getActivity().getApplicationContext(),"Conference Room Booked!", Toast.LENGTH_LONG).show();
        }
    }
}
