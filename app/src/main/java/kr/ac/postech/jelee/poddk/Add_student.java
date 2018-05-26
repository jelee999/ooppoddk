package kr.ac.postech.jelee.poddk;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add_student extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "poddk_Add_student";

    Button cancelButton;
    Button addButton;

    //아이디에서 받아올 데이터
    String studentID;
    String studentName;
    int studentAge;
    String studentSex;

    //사용자로부터 입력받을 데이터
    String majorSubject;
    String minorSubject;
    String learnContents;
    String studentAbility;
    String availableTime;
    String etcData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        this.setFinishOnTouchOutside(false);

        //Intent pintent = getIntent();

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getString("ID");
        studentName = bundle.getString("Name");
        studentAge = bundle.getInt("Age");
        studentSex = bundle.getString("Sex");

        TextView studentNameview = (TextView)findViewById(R.id.NameData);
        TextView studentAgeview = (TextView)findViewById(R.id.AgeData);
        TextView studentSexview = (TextView)findViewById(R.id.SexData);
        studentNameview.setText(studentName);
        studentAgeview.setText(String.valueOf(studentAge));
        studentSexview.setText(studentSex);


        ArrayList<String> majorList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.majorSubjectList)));
        ArrayList<String> linguisticList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LING)));
        ArrayList<String> mathList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATH)));
        ArrayList<String> physicsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYS)));
        ArrayList<String> chemistryList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEM)));
        ArrayList<String> lifescienceList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFE)));
        ArrayList<String> mechanicList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECH)));
        ArrayList<String> imeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMEN)));
        ArrayList<String> materialList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSE)));
        ArrayList<String> electricList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELEC)));
        ArrayList<String> cseList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSED)));
        ArrayList<String> chemicalengineeringList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEB)));
        ArrayList<String> citeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITE)));
        ArrayList<String> etcList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCS)));
        ArrayList<String> nullList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.NULL)));


        //주요과목 스피너
        final ArrayAdapter majorAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, majorList);
        final Spinner majorSubjectSpinner = (Spinner)findViewById(R.id.majorSubject);

        //Spinner minorSubjectSpinner;

        final ArrayAdapter linguisticAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, linguisticList);
        final ArrayAdapter mathAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mathList);
        final ArrayAdapter physicsAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, physicsList);
        final ArrayAdapter chemistryAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, chemistryList);
        final ArrayAdapter lifescienceAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lifescienceList);
        final ArrayAdapter mechanicAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mechanicList);
        final ArrayAdapter imeAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, imeList);
        final ArrayAdapter materialAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, materialList);
        final ArrayAdapter electricAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, electricList);
        final ArrayAdapter cseAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cseList);
        final ArrayAdapter chemiengineeringAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, chemicalengineeringList);
        final ArrayAdapter citeAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, citeList);
        final ArrayAdapter etcAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, etcList);
        final ArrayAdapter nullAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, nullList);

        majorSubjectSpinner.setAdapter(majorAdapter);
        majorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //세부과목 스피너 설정
                if(majorSubjectSpinner.getSelectedItemPosition() == 0){ //'선택사항없음'
                    Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(nullAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition()== 1){
                    Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mathAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(imeAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(materialAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(electricAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(cseAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemiengineeringAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(citeAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(etcAdapter);
                    minorSubject = minorSubjectSpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        //학생정보 값 받아오기
        //과목정보 받아오기
        majorSubject = majorSubjectSpinner.getSelectedItem().toString();

        EditText learndatatext = (EditText)findViewById(R.id.learnData);
        EditText studentabilitytext = (EditText)findViewById(R.id.studentAbility);
        EditText availabletimetext = (EditText)findViewById(R.id.availableTime);
        EditText etcDatatext = (EditText)findViewById(R.id.etcData);

        learnContents = learndatatext.getText().toString();
        studentAbility = studentabilitytext.getText().toString();
        availableTime = availabletimetext.getText().toString();
        etcData = etcDatatext.getText().toString();

    }


    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == cancelButton){
            finish(); //화면 종료
        }
        if(view == addButton){
            //학생 만들어서 넘겨주기
            Person pstudent = new Person(studentID, R.drawable.profile, studentName, studentAge, studentSex, majorSubject, minorSubject,
                    learnContents, studentAbility, availableTime, etcData);
            Intent intent = new Intent();
            intent.putExtra("studenttoAdd", pstudent);
            finish();
        }
    }

    //HTTP Post로 주고 받기
    public void HttpPostData() {
        try {
            //URL 설정하고 접속하기
            URL url = new URL("127.0.0.1"); //괄호 안에 URL 입력하기
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            //전송모드 설정(기본설정)
            http.setDefaultUseCaches(false);
            http.setDoInput(true); // 서버에서 읽기 모드 지정
            http.setDoOutput(true); //서버에서 쓰기 모드 지정
            http.setRequestMethod("POST"); // 전송 방식은 POST

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded"); // 서버에세 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 것을 알려줌

            //서버로 값 전송
            StringBuffer buffer = new StringBuffer();
            buffer.append("Name").append("=").append(studentName).append("&"); //php 번수에 값 대입
            buffer.append("Age").append("=").append(studentAge).append("&"); //php 번수에 값 대입
            buffer.append("Sex").append("=").append(studentSex).append("&"); //php 번수에 값 대입
            buffer.append("majorSubject").append("=").append(majorSubject).append("&"); //php 번수에 값 대입
            buffer.append("minorSubject").append("=").append(minorSubject).append("&"); //php 번수에 값 대입
            buffer.append("learnContents").append("=").append(learnContents).append("&"); //php 번수에 값 대입
            buffer.append("studentAbility").append("=").append(studentAbility).append("&"); //php 번수에 값 대입
            buffer.append("availableTime").append("=").append(availableTime).append("&"); //php 번수에 값 대입
            buffer.append("etcData").append("=").append(etcData); //php 번수에 값 대입

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();


            //서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) { //서버에서 라인 단위로 보내줄 것이므로 라인단위로 읽는다.

                builder.append(str+"\n"); //Viewㅔ 표시하기 위해 라인 구분자 추가
            }

            //myResult = builder.toString();
            //((TextView)(findViewById(R.id.text_result))).setText(myResult);
            //Toast.makeText(Add_student.this, "전송 후 결과 받음", 0).show();

        }catch(MalformedURLException e){
            //
        }catch(IOException e){
            //
        }//try
    }//HttpPostData
} //Activity
