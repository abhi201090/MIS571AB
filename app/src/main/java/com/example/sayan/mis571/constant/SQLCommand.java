package com.example.sayan.mis571.constant;

import android.text.Editable;
import java.util.Date;

import com.example.sayan.mis571.util.DBOperator;



public abstract class SQLCommand
{
    public static String GetUser(Editable username, Editable password){
        String login_Query = "SELECT userID,userType FROM users WHERE username = '"+username.toString()+"' AND password = '"+password.toString()+"'";
        return login_Query;
    }

    public static String GetStudentCourses(int UserID){
        String query = "SELECT T2.ID, T2.CourseCode||'-'||T2.Title FROM StudentCourse AS T1 " +
                "JOIN Course AS T2 ON T1.CourseID = T2.ID " +
                "WHERE StudentID = " + UserID;
        return  query;
    }

    public static String GetCourseProfessors(int CourseID){
        String query = "SELECT T2.ID, T2.FirstName||' '||T2.LastName FROM InstructorCourse AS T1 " +
                "JOIN  Instructors AS T2 ON T1.InstructorID = T2.ID " +
                "WHERE CourseID = " + CourseID;
        return query;
    }

    public static String GetBuildings(){
        String query = "select * from Buildings";
        return query;
    }

    public static String GetConfBooks(int buildingID, Date start, Date end){
        String query = "SELECT ID as _id, Name,Floor FROM ConferenceRooms WHERE ID NOT IN (\n" +
                "SELECT T2.ID FROM ConfBook AS T1\n" +
                "JOIN ConferenceRooms AS T2 ON T1.ConferenceRoomID = T2.ID\n" +
                "JOIN Buildings AS T3 ON T3.ID = T2.Building\n" +
                "WHERE (T1.ToDate > Datetime('"+start+"') OR T1.FromDate< Datetime('"+end+"')) AND T3.ID = "+buildingID+") AND Building = "+buildingID;
        return  query;
    }

    public static String GetInsertConfBookQuery(int confRoomID,int userID, Date startDate, Date endDate){
        String query = "INSERT INTO ConfBook(ConferenceRoomID, UserID, FromDate,ToDate,Status) Values("+confRoomID+","+userID+",'"+startDate+"','"+endDate+"','Confirmed')";
        return query;
    }


}