package com.example.agendablenda;

import java.sql.Time;
import java.util.ArrayList;

public class Schedule {

    String courseName;
    ArrayList<String> days;
    Time starttime;
    Time endTime;

    public Schedule(String courseName, ArrayList<String> days, Time starttime, Time endTime) {
        this.courseName = courseName;
        this.days = days;
        this.starttime = starttime;
        this.endTime = endTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public Time getStarttime() {
        return starttime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
