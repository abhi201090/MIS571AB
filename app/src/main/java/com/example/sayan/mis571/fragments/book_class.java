package com.example.sayan.mis571.fragments;

import android.app.TimePickerDialog;
import java.sql.Time;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sayan.mis571.R;
import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.Building;
import com.example.sayan.mis571.util.Course;
import com.example.sayan.mis571.util.DBOperator;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class book_class extends Fragment {
    private int UserID;
    private static List<Building> buildingList;
    private static List<Course> courseList ;
    private Spinner spinnerBuilding;
    private Spinner spinnerCapacity;
    private ImageButton btn;
    private Building selectedBuilding;
    private Spinner spinnerTerm;
    private Spinner spinnerCourse;
    private Spinner spinnerDay;
    private CheckBox hasProjector,hasMic,hasComputer;

    public book_class() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_class, container, false);
        spinnerBuilding = (Spinner)rootView.findViewById(R.id.spinnerBuildingName);
        spinnerCapacity = (Spinner)rootView.findViewById(R.id.spinnerCapacity);
        spinnerTerm = (Spinner)rootView.findViewById(R.id.spinnerTerm);
        spinnerCourse = (Spinner)rootView.findViewById(R.id.spinnerCourse);
        spinnerDay = (Spinner)rootView.findViewById(R.id.spinnerDays);
        hasProjector = (CheckBox)rootView.findViewById(R.id.hasProjector);
        hasMic = (CheckBox)rootView.findViewById(R.id.hasMic);
        hasComputer = (CheckBox)rootView.findViewById(R.id.hasComputer);
        btn = (ImageButton)rootView.findViewById(R.id.btnSearch);

        buildingList = new ArrayList<Building>();
        courseList = new ArrayList<Course>();
        LoadBuildings(rootView);
        spinnerBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = spinnerBuilding.getSelectedItem().toString();
                if(!buildingList.isEmpty() && !selectedValue.equals("Select a Building")){
                    selectedBuilding = buildingList.stream().filter(b -> b.GetBuildingName().equals(selectedValue)).findFirst().get();
                    LoadCapacity(rootView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = spinnerTerm.getSelectedItem().toString();
                if(!selectedValue.equals("Select a Term")){
                    LoadCourses(rootView,selectedValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final EditText st_time = (EditText)getActivity().findViewById(R.id.starttime);
        final EditText end_time = (EditText)getActivity().findViewById(R.id.endtime);

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
                        st_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();
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
                        end_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTerm = spinnerTerm.getSelectedItem().toString();
                String selectedDay = spinnerDay.getSelectedItem().toString();
                int courseID = courseList.stream().filter(c -> c.GetCourseCode().equals(spinnerCourse.getSelectedItem().toString())).findFirst().get().GetCourseID();
                int capacity = Integer.parseInt(spinnerCapacity.getSelectedItem().toString());
                EditText st_time = (EditText)getActivity().findViewById(R.id.starttime);
                EditText end_time = (EditText)getActivity().findViewById(R.id.endtime);
                String start_time = st_time.getText().toString();
                String ed_time = end_time.getText().toString();
                boolean timeFlag = false;
                try {
                    DateFormat formatter = new SimpleDateFormat("HH:mm");
                    Time s_time = new Time(formatter.parse(start_time).getTime());
                    Time e_time = new Time(formatter.parse(ed_time).getTime());
                    timeFlag = s_time.getHours() < e_time.getHours();
                }
                catch (Exception e)
                {

                }
                if(!selectedTerm.equals("Select a Term") && !selectedDay.equals("Select a Day") && courseID!=0 && selectedBuilding != null && capacity !=0 && !start_time.equals("") && !ed_time.equals("") && timeFlag){
                    int hascomp = hasComputer.isChecked()?1:0;
                    int hasMicro = hasMic.isChecked()?1:0;
                    int hasProj = hasProjector.isChecked()?1:0;
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetClassBookings(start_time,ed_time,selectedDay,selectedTerm,year,selectedBuilding.GetBuildingID(),capacity,hascomp,hasMicro,hasProj));
                    int count = cursor.getCount();
                    if(count>0){
                        book_class_search_results results = new book_class_search_results();
                        results.SetValues(start_time,ed_time,selectedDay,selectedTerm,year,courseID,cursor,UserID,selectedBuilding.GetBuildingID(),hascomp,hasProj,hasMicro,capacity);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentholder, results);
                        fragmentTransaction.commit();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(),"No Result", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }


    public void SetUserID(int userID){
        UserID = userID;
    }

    public void LoadBuildings(View rootView){
        //SQL query to get all the Building ID, Building Name
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetBuildings());
        //build an array of String to hold all the values, set length
        String[] values = new String[(cursor.getCount()+1)];

        int i =0;
        if(cursor.getCount()!=0) {
            values[0] = "Select a Building";
            while(cursor.moveToNext()){
                buildingList.add(new Building(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
                values[i+1] = cursor.getString(1);
                i++;
            }
            ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, values);
            spinnerBuilding.setAdapter(adapt);
        }
    }

    public void LoadCapacity(View rootView){
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetClassroomCapacities(selectedBuilding.GetBuildingID()));
        String[] values = new String[(cursor.getCount()+1)];
        int i =0;
        if(cursor.getCount()!=0) {
            values[0] = "Select a Capacity";
            while(cursor.moveToNext()){
                values[i+1] = cursor.getString(0);
                i++;
            }
            ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, values);
            spinnerCapacity.setAdapter(adapt);
        }

    }

    public void LoadCourses(View rootView,String term){
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetProfCourses(UserID,term));
        String[] values = new String[(cursor.getCount()+1)];
        int i =0;
        if(cursor.getCount()!=0) {
            values[0] = "Select a Course";
            courseList.clear();
            while(cursor.moveToNext()){
                courseList.add(new Course(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
                values[i+1] = cursor.getString(1);
                i++;
            }
            ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, values);
            spinnerCourse.setAdapter(adapt);
        }
    }
}