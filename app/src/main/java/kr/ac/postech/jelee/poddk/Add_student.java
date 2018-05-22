package kr.ac.postech.jelee.poddk;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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

    Button addsubjectButton;
    Button cancelButton;
    Button addButton;

    int numSubject = 0;
    private final int DYNAMIC_VIEW_ID = 0x8000;
    private LinearLayout dynamicLayout = (LinearLayout) findViewById(R.id.dynamicArea);

    List<String> majorsubjectdata;
    List<String> minorsubjectdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        this.setFinishOnTouchOutside(false);

        ArrayList<String> majorList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.majorList)));
        ArrayList<String> linguisticList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.linguisticList)));
        ArrayList<String> mathList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.mathList)));
        ArrayList<String> physicsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.physicsList)));
        ArrayList<String> chemistryList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.chemistryList)));
        ArrayList<String> lifescienceList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.lifescienceList)));
        ArrayList<String> mechanicList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.mechanicList)));
        ArrayList<String> imeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.imeList)));
        ArrayList<String> materialList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.materialList)));
        ArrayList<String> electricList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.electricList)));
        ArrayList<String> cseList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.cseList)));
        ArrayList<String> chemicalengineeringList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.chemicalengineeringList)));
        ArrayList<String> citeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.citeList)));
        ArrayList<String> etcList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.etcList)));


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

        majorSubjectSpinner.setAdapter(majorAdapter);
        majorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //선택된 아이템 팝업 Toast.makeText(Add_student.this, "선택된 아이템: "+majorSubjectSpinner.getItemAtPosition(i),Toast.LENGTH_SHORT).show();

                //세부과목 스피너 설정
                if(majorSubjectSpinner.getSelectedItemPosition() == 0){ //'선택사항없음'
                    Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(etcAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition()== 1){
                    Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mathAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(imeAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);

                    minorSubjectSpinner.setAdapter(materialAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(electricAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(cseAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(chemiengineeringAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(citeAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    Spinner minorSubjectSpinner = (Spinner) findViewById(R.id.minorSubject);
                    minorSubjectSpinner.setAdapter(etcAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addsubjectButton = (Button)findViewById(R.id.addsubjectbutton);
        addsubjectButton.setOnClickListener(this);

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
    }


    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addsubjectButton){
            addSubjectlist();
        }

        else if(view == cancelButton){
            finish();
        }
        else if(view == addButton){
            //데이터 전달하기
            finish();
        }
    }

    public void addSubjectlist(){
        numSubject++;

        Button dynamicButton = new Button(this);
        dynamicButton.setId(DYNAMIC_VIEW_ID + numSubject);
        dynamicButton.setText(numSubject);
        dynamicButton.setOnClickListener(this);

        dynamicLayout.addView(dynamicButton, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
     }

    /*1111
    // 매개변수를 웹으로 보내고 그 결과를 반환하는 함수
    // 참고: http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post
    private String send(HashMap<String, String> map, String addr) {
        String response = ""; // DB 서버의 응답을 담는 변수

        try {
            URL url = new URL(addr); //URL 주소 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 해당 URL에 연결

            conn.setConnectTimeout(10000); // 타임아웃: 10초
            conn.setUseCaches(false); // 캐시 사용 안 함
            conn.setRequestMethod("POST"); // POST로 연결
            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (map != null) { // 웹 서버로 보낼 매개변수가 있는 경우우
                OutputStream os = conn.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송
                bw.write(getPostString(map)); // 매개변수 전송
                bw.flush();
                bw.close();
                os.close();
            }

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // 연결에 성공한 경우
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 서버의 응답을 읽기 위한 입력 스트림

                while ((line = br.readLine()) != null) // 서버의 응답을 읽어옴
                    response += line;
            }

            conn.disconnect();
        } catch (MalformedURLException me) {
            me.printStackTrace();
            return me.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return response;
    }

    // 매개변수를 URL에 붙이는 함수
// 참고: http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post
    private String getPostString(HashMap<String, String> map) {
        StringBuilder result = new StringBuilder();
        boolean first = true; // 첫 번째 매개변수 여부

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (first)
                first = false;
            else // 첫 번째 매개변수가 아닌 경우엔 앞에 &를 붙임
                result.append("&");

            try { // UTF-8로 주소에 키와 값을 붙임
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException ue) {
                ue.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }
    */








    /*22222

    public void HttpPostData() {
        try {
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL("http://korea-com.org/foxmann/lesson01.php");       // URL 설정
            HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                         // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");         // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            buffer.append("majorsubjectdata").append("=").append(majorsubjectdata).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            buffer.append("minorsubjectdata").append("=").append(minorsubjectdata).append("&");           // 변수 구분은 '&' 사용
            buffer.append("subject").append("=").append(mySubject);

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
            }
            myResult = builder.toString();                       // 전송결과를 전역 변수에 저장
            ((TextView)(findViewById(R.id.text_result))).setText(myResult);
            Toast.makeText(MainActivity.this, "전송 후 결과 받음", 0).show();
        } catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            //
        } // try
    } // HttpPostData

    */
}
