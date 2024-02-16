package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class  Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.size()==0) {
            return 0;
        }
        ArrayList<Meeting> temp = new ArrayList<>(calendar);
        Collections.sort(temp, (Meeting a, Meeting b) -> a.getstartTime().compareTo(b.getstartTime()));

        LocalTime start = calendar.get(0).getstartTime();
        LocalTime end = calendar.get(0).getendTime();
        int count = 1;

        for(int i=1;i<calendar.size();i++) {
            if(calendar.get(i).getstartTime().compareTo(start)>0 && calendar.get(i).getstartTime().compareTo(end)>0
                    && calendar.get(i).getendTime().compareTo(start)>0 && calendar.get(i).getendTime().compareTo(end)>0) {
                count++;
                start = calendar.get(i).getstartTime();
                end = calendar.get(i).getendTime();
            }

        }
        return count;

    }
}