
package com.driver;
import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    PriorityQueue<Mails> Inbox = new PriorityQueue<Mails>((Mails a,Mails b)->  a.date.compareTo(b.date));
    PriorityQueue<Mails> Trash = new PriorityQueue<Mails>((Mails a,Mails b)->  a.date.compareTo(b.date));
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    int counter = 0;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(this.counter == this.inboxCapacity ) {
            Trash.add(this.Inbox.poll());
            this.counter--;
        }
        this.Inbox.add(new Mails(date,sender,message));
        this.counter++;


    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Queue<Mails> queue = new LinkedList<>();
        while(!Inbox.isEmpty()) {
            Mails temp = Inbox.poll();
            if(temp.message.equals(message)) {
                Trash.add(temp);
            }
            else {
                queue.add(temp);
            }
        }
        while(!queue.isEmpty()) {
            Inbox.add(queue.remove());
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.isEmpty()) {
            return null;
        }
        Queue<Mails> queue = new LinkedList<>();
        while(Inbox.size() > 1) {
            queue.add(Inbox.poll());
        }
        String ans = Inbox.peek().message;
        while(!queue.isEmpty()) {
            Inbox.add(queue.remove());
        }
        return ans;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size()==0) {
            return null;
        }
        else {
            return Inbox.peek().message;
        }

    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        Queue<Mails> tempQueue = new LinkedList<>();

        while (!Inbox.isEmpty()) {
            Mails temp = Inbox.poll();

            if (isDateInRange(temp.date, start, end)) {
                count++;
            }

            tempQueue.add(temp);
        }

        Inbox.addAll(tempQueue);

        return count;
    }

    private boolean isDateInRange(Date date, Date start, Date end) {
        return start.compareTo(date) <= 0 && date.compareTo(end) <= 0;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.Inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.Trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        while(!this.Trash.isEmpty()) {
            this.Trash.remove();
        }

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}

class Mails {
    Date date;
    String sender;
    String message;

    Mails(Date date,String sender,String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}