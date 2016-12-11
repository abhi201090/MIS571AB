package com.example.sayan.mis571.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
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
//
//        final EditText username = (EditText)rootView.findViewById(R.id.EditTxtUsername);
//        final EditText password = (EditText)rootView.findViewById(R.id.EditTxtPass);
//
//        TextView welcome = (TextView) rootView.findViewById(R.id.textView7);
//        TextView firstName = (TextView) rootView.findViewById(R.id.textView10);
//        TextView lastName = (TextView) rootView.findViewById(R.id.textView8);
//        TextView department = (TextView) rootView.findViewById(R.id.textView12);
//        TextView degree = (TextView) rootView.findViewById(R.id.textView13);
//        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.GetFirstName(UserID));
//        String FirstName = cursor1.getString(0);
//        firstName.setText("First Name: " + FirstName);
//        Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.GetLasttName(UserID));
//        String LastName = cursor2.getString(0);
//        lastName.setText("Last Name: " + cursor2);
//        Cursor cursor3 = DBOperator.getInstance().execQuery(SQLCommand.GetDepartment(UserID));
//        String Department = cursor3.getString(0);
//        department.setText("Department: " + cursor3);
//        Cursor cursor4 = DBOperator.getInstance().execQuery(SQLCommand.GetDegree(UserID));
//        String Degree = cursor4.getString(0);
//        degree.setText("Degreee: " + cursor4);

        return rootView;
    }
}