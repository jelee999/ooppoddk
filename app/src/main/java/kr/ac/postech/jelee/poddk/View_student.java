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

public class View_student extends AppCompatActivity implements View.OnClickListener {

    Button contactButton;
    Button cancelButton;
    String userID;
    Button deleteButton;
    Person pstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);

        Bundle bundle = getIntent().getExtras();
        pstudent = bundle.getParcelable("studentInfo");

        TextView NameData = (TextView) findViewById(R.id.studentNameData);
        TextView AgeData = (TextView) findViewById(R.id.studentAgeData);
        TextView SexData = (TextView) findViewById(R.id.studentSexData);
        TextView MajorSubjectData = (TextView) findViewById(R.id.majorSubject);
        TextView MinorSubjectData = (TextView) findViewById(R.id.minorSubject);
        TextView learnContent = (TextView) findViewById(R.id.learnData);
        TextView studentAbility = (TextView) findViewById(R.id.studentCapability);
        TextView availableTime = (TextView) findViewById(R.id.availableTime);
        TextView etcData = (TextView) findViewById(R.id.etcData);

        NameData.setText(pstudent.getName());
        AgeData.setText(String.valueOf(pstudent.getAge()));
        SexData.setText(pstudent.getSex());
        MajorSubjectData.setText(pstudent.getmajorSubject());
        MinorSubjectData.setText(pstudent.getminorSubject());
        learnContent.setText(pstudent.getContents());
        studentAbility.setText(pstudent.getAbility());
        availableTime.setText(pstudent.getavailableTime());
        etcData.setText(pstudent.getetcData());

        contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(this);

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        SharedPreferences auto = this.getSharedPreferences("auto",this.MODE_PRIVATE);
        String savedID = auto.getString("inputID",null);


        //사용자가 등록한 정보이면 삭제 버튼을 누를 수 있도록 함.
        if(pstudent.getIDdata().equals(savedID)){
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
            //학생에게 연락하기
            Intent intent = new Intent(this, WriteMail.class);
            intent.putExtra("receiverID", pstudent.getIDdata());
            intent.putExtra("userID", userID);
            this.startActivity(intent);
        }
        else if(view == deleteButton){
            //Postechian_main에 삭제할 학생 정보 전달
            Intent pintent = new Intent();
            pintent.putExtra("ID", pstudent.getIDdata());
            pintent.putExtra("majorsubject", pstudent.getmajorSubject());
            pintent.putExtra("minorsubject", pstudent.getminorSubject());
            setResult(RESULT_OK, pintent);

            finish();
        }
    }



}
