package com.example.sayan.mis571.util;

/**
 * Created by HEGDEA15 on 11/13/2016.
 */

public class Instructors {
    private int ID;
    private String Name;

    public Instructors(int id, String name){
        ID=id;
        Name = name;
    }

    public int GetID(){
        return ID;
    }

    public String GetName(){
        return  Name;
    }

    public void SetID(int id){
        ID = id;
    }

    public void SetName(String name){
        Name=name;
    }
}
