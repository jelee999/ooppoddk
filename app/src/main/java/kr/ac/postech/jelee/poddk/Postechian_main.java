package kr.ac.postech.jelee.poddk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.Subject;


public class Postechian_main extends Fragment implements StudentAdapter.OnItemClickListener, View.OnClickListener {
    static final int PICK_CONTACT_REQUEST = 1;
    static final int ADDORDER = 4;
    static final int DELETEORDER = 5;

    String savedID;
    String savedname;
    String savedgender;
    String savedyear;
    int savedage;

    String majortext=null;

    FloatingActionButton addStudentButton;
    TextView clicktosearch;
    LinearLayout hidesearchlayout;
    private View rootView;

    public Postechian_main() {
    }

    private RecyclerView mrecyclerView;
    private StudentAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private RequestQueue mRequestQueue;
    private ArrayList<Person> studentList; //학생 리스트
    private ArrayList<Person> currentstudentList;
    EditText namesearch;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.postechian_main, container, false);
        rootView = view;

        SharedPreferences auto = Postechian_main.this.getContext().getSharedPreferences("auto",Postechian_main.this.getContext().MODE_PRIVATE);
        savedID = auto.getString("inputID",null);
        savedgender = auto.getString("inputgender",null);
        savedname = auto.getString("inputname",null);
        savedyear = auto.getString("inputyear",null);
        savedage = 2019-Integer.valueOf(savedyear);

        if(savedgender=="female"){
            savedgender = "여";
        }
        else{
            savedgender = "남";
        }


        createStudentList();
        buildRecyclerView();

        //과목 스피너
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

        //주요과목 스피너
        final ArrayAdapter majorAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, majorList);
        final Spinner majorSubjectSpinner = (Spinner) view.findViewById(R.id.majorSubjectsearch);

        final Spinner minorSubjectSpinner = (Spinner) view.findViewById(R.id.minorSubjectsearch);
        final ArrayAdapter linguisticAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, linguisticList);
        final ArrayAdapter mathAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, mathList);
        final ArrayAdapter physicsAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, physicsList);
        final ArrayAdapter chemistryAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, chemistryList);
        final ArrayAdapter lifescienceAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, lifescienceList);
        final ArrayAdapter mechanicAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, mechanicList);
        final ArrayAdapter imeAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, imeList);
        final ArrayAdapter materialAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, materialList);
        final ArrayAdapter electricAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, electricList);
        final ArrayAdapter cseAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, cseList);
        final ArrayAdapter chemicalengineeringAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, chemicalengineeringList);
        final ArrayAdapter citeAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, citeList);
        final ArrayAdapter etcAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, etcList);

        majorSubjectSpinner.setAdapter(majorAdapter);
        majorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //세부과목 스피너 설정
                if (majorSubjectSpinner.getSelectedItemPosition() == 0) { //'선택사항없음'
                    minorSubjectSpinner.setAdapter(etcAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(null, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 1) {
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    minorSubjectSpinner.setAdapter(mathAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                   //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    minorSubjectSpinner.setAdapter(imeAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    minorSubjectSpinner.setAdapter(materialAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    minorSubjectSpinner.setAdapter(electricAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    minorSubjectSpinner.setAdapter(cseAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    minorSubjectSpinner.setAdapter(chemicalengineeringAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    minorSubjectSpinner.setAdapter(citeAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    minorSubjectSpinner.setAdapter(etcAdapter);
                    //majortext = majorSubjectSpinner.getSelectedItem().toString();
                    //mAdapter.filtermajorsubject(majortext, null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        /*
        minorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(minorSubjectSpinner.getSelectedItemPosition() == 0){
                    mAdapter.filtermajorsubject(majortext, null);
                }else {
                    String minortext = minorSubjectSpinner.getSelectedItem().toString();
                    mAdapter.filtermajorsubject(majortext, minortext);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        addStudentButton = (FloatingActionButton) view.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        clicktosearch = (TextView) view.findViewById(R.id.clickbutton);
        clicktosearch.setOnClickListener(this);

        hidesearchlayout = (LinearLayout) view.findViewById(R.id.hidesearchBar);
        hidesearchlayout.setVisibility(View.GONE);

        namesearch = (EditText) view.findViewById(R.id.searchName);
        namesearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = namesearch.getText().toString().toLowerCase(Locale.getDefault());
                mAdapter.filter(text);
            }
        });

        return view;
    }


    public void createStudentList() {
        studentList = new ArrayList<Person>();

    }

    public void buildRecyclerView() {

        mrecyclerView = rootView.findViewById(R.id.studentRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new StudentAdapter(studentList);

        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setAdapter(mAdapter);

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();

    }


    //JSON에서 정보 받아오기
    private void parseJSON() {
        String url = "http://ljh453.cafe24.com/podduk_showstudent_init.php"; //url 연결

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("response");

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject student = jsonArray.getJSONObject(i);

                                String ID = student.getString("id");
                                //Int imageID = student.getInt("imageID");
                                String subjectcode = student.getString("subject");
                                String content = student.getString("content");
                                String ability = student.getString("ability");
                                String time = student.getString("time");
                                String etc = student.getString("etc");
                                String Name = student.getString("name");
                                String tempsex = student.getString("gender");
                                int Age = 2019-Integer.valueOf(student.getString("birth_year"));
                                String Sex;
                                if(tempsex == "male"){
                                    Sex = "남";
                                }
                                else{
                                    Sex = "여";
                                }

                                String majorSubjectCode = subjectcode.substring(0,4); //ex)CSED
                                String majorsubject="";
                                String minorsubject="";
                                String tempminor="";


                                if(majorSubjectCode.equals("LING")) {
                                    majorsubject = "언어";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LING)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LINGCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("MATH")) {
                                    majorsubject = "수학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATH)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATHCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("PHYS")) {
                                    majorsubject = "물리";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYS)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYSCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("CHEM")) {
                                    majorsubject = "화학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEM)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEMCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("LIFE")) {
                                    majorsubject = "생명과학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFE)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFECode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("MECH")) {
                                    majorsubject = "기계공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECH)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECHCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("IMEN")) {
                                    majorsubject = "산업경영공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMEN)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMENCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("AMSE")) {
                                    majorsubject = "신소재공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSE)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSECode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("ELEC")) {
                                    majorsubject = "전자전기공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELEC)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELECCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("CSED")) {
                                    majorsubject = "컴퓨터공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSED)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSEDCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("CHEB")) {
                                    majorsubject = "화학공학";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEB)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEBCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("CITE")) {
                                    majorsubject = "창의IT";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITE)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITECode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }
                                else if(majorSubjectCode.equals("ETCS")) {
                                    majorsubject = "기타";
                                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCS)));
                                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCSCode)));
                                    for(int m=0;m<minorsubjectList.size();m++){
                                        tempminor = minorsubjectcodeList.get(m);
                                        if(subjectcode.equals(tempminor)){
                                            minorsubject = minorsubjectList.get(m);
                                        }
                                    }
                                }



                                //ID에서 데이터 받아오기!!!!!!!!

                                studentList.add(new Person(ID, R.drawable.profile, Name, Age, Sex, majorsubject, minorsubject, content, ability, time, etc));
                            }
                            mAdapter = new StudentAdapter(Postechian_main.this.getContext(), studentList);
                            mrecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickLIstener(Postechian_main.this);

                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


    //학생 등록
    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Postechian_main.this.getContext(),
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            //Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String id = savedID;
            String subject = (String)params[1];
            String content = (String)params[2];
            String ability = (String)params[3];
            String time = (String)params[4];
            String etc = (String)params[5];

            String serverURL = "http://ljh453.cafe24.com/podduk_registerstudent.php";
            String postParameters = "id=" + id + "&subject=" + subject+ "&content=" + content+ "&ability=" + ability+ "&time=" + time+ "&etc=" + etc;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                //Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


    //학생 삭제
    class DeleteData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Postechian_main.this.getContext(),
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            //Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[0];
            String subject = (String)params[1];

            String postParameters = "id=" + id + "&subject=" + subject;

            try {

                URL url = new URL("http://ljh453.cafe24.com/podduk_deletestudent.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                //Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 어떤 request확인
        if(requestCode == ADDORDER) {
            if(resultCode == -1){

                Bundle b = data.getExtras();
                String id = b.getString("ID");
                String majorsubject = b.getString("majorsubject");
                String subject = b.getString("minorsubject");

                String content = b.getString("contents");
                String ability = b.getString("ability");
                String time = b.getString("time");
                String etc = b.getString("etc");

                String tempminor;

                if(majorsubject.equals("언어")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LING)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LINGCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("수학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATH)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATHCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("물리")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYS)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYSCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("화학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEM)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEMCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("생명과학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("기계공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECH)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECHCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("산업경영공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMEN)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMENCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("신소재공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("전자전기공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELEC)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELECCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("컴퓨터공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSED)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSEDCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("화학공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEB)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEBCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("창의IT")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("기타")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCS)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCSCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }

                InsertData task = new InsertData();
                task.execute(id, subject, content, ability, time, etc);
                //페이지 새로고침
                refresh();
            }
        }

        else if(requestCode == DELETEORDER) { //delete 명령과 함께 액티비티가 끝나면...
            if(resultCode == -1) { //성공
                Bundle b2 = data.getExtras();
                String id = b2.getString("ID");
                String majorsubject = b2.getString("majorsubject");
                String subject = b2.getString("minorsubject");

                String tempminor;

                if(majorsubject.equals("언어")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LING)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LINGCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("수학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATH)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MATHCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("물리")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYS)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.PHYSCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("화학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEM)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEMCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("생명과학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.LIFECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("기계공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECH)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.MECHCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("산업경영공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMEN)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.IMENCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("신소재공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.AMSECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("전자전기공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELEC)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ELECCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("컴퓨터공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSED)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CSEDCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("화학공학")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEB)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CHEBCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("창의IT")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITE)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.CITECode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                else if(majorsubject.equals("기타")) {
                    ArrayList<String> minorsubjectList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCS)));
                    ArrayList<String> minorsubjectcodeList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCSCode)));
                    for(int m=0;m<minorsubjectList.size();m++){
                        tempminor = minorsubjectList.get(m);
                        if(subject.equals(tempminor)){
                            subject = minorsubjectcodeList.get(m);
                        }
                    }
                }
                DeleteData task = new DeleteData();
                task.execute(id, subject);

                //fragment 새로고침
                refresh();
            }
        }

    }

    private void refresh(){
        Postechian_main rSum = new Postechian_main();

    }


        //버튼 클릭했을 때
        public void onClick(View view) {
            if (view == addStudentButton) {
                Intent intent = new Intent(getActivity(), Add_student.class);
                intent.putExtra("ID", savedID);
                intent.putExtra("Name", savedname); //ID에서 이름 가져오기
                intent.putExtra("Age", savedage); //ID에서 나이 가져오기
                intent.putExtra("Sex", savedgender); //ID에서 성별 가져오기

                startActivityForResult(intent, ADDORDER);
            } else if (view == clicktosearch) {
                if (hidesearchlayout.getVisibility() == View.GONE) {
                    hidesearchlayout.setVisibility(View.VISIBLE);
                    clicktosearch.setText("Click to Hide ▲");
                } else {
                    hidesearchlayout.setVisibility(View.GONE);
                    clicktosearch.setText("Click to Search ▼");
                }
            }
        }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), View_student.class);
        Person clickedItem = studentList.get(position);

        detailIntent.putExtra("studentInfo", studentList.get(position));
        startActivityForResult(detailIntent, DELETEORDER);
    }
}



