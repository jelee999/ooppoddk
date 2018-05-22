package kr.ac.postech.jelee.poddk;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class MailboxActivity extends AppCompatActivity{

    private ListView mailListView;
    private MailListAdapter adapter;
    private List<Mail> mailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        mailListView = (ListView)findViewById(R.id.mailListView);
        mailList = new ArrayList<Mail>();
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        mailList.add(new Mail("메일 제목", "메일 내용", "2018-05-21", R.drawable.profile ));
        adapter = new MailListAdapter(getApplicationContext(), mailList);
        mailListView.setAdapter(adapter);

    }


}
