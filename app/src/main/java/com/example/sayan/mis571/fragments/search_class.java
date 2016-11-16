package com.example.sayan.mis571.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.R;
import com.example.sayan.mis571.util.DBOperator;
import com.example.sayan.mis571.util.Instructors;
import com.example.sayan.mis571.util.Pair;
import com.example.sayan.mis571.util.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class search_class extends Fragment {
    private static int UserID = 0;
    private static List<Course> courseList ;
    private static List<Instructors> instList;
    private Spinner studentCourse;
    private Spinner spinnerProfessor;
    public search_class() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_class, container, false);
        courseList = new ArrayList<Course>();
        instList = new ArrayList<Instructors>();
        studentCourse = (Spinner)rootView.findViewById(R.id.spinnerCourse);
        spinnerProfessor = (Spinner)rootView.findViewById(R.id.spinnerProfessor);
        LoadDefaults(rootView);
        studentCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = studentCourse.getSelectedItem().toString();
                if(!courseList.isEmpty() && selectedValue != "Select a Course"){
                    Course course = courseList.stream().filter(c -> c.GetCourseCode().equals(selectedValue)).findFirst().get();
                    String q = SQLCommand.GetCourseProfessors(course.GetCourseID());
                    Cursor c = DBOperator.getInstance().execQuery(SQLCommand.GetCourseProfessors(course.GetCourseID()));
                    String[] values = new String[c.getCount()+1];
                    int i=0;
                    if(c.getCount()!= 0){
                        values[0] = "Select an Instructor";
                        while(c.moveToNext()){
                            instList.add(new Instructors(Integer.parseInt(c.getString(0)), c.getString(1)));
                            values[i+1] = c.getString(1);
                            i++;
                        }
                        ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(),
                                android.R.layout.simple_spinner_item, values);
                        spinnerProfessor.setAdapter(adapt);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }


    public void SetUserID(int userID){
        UserID = userID;
    }

    public void LoadDefaults(View rootView){
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetStudentCourses(UserID));
        String[] values = new String[(cursor.getCount()+1)];
        int i =0;
        if(cursor.getCount()!=0){
            values[0] = "Select a Course";
            while(cursor.moveToNext()){
                courseList.add(new Course(Integer.parseInt(cursor.getString(0)),cursor.getString(1)));
                values[i+1] = cursor.getString(1);
                i++;
            }
            ArrayAdapter adapt = new ArrayAdapter (getActivity().getApplicationContext(),
                    android.R.layout.simple_spinner_item, values);
            studentCourse.setAdapter(adapt);
        }
    }
}


