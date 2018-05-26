package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class View_teacher extends AppCompatActivity implements View.OnClickListener{

    Button contactButton;
    Button cancelButton;
    Person pteacher;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_info);

        Bundle bundle = getIntent().getExtras();
        pteacher = bundle.getParcelable("teacherInfo");

        TextView NameData = (TextView) findViewById(R.id.NameData);
        TextView AgeData = (TextView) findViewById(R.id.AgeData);
        TextView SexData = (TextView) findViewById(R.id.SexData);
        TextView MajorSubjectData = (TextView) findViewById(R.id.majorSubject);
        TextView MinorSubjectData = (TextView) findViewById(R.id.minorSubject);
        TextView learnContent = (TextView) findViewById(R.id.Data);
        TextView studentAbility = (TextView) findViewById(R.id.Ability);
        TextView availableTime = (TextView) findViewById(R.id.availableTime);
        TextView etcData = (TextView) findViewById(R.id.etcData);

        NameData.setText(pteacher.getName());
        AgeData.setText(String.valueOf(pteacher.getAge()));
        SexData.setText(pteacher.getSex());
        MajorSubjectData.setText(pteacher.getmajorSubject());
        MinorSubjectData.setText(pteacher.getminorSubject());
        learnContent.setText(pteacher.getContents());
        studentAbility.setText(pteacher.getAbility());
        availableTime.setText(pteacher.getavailableTime());
        etcData.setText(pteacher.getetcData());

        contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);


    }

    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == cancelButton){
            finish(); //화면 종료
        }
        else if(view == contactButton){
            //HttpPostData();
            //선생에게 연락하기

            Intent intent = new Intent(this, WriteMail.class);
            intent.putExtra("receiverID", "abcd"/*pteacher.getIDdata()*/);
            intent.putExtra("userID", /*userID*/"hsm9300");
            this.startActivity(intent);
        }
    }

}
