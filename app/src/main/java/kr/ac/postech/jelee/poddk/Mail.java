package kr.ac.postech.jelee.poddk;

import android.media.Image;

/**
 * Created by ljh45 on 2018-05-19.
 */

public class Mail {
    String mailTitle;
    String mailContent;
    String senderID;
    String senderName;
    String date;
    int ImageID;

    public Mail( String mailTitle, String mailContent, String senderID, String senderName, String date, int ImageID) {
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.senderID = senderID;
        this.senderName = senderName;
        this.date = date;
        this.ImageID = ImageID;
    }


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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}
