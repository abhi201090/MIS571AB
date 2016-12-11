package com.example.sayan.mis571.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sayan.mis571.R;
import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.DBOperator;

public class student_home_fragment extends Fragment {
    private static int UserID = 0;

    public void SetUserID(int userID){
        UserID = userID;
    }

    public student_home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student_home, container, false);

        TextView welcome = (TextView) rootView.findViewById(R.id.Welcome);
        TextView firstName = (TextView) rootView.findViewById(R.id.FirstName);
        TextView lastName = (TextView) rootView.findViewById(R.id.LastName);
        TextView department = (TextView) rootView.findViewById(R.id.Dept);
        TextView degree = (TextView) rootView.findViewById(R.id.Degree);
        TextView year = (TextView) rootView.findViewById(R.id.Year);
        TextView firstNameval = (TextView) rootView.findViewById(R.id.FirstNameVal);
        TextView lastNameval = (TextView) rootView.findViewById(R.id.LastNameVal);
        TextView departmentval = (TextView) rootView.findViewById(R.id.DeptVal);
        TextView degreeval = (TextView) rootView.findViewById(R.id.DegreeVal);
        TextView yearval = (TextView) rootView.findViewById(R.id.YearVal);
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetStudInfo(UserID));
        cursor.moveToFirst();
        firstNameval.setText   (cursor.getString(0));
        lastNameval.setText    (cursor.getString(1));
        departmentval.setText  (cursor.getString(2));
        degreeval.setText      (cursor.getString(3));
        yearval.setText        (cursor.getString(4));


        return rootView;
    }
}