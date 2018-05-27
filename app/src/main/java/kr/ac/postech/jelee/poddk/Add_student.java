package kr.ac.postech.jelee.poddk;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.CheckBox;
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

public class Add_student extends AppCompatActivity implements View.OnClickListener {
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

        Bundle bundle = getIntent().getExtras();
        studentID = bundle.getString("ID");
        studentName = bundle.getString("Name");
        studentAge = bundle.getInt("Age");
        studentSex = bundle.getString("Sex");

        TextView studentNameview = (TextView) findViewById(R.id.NameData);
        TextView studentAgeview = (TextView) findViewById(R.id.AgeData);
        TextView studentSexview = (TextView) findViewById(R.id.SexData);

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
        final Spinner majorSubjectSpinner = (Spinner) findViewById(R.id.majorSubject);

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

        final Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);


        majorSubjectSpinner.setAdapter(majorAdapter);
        majorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //세부과목 스피너 설정
                if (majorSubjectSpinner.getSelectedItemPosition() == 0) { //'선택사항없음'
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(nullAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 1) {
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mathAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(imeAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(materialAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(electricAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(cseAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemiengineeringAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(citeAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    majorSubject = majorSubjectSpinner.getSelectedItem().toString();
                    //Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(etcAdapter);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        minorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                minorSubject = minorSubjectSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        EditText learndatatext = (EditText) findViewById(R.id.learnData);
        EditText studentabilitytext = (EditText) findViewById(R.id.studentAbility);
        EditText availabletimetext = (EditText) findViewById(R.id.availableTime);
        EditText etcDatatext = (EditText) findViewById(R.id.etcData);

        learnContents = learndatatext.getText().toString();
        studentAbility = studentabilitytext.getText().toString();
        availableTime = availabletimetext.getText().toString();
        etcData = etcDatatext.getText().toString();

    }


    // 버튼 클릭했을 때
    public void onClick(View view) {
        if (view == cancelButton) {
            setResult(RESULT_CANCELED);
            finish(); //화면 종료
        } else if (view == addButton) {
            if(majorSubject.equals("선택해주세요") || minorSubject.equals("선택사항없음"))
            {
                Toast.makeText(this, "과목을 선택해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                EditText learndatatext = (EditText) findViewById(R.id.learnData);
                EditText studentabilitytext = (EditText) findViewById(R.id.studentAbility);
                EditText availabletimetext = (EditText) findViewById(R.id.availableTime);
                EditText etcDatatext = (EditText) findViewById(R.id.etcData);

                learnContents = learndatatext.getText().toString();
                studentAbility = studentabilitytext.getText().toString();
                availableTime = availabletimetext.getText().toString();
                etcData = etcDatatext.getText().toString();

                Intent pintent = new Intent();
                pintent.putExtra("ID", studentID);
                pintent.putExtra("majorsubject", majorSubject);
                pintent.putExtra("minorsubject", minorSubject);
                pintent.putExtra("contents", learnContents);
                pintent.putExtra("ability", studentAbility);
                pintent.putExtra("time", availableTime);
                pintent.putExtra("etc", etcData);

                setResult(RESULT_OK, pintent);
                finish();
            }
        }
    }

}