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

public class prof_home_fragment extends Fragment {

    private static int UserID = 0;


    public prof_home_fragment() {
        // Required empty public constructor
    }

    public void SetUserID(int userID){
        UserID = userID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prof_home, container, false);
//

        TextView firstNamevalIns = (TextView) rootView.findViewById(R.id.FirstNameValIns);
        TextView lastNamevalIns = (TextView) rootView.findViewById(R.id.LastNameValIns);
        TextView departmentvalIns = (TextView) rootView.findViewById(R.id.DeptValIns);

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetInsInfo(UserID));
        cursor.moveToFirst();
        firstNamevalIns.setText   (cursor.getString(0));
        lastNamevalIns.setText    (cursor.getString(1));
        departmentvalIns.setText  (cursor.getString(2));


        return rootView;
    }
}