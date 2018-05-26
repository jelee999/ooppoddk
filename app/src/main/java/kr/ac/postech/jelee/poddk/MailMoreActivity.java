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

        Intent intent = getIntent();

        String strMailTitle;
        String strMailDate;
        String strMailContent;
        String strMailSenderID;

        strMailTitle =  intent.getExtras().getString("MailTitle");
        strMailDate =  intent.getExtras().getString("MailDate");
        strMailContent =  intent.getExtras().getString("MailContent");
        strMailSenderID =  intent.getExtras().getString("MailSenderID");
        //MailListAdapter 클래스라는 클래스에서 전달받은 데이터를
        //strMailTitle, strMailDate, strMailContent에 저장

        mailTitle.setText(strMailTitle);
        mailDate.setText(strMailDate);
        mailContent.setText(strMailContent);
        mailSenderID.setText(strMailSenderID);

    }
}
