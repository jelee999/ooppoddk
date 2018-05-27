package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MailMoreActivity extends MailboxActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_more);

        TextView mailTitle = (TextView)findViewById(R.id.MailTitle_more);
        TextView mailDate = (TextView)findViewById(R.id.MailDate_more);
        TextView mailContent = (TextView)findViewById(R.id.MailContent_more);
        TextView mailSenderID = (TextView)findViewById(R.id.MailSenderID_more);
        TextView mailSenderName = (TextView)findViewById(R.id.MailSenderName_more);
        //activity_mail_more.xml의 TextView로 mailTitle,mailDate,mailContent,mailSenderID,mailSenderName을 초기화한다.
        //MailListAdapter 액티비티로부터 받은 문자열을 activity_mail_more.xml의 텍스트뷰로 보여주는 목적으로 선언했다.


        Intent intent = getIntent();
        //이전 액티비티에서 받은 데이터를 intent로 넘겨받는다.
        String strMailTitle;
        String strMailDate;
        String strMailContent;
        String strMailSenderID;
        String strMailSenderName;
        //메일에 대한 정보를 저장할 문자열 선언.

        strMailTitle =  intent.getExtras().getString("MailTitle");
        strMailDate =  intent.getExtras().getString("MailDate");
        strMailContent =  intent.getExtras().getString("MailContent");
        strMailSenderID =  intent.getExtras().getString("MailSenderID");
        strMailSenderName = intent.getExtras().getString("MailSenderName");
        //MailListAdapter 클래스라는 클래스에서 전달받은 데이터를
        //strMailTitle, strMailDate, strMailContent, strMailSenderID, strMailSenderName 에 저장한다.

        mailTitle.setText(strMailTitle);
        mailDate.setText(strMailDate);
        mailContent.setText(strMailContent);
        mailSenderID.setText("("+strMailSenderID+")");
        mailSenderName.setText(strMailSenderName);
        //각각의 문자열로 텍스트뷰 내용을 변경함.
    }
}
