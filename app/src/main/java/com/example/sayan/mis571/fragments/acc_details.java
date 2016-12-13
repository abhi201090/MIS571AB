package com.example.sayan.mis571.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sayan.mis571.R;
import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.DBOpenHelper;
import com.example.sayan.mis571.util.DBOperator;

public class acc_details extends Fragment {
    private static int UserID = 0;

    public acc_details() {
        // Required empty public constructor
    }

    public void SetUserID(int userID){
        UserID = userID;
    }

    private TextView classroom, from, to, status;
    private TextView name1, name2, name3, name4, name5;
    private TextView from1, from2, from3, from4, from5;
    private TextView to1, to2, to3, to4, to5;
    private TextView status1, status2, status3, status4, status5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_acc_details, container, false);

        name1 = (TextView) rootView.findViewById(R.id.name1);
        from1= (TextView) rootView.findViewById(R.id.from1);
        to1 = (TextView) rootView.findViewById(R.id.to1);
        status1 = (TextView) rootView.findViewById(R.id.st1);
        name2 = (TextView) rootView.findViewById(R.id.name2);
        from2 = (TextView) rootView.findViewById(R.id.from2);
        to2 = (TextView) rootView.findViewById(R.id.to2);
        status2= (TextView) rootView.findViewById(R.id.st2);

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ShowRoomBooked(UserID));
//        int recordcount = cursor.getCount();
//        while (cursor.getPosition() < recordcount){
        int i = cursor.getCount();
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            name1.setText(cursor.getString(0));
            from1.setText(cursor.getString(1));
            to1.setText(cursor.getString(2));
            status1.setText(cursor.getString(3));

            if (cursor.getCount() > 1) {
                cursor.moveToNext();
                name2.setText(cursor.getString(0));
                from2.setText(cursor.getString(1));
                to2.setText(cursor.getString(2));
                status2.setText(cursor.getString(3));
            }
        }
        else{
            //TODO: write reminder if no record.
        }
        return rootView;
    }

    public void ClearData(){
        name1.setText("");
        from1.setText("");
        to1.setText("");
        status1.setText("");
    }
}
