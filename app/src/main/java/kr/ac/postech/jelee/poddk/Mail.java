package kr.ac.postech.jelee.poddk;

import android.media.Image;

/**
 * Created by ljh45 on 2018-05-19.
 */

public class Mail {
    String title;
    String Content;
    String Name;
    String Date;
    int ImageID;

    public Mail(String title, String content, String date, int imageID) {
        this.title = title;
        Content = content;
        Date = date;
        ImageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}
