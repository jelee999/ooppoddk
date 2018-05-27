package kr.ac.postech.jelee.poddk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class MailboxActivity extends AppCompatActivity {

    protected ListView mailListView;
    protected MailListAdapter adapter;
    protected List<Mail> mailList;
    protected TextView mailEmpty;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        SharedPreferences saved = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        ID = saved.getString("inputID","0");
        //SharedPreference 에 저장된 ID 값을 불러와서 해당 Activity의 ID 문자열에 저장.
        mailListView = (ListView) findViewById(R.id.mailListView);
        mailList = new ArrayList<Mail>();
        adapter = new MailListAdapter(getApplicationContext(), mailList);
        mailEmpty = (TextView)findViewById(R.id.EmptyMailTxt);
        mailListView.setAdapter(adapter);
        mailListView.setEmptyView(mailEmpty);
        new BackgroundTask().execute();
    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {

            //try {
                target = "http://ljh453.cafe24.com/podduk_mailread.php?receive_id=jim0307";//"+URLEncoder.encode(ID, "UTF-8");
                //receive_id 즉, 송신인의 ID(사용자 ID)에 저장된 메일을 읽어온다.
            //} catch (UnsupportedEncodingException e) {
             //   e.printStackTrace();
            //}

        }

        @Override
protected String doInBackground(Void... voids) {
    try {
        URL url = new URL(target);
        //위 target URL을 url에 저장.
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //해당 URL로 부터 데이터를 읽어옴.
        String temp;
        StringBuilder stringBuilder = new StringBuilder();
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp + "\n");
        }//서버의 데이터를 줄단위로 읽어서 문자열을 담음
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        //URL 연결 끊음
        return stringBuilder.toString().trim();
    } catch (Exception e) {
        e.printStackTrace();
        //URL 연결 되지 않았을 경우 실행
    }
    return null;
}

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            //json 객체에 대해, jsonArray를 서버로부터 response로 받은 배열로 초기화.
            String mailTitle;
            String mailContent;
            String senderID;
            String date;
            String senderName;
            int count = 0;//메일의 index
            while (count < jsonArray.length())//jsonArray의 개수(메일의 개수)만큼 반복실행
            {
                JSONObject object = jsonArray.getJSONObject(count);

                mailTitle = object.getString("mail_title");
                mailContent = object.getString("mail_content");
                date = object.getString("receive_date");
                senderID = object.getString("send_id");
                senderName = object.getString("send_name");
                //메일의 제목, 내용, 날짜, 발송인 ID 및 이름을 서버로 부터 받아온 값으로 저장
                Mail mail = new Mail(mailTitle, mailContent, senderID, date, senderName);
                //그 메일을 생성.
                mailList.add(mail);
                //메일 리스트에 해당 메일 생성
                adapter.notifyDataSetChanged();
                //메일 어댑터에 메일 데이터가 변경됐다는 것을 알려서, 메일 리스트 업데이트
                count++;
                //메일의 index 증가
            }
        } catch (Exception e) {
            e.printStackTrace();
            //서버 연결 안됐을 때 처리
        }
    }
}


}
