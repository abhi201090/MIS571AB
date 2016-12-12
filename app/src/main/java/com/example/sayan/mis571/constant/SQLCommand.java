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
        String query = "SELECT T2.ID, T2.CourseCode||'-'||T2.Title FROM StudentCourse AS T1 \n" +
                "JOIN Course AS T2 ON T1.CourseID = T2.ID\n" +
                "JOIN Students AS T3 ON T3.ID = T1.StudentID\n" +
                "WHERE T3.UserID = "+UserID;
        return  query;
    }
    public static String GetStudInfo(int UserID){
        String query = "SELECT FirstName, LastName, Departments.Department, Degree, Year FROM Departments, Students WHERE " +
                "Departments.ID = Students.Department AND UserID = " + Integer.toString(UserID);
        return query;
    }

    public static String GetInsInfo(int UserID){
        String query = "SELECT FirstName, LastName, Departments.Department FROM Departments, Instructors WHERE " +
                "Departments.ID = Instructors.Department AND UserID = " + Integer.toString(UserID);
        return query;
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

    public static String GetConfBooks(int buildingID, String start, String end){
        String query = "SELECT ID as _id, Name,Floor FROM ConferenceRooms WHERE ID NOT IN (\n" +
                "SELECT T2.ID FROM ConfBook AS T1\n" +
                "JOIN ConferenceRooms AS T2 ON T1.ConferenceRoomID = T2.ID\n" +
                "JOIN Buildings AS T3 ON T3.ID = T2.Building\n" +
                "WHERE (T1.ToDate >= Datetime('"+start+"') OR T1.FromDate<= Datetime('"+end+"')) AND T3.ID = "+buildingID+") AND Building = "+buildingID;
        return  query;
    }

    public static String GetInsertConfBookQuery(){
        //String query = "INSERT INTO ConfBook(ConferenceRoomID, UserID, FromDate,ToDate,Status) Values("+confRoomID+","+userID+",'"+startDate+"','"+endDate+"','Confirmed')";
        String query = "INSERT INTO ConfBook(ConferenceRoomID, UserID, FromDate,ToDate,Status) Values(?,?,?,?,?)";
        return query;
    }

    public static String GetClassroomCapacities(int buildingID){
        String query = "select distinct capacity from Classrooms where building = " + buildingID;
        return query;
    }

    public static String GetProfCourses(int userID, String term){
        String query = "select T2.ID, T2.CourseCode || '-' || T2.Title from InstructorCourse as T1\n" +
                "join Course T2 on T1.CourseID = T2.ID\n" +
                "join Instructors T3 on T3.ID = T1.InstructorID\n" +
                "where T1.Semester = '"+term+"' and T3.UserID = " +userID;
        return query;
    }

    public static String GetClassBookings(String start_time, String end_time,String day,String term, int year,int building,int capacity,int hasComp, int hasMic, int hasProj){
        String query = "SELECT ID as _id, Name,Floor FROM Classrooms WHERE ID NOT IN \n" +
                "(SELECT Distinct T1.ClassID FROM ClassBook AS T1\n" +
                "JOIN Classrooms AS T2 ON T1.ClassID = T2.ID\n" +
                "JOIN Buildings As T3 ON T3.ID = T2.Building\n" +
                "WHERE (Time(T1.to_Time) >= Time('"+start_time+"') OR Time(T1.From_Time)<=Time('"+end_time+"')) AND T2.Building = "+building+"  AND T1.Day = '"+day+"' AND T1.Semester = '"+term+"' AND T1.Year = "+year+") \n" +
                "AND Capacity = " + capacity +
                " AND HasComputer = " + hasComp +
                " AND HasMic = " + hasMic +
                " AND HasProjector = " + hasProj +
                " AND Building = " + building;
        return query;
    }


    /*
    public static String GetInsertClassBook(int UserID,int ClassID,String term, int year, int courseID,String day, String start_time,String end_time){
        String query = "INSERT INTO ClassBook (UserID,ClassID,Semester,Year,CourseID,Status,Day,From_Time,To_Time) VALUES("+UserID+","+ClassID+",'"+term+"',"+year+","+courseID+",'Confirmed','"+day+"','"+start_time+"','"+end_time+"')";
        //String query = "INSERT INTO ClassBook (UserID,ClassID,Semester,Year,CourseID,Status,Day,From_Time,To_Time) VALUES(?,?,?,?,?,?,?,?,?)";
        return query;
    }*/

    public static String GetInsertClassBook(){
        //String query = "INSERT INTO ClassBook (UserID,ClassID,Semester,Year,CourseID,Status,Day,From_Time,To_Time) VALUES("+UserID+","+ClassID+",'"+term+"',"+year+","+courseID+",'Confirmed','"+day+"',Time('"+start_time+"'),Time('"+end_time+"'))";
        String query = "INSERT INTO ClassBook (UserID,ClassID,Semester,Year,CourseID,Status,Day,From_Time,To_Time) VALUES(?,?,?,?,?,?,?,?,?)";
        return query;
    }

    public static String GetClassDetails(int courseID,int instID){
        String query = "SELECT 'Floor: '||T3.Floor ||' Building: '||T4.Name, T1.From_Time||' to '||T1.To_Time FROM ClassBook T1\n" +
                "JOIN Instructors T2 ON T1.UserID=T2.UserID\n" +
                "JOIN Classrooms T3 ON T1.ClassID = T3.ID\n" +
                "JOIN Buildings T4 ON T3.Building = T4.ID\n" +
                "WHERE T1.CourseID = "+courseID+" AND T2.ID = "+instID;
        return query;
    }
}