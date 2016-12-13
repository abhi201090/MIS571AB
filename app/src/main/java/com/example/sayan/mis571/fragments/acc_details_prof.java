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

public class acc_details_prof extends Fragment {
    private static int UserID = 0;

    public acc_details_prof() {
        // Required empty public constructermr
    }

    public void SetUserID(int userID){
        UserID = userID;
    }

    private TextView cid, ccname, cterm, cctime;
    private TextView cid1, cid2, id3, id4, id5;
    private TextView cname1, cname2, cname3, cname4, cname5;
    private TextView cterm1, cterm2, term3, term4, term5;
    private TextView ctime1, ctime2, ctime3, ctime4, ctime5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.acc_details_prof, container, false);

        cid1 = (TextView) rootView.findViewById(R.id.cid1);
        cname1= (TextView) rootView.findViewById(R.id.cname1);
        cterm1 = (TextView) rootView.findViewById(R.id.cterm1);
        ctime1 = (TextView) rootView.findViewById(R.id.ctime1);
        cid2 = (TextView) rootView.findViewById(R.id.cid2);
        cname2 = (TextView) rootView.findViewById(R.id.cname2);
        cterm2 = (TextView) rootView.findViewById(R.id.cterm2);
        ctime2= (TextView) rootView.findViewById(R.id.ctime2);

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.ShowClassBooked(UserID));
        int recordcount = cursor.getCount();
//        while (cursor.getPosition() < recordcount){
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            cid1.setText(cursor.getString(0));
            cname1.setText(cursor.getString(1));
            cterm1.setText(cursor.getString(2) + "    " + cursor.getString(3));
            ctime1.setText(cursor.getString(4) + "    " + cursor.getString(5) + "-" + cursor.getString(6));

            if (cursor.getCount() > 1) {
                cursor.moveToNext();
                cid2.setText(cursor.getString(0));
                cname2.setText(cursor.getString(1));
                cterm2.setText(cursor.getString(2) + "    " + cursor.getString(3));
                ctime2.setText(cursor.getString(4) + "    " + cursor.getString(5) + "-" + cursor.getString(6));
            }
        }

        else{
            //TODO: write reminder if no record.
        }
        return rootView;
    }
}
