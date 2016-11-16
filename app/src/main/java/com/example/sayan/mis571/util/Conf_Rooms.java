package com.example.sayan.mis571.util;

/**
 * Created by HEGDEA15 on 11/16/2016.
 */

public class Conf_Rooms {
    private int ID;
    private int floorNo;
    private String Name;

    public Conf_Rooms(int id, int fNo, String name){
        ID = id;
        floorNo = fNo;
        Name = name;
    }

    public void SetFloorNo(int fNo){
        floorNo = fNo;
    }
    public void SetID(int id){
        ID = id;
    }
    public void SetName(String name){
        Name = name;
    }

    public int GetFloorNo(){
        return floorNo;
    }
    public int GetID(){
        return  ID;
    }
    public String GetName(){
        return Name;
    }
}
