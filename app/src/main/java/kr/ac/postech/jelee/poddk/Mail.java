package kr.ac.postech.jelee.poddk;

import android.media.Image;

/**
 * Created by ljh45 on 2018-05-19.
 */

public class Mail {
    private String mailTitle;//메일 제목
    private String mailContent;//메일 내용
    private String senderID;//송신자 ID
    private String date;//메일 발송일
    private String senderName;

    Mail(String mailTitle, String mailContent, String senderID, String date, String senderName) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderID = senderID;
        this.date = date;
        this.senderName = senderName;
    }//메일 Constructor


    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderName(){
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
