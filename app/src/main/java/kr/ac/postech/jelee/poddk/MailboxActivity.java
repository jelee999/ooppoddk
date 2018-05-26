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

public class MailboxActivity extends AppCompatActivity
{

    protected ListView mailListView;
    protected MailListAdapter adapter;
    protected List<Mail> mailList;
    String ID ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        SharedPreferences saved = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        ID = saved.getString("inputID","0");

        mailListView = (ListView)findViewById(R.id.mailListView);
        mailList = new ArrayList<Mail>();
        /*
        mailList.add(new Mail("메일 제목1", "메일 내용","송신자ID",
                "송신자 이름", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail( "메일 제목2", "메일 내용","송신자ID",
                "송신자 이름", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail( "메일 제목3", "메일 내용","송신자ID",
                "송신자 이름", "2018-05-21", R.drawable.profile ));*/
        adapter = new MailListAdapter(getApplicationContext(), mailList);

        mailListView.setAdapter(adapter);

        new BackgroundTask().execute();
    }





    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute()
        {

            try {
                target = "http://ljh453.cafe24.com/podduk_mail.php?id="+ URLEncoder.encode(ID,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                String mailTitle;
                String mailContent;
                String senderID;
                String date;
                int ImageID;
                int count = 0;
                while(count<jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);
                    mailTitle = object.getString("mail_title");
                    mailContent = object.getString("mail_content");
                    date = object.getString("receive_date");
                    senderID = object.getString("send_id");
                    Mail mail = new Mail( mailTitle, mailContent, senderID, date);
                    mailList.add(mail);
                    adapter.notifyDataSetChanged();
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
