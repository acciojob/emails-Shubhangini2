package com.driver;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity;
    //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    //private ArrayList<Triple<Date, String, String>> Inbox;
   private ArrayList<Mail> Trash;

    private ArrayList<Mail> inbox;

    public Gmail(String emailId, int inboxCapacity){
        super(emailId);
        this.inboxCapacity= inboxCapacity;
        this.inbox= new ArrayList<>();
      // this.Inbox= new ArrayList<>();
       this.Trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inbox.size()==inboxCapacity){
            // Triple<Date, String, String> oldestMail = Inbox.get(0);
           // Inbox.remove(0);
           // Trash.add(oldestMail);

            Mail oldestMail= inbox.get(0);
            Mail newMail = new Mail(date, sender, message);
            inbox.add(newMail);
            Trash.add(oldestMail);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

       /* int index=-1;
        for(int i=0; i< Inbox.size(); i++){
            if(message.equals(Inbox.get(i).getRight())){ //message is at third place
                index=i;
                break;
            }
        } */


        Mail mailToDelete=null;
        for(Mail mail : inbox){
            if(message.equals(mail.getMessage())){
                mailToDelete=mail;
            }
        }

        if(mailToDelete != null){
            inbox.remove(mailToDelete);
            Trash.add(mailToDelete);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        int index= inbox.size()-1;

        if(inbox.isEmpty()) {
            return null;
        }
        else{
            return inbox.get(index).getMessage();
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }
        else{
            return inbox.get(0).getMessage();
        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int numberOfMails=0;
        for(int index=0; index <inbox.size(); index++){
            //Comparison of dates
            if((inbox.get(index).getDate().compareTo(start)>=0) && (inbox.get(index).getDate().compareTo(end)<=0)){

                numberOfMails+=1;
            }
        }

        return numberOfMails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox

        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash

        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox

        return inboxCapacity;
    }
}
