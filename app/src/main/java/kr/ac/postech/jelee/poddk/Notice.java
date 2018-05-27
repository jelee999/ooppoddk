package kr.ac.postech.jelee.poddk;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by ljh45 on 2018-05-20.
 */

public class Notice {
    String title;//공지사항 제목
    String Content;//공지사항 내용
    String date;//공지 날짜

    public Notice(String title, String content, String date) {
        this.title = title;
        Content = content;
        this.date = date;
    }//공지사항에 대한 생성자


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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
