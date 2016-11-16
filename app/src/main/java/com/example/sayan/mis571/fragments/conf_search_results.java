package com.example.sayan.mis571.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sayan.mis571.R;

/**
 * Created by HEGDEA15 on 11/15/2016.
 */

public class conf_search_results  extends Fragment{
    public conf_search_results() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_conf_results, container, false);
        return rootView;
    }
}
