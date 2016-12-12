package com.example.sayan.mis571.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.sayan.mis571.R;
import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.Building;
import com.example.sayan.mis571.util.DBOperator;

import java.util.Calendar;
import java.util.Locale;

public class book_conf_room extends Fragment {
    private int UserID;
    private static List<Building> buildingList;
    private static Date startDateTime;
    private static Date endDateTime;
    private ImageButton btn;

    private Spinner spinnerBuilding;
    public book_conf_room() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_conf_room, container, false);
        spinnerBuilding = (Spinner)rootView.findViewById(R.id.spinnerBuilding);
        btn = (ImageButton) rootView.findViewById(R.id.btnSearchConf);
        buildingList = new ArrayList<Building>();
        startDateTime = new Date();
        endDateTime = new Date();
        LoadBuildings(rootView);
        return rootView;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final EditText st_time = (EditText)getActivity().findViewById(R.id.starttime2);
        final EditText end_time = (EditText)getActivity().findViewById(R.id.endtime2);
        final EditText dateselect = (EditText) getActivity().findViewById(R.id.dateselect);

        st_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);


                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //st_time.setText( selectedHour + ":" + selectedMinute);
                        startDateTime.setHours(selectedHour);
                        startDateTime.setMinutes(selectedMinute);
                        SimpleDateFormat formatt = new SimpleDateFormat("HH:mm");
                        st_time.setText(formatt.format(startDateTime));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Date");
                mTimePicker.show();
            }
        });

        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //dateselect.setText(month + "/" + dayOfMonth + "/" + year);
                        startDateTime.setYear(year - 1900);
                        startDateTime.setMonth(month);
                        startDateTime.setDate(dayOfMonth);
                        endDateTime.setYear(year - 1900);
                        endDateTime.setMonth(month);
                        endDateTime.setDate(dayOfMonth);
                        SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                        dateselect.setText(formatt.format(startDateTime));
                    }
                }, year, month, day);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //end_time.setText( selectedHour + ":" + selectedMinute);
                        endDateTime.setHours(selectedHour);
                        endDateTime.setMinutes(selectedMinute);
                        SimpleDateFormat formatt = new SimpleDateFormat("HH:mm");
                        end_time.setText(formatt.format(endDateTime));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date current = Calendar.getInstance().getTime();
                String s = spinnerBuilding.getSelectedItem().toString();
                if(startDateTime.after(current) && endDateTime.after(startDateTime) && !spinnerBuilding.getSelectedItem().toString().equals("Select a Building")){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                    int buildingID = buildingList.stream().filter(b -> b.GetBuildingName().equals(spinnerBuilding.getSelectedItem().toString())).findFirst().get().GetBuildingID();
                    Cursor c = DBOperator.getInstance().execQuery(SQLCommand.GetConfBooks(buildingID, formatter.format(startDateTime),formatter.format(endDateTime)));
                    int count = c.getCount();
                    if(c.getCount()>0){
                        conf_search_results results = new conf_search_results();
                        results.SetUserID(UserID);
                        results.SetBuildingID(buildingID);
                        results.SetEndDate(endDateTime);
                        results.SetStartDate(startDateTime);
                        results.SetCursor(c);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentholder, results);
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }

    public void SetUserID(int userID){
        UserID = userID;
    }

    public void LoadBuildings(View rootView){
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetBuildings());
        String[] values = new String[(cursor.getCount()+1)];
        int i =0;
        if(cursor.getCount()!=0) {
            values[0] = "Select a Building";
            while(cursor.moveToNext()){
                buildingList.add(new Building(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
                values[i+1] = cursor.getString(1);
                i++;
            }
            ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item, values);
            spinnerBuilding.setAdapter(adapt);
        }
    }
}
