package com.driver;

import java.time.LocalTime;

public class Meeting {
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public LocalTime getstartTime() {
        return this.startTime;
    }

    public LocalTime getendTime() {
        return this.endTime;
    }

}