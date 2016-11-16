package com.example.sayan.mis571.util;

/**
 * Created by HEGDEA15 on 11/13/2016.
 */

public class Course {
    private int CourseID;
    private String CourseCode;

    public Course(int courseID, String courseCode) {
        CourseID = courseID;
        CourseCode = courseCode;
    }
    public int GetCourseID(){
        return CourseID;
    }

    public String GetCourseCode(){
        return CourseCode;
    }

    public void SetCourse(int courseID, String courseCode){
        CourseID = courseID;
        CourseCode = courseCode;
    }

}
