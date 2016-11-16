package com.example.sayan.mis571.util;

/**
 * Created by HEGDEA15 on 11/13/2016.
 */

public class Building {
    private int ID;
    private String Name;

    public Building(int id, String name){
        ID=id;
        Name=name;
    }

    public int GetBuildingID(){
        return ID;
    }

    public String GetBuildingName(){
        return Name;
    }


}
