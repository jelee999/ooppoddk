package kr.ac.postech.jelee.poddk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class View_teacher extends AppCompatActivity implements View.OnClickListener {

    Button contactButton;
    Button cancelButton;
    String userID;
    Button deleteButton;
    Person pteacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_info);

        Bundle bundle = getIntent().getExtras();
        pteacher = bundle.getParcelable("teacherInfo");

        TextView NameData = (TextView) findViewById(R.id.teacherNameData);
        TextView AgeData = (TextView) findViewById(R.id.teacherAgeData);
        TextView SexData = (TextView) findViewById(R.id.teacherSexData);
        TextView MajorSubjectData = (TextView) findViewById(R.id.majorSubject);
        TextView MinorSubjectData = (TextView) findViewById(R.id.minorSubject);
        TextView teachContent = (TextView) findViewById(R.id.teachData);
        TextView teacherAbility = (TextView) findViewById(R.id.teacherCapability);
        TextView availableTime = (TextView) findViewById(R.id.availableTime);
        TextView etcData = (TextView) findViewById(R.id.etcData);

        NameData.setText(pteacher.getName());
        AgeData.setText(String.valueOf(pteacher.getAge()));
        SexData.setText(pteacher.getSex());
        MajorSubjectData.setText(pteacher.getmajorSubject());
        MinorSubjectData.setText(pteacher.getminorSubject());
        teachContent.setText(pteacher.getContents());
        teacherAbility.setText(pteacher.getAbility());
        availableTime.setText(pteacher.getavailableTime());
        etcData.setText(pteacher.getetcData());

        contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        SharedPreferences auto = this.getSharedPreferences("auto",this.MODE_PRIVATE);
        String savedID = auto.getString("inputID",null);


        //사용자가 등록한 정보이면 삭제 버튼을 누를 수 있도록 함.
        if(pteacher.getIDdata().equals(savedID)){
            deleteButton.setVisibility(View.VISIBLE);
        } else{
            deleteButton.setVisibility(View.GONE);
        }



    }

    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == cancelButton){
            setResult(RESULT_CANCELED);
            finish(); //화면 종료
        }
        else if(view == contactButton) {
            //선생님에게 연락하기
            Intent intent = new Intent(this, WriteMail.class);
            intent.putExtra("receiverID", pteacher.getIDdata());
            intent.putExtra("userID", userID);
            this.startActivity(intent);
        }
        else if(view == deleteButton){
            //Student_main에 삭제할 학생 정보 전달
            Intent pintent = new Intent();
            pintent.putExtra("ID", pteacher.getIDdata());
            pintent.putExtra("majorsubject", pteacher.getmajorSubject());
            pintent.putExtra("minorsubject", pteacher.getminorSubject());
            setResult(RESULT_OK, pintent);

            finish();
        }
    }



}
