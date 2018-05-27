package kr.ac.postech.jelee.poddk;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.Subject;


public class Postechian_main extends Fragment implements StudentAdapter.OnItemClickListener, View.OnClickListener {
    static final int PICK_CONTACT_REQUEST = 1;
    static final int ADDORDER = 4;
    static final int DELETEORDER = 5;
    static final int EDITORDER = 6;

    //SharedPreferences saved = getSharedPreferences("auto", Activity.MODE_PRIVATE);
    //String ID = saved.getString("inputID","0"); //사용자 ID 가져오기
    String ID = "jelee"; //임시로 아이디 만들었음


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
    private ArrayList<Person> currentstudentList = new ArrayList<Person>();
    SearchView searchview;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.postechian_main, container, false);
        rootView = view;

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
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 1) {
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    minorSubjectSpinner.setAdapter(mathAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    minorSubjectSpinner.setAdapter(imeAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    minorSubjectSpinner.setAdapter(materialAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    minorSubjectSpinner.setAdapter(electricAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    minorSubjectSpinner.setAdapter(cseAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    minorSubjectSpinner.setAdapter(chemicalengineeringAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    minorSubjectSpinner.setAdapter(citeAdapter);
                } else if (majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    minorSubjectSpinner.setAdapter(etcAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        addStudentButton = (FloatingActionButton) view.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        clicktosearch = (TextView) view.findViewById(R.id.clickbutton);
        clicktosearch.setOnClickListener(this);

        hidesearchlayout = (LinearLayout) view.findViewById(R.id.hidesearchBar);
        hidesearchlayout.setVisibility(View.GONE);

        searchview = (SearchView)view.findViewById(R.id.searchName);



        return view;
    }


    public void insertItem(int position) {
        studentList.add(position, new Person("jelee", R.drawable.profile, " 김이름", 23, "여", "수학", "선형대수학",
                "blank", "blank", "blank", "blank"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        studentList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }


    //IDdata가 list안에 있으면 그 index를 반환함 & 없으면 -1 반환
    public int checkPosition(String identification){
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).getIdentification() == identification) {
                return i;
            }
        }
        return -1;
    }

    public void createStudentList() {
        studentList = new ArrayList<Person>();
        studentList.add(new Person("jelee", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Person("jelee", R.drawable.profile, "김과학", 25, "여", "물리", "양자물리",
                "blank", "blank", "blank", "blank"));
    }

    public void buildRecyclerView() {

        mrecyclerView = rootView.findViewById(R.id.studentRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new StudentAdapter(studentList);

        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setAdapter(mAdapter);

       // currentstudentList = getStudents();

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();
        //new BackgroundTask().execute();

        /*
        mAdapter.setOnItemClickLIstener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), View_student.class);
                intent.putExtra("studentInfo", studentList.get(position));
                startActivity(intent);
                //startActivityForResult(intent, PICK_CONTACT_REQUEST);

            }
        });*/
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
                                int Age = 16;

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





/*
    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://ljh453.cafe24.com/podduk_showstudent_init.php"; // 연결할 url
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject student = jsonArray.getJSONObject(count);
                    String ID = student.getString("id");
                    //Int imageID = student.getInt("imageID");
                    String subjectcode = student.getString("subject");
                    String content = student.getString("content");
                    String ability = student.getString("ability");
                    String time = student.getString("time");
                    String etc = student.getString("etc");
                    String majorsubject = "";
                    String minorsubject = "";

                    String majorSubjectCode = subjectcode.substring(0, 3); //ex)CSED
                    String SubjectList = majorSubjectCode + "Code"; //ex)CSEDCode
                    List<String> majorsubjectList = Arrays.asList(getResources().getStringArray(R.array.majorSubjectList));
                    List<String> majorsubjectcodeList = Arrays.asList(getResources().getStringArray(R.array.majorSubjectListCode));


                    int arryid = getResources().getIdentifier(majorSubjectCode, "array", getActivity().getPackageName());
                    int arrycodeid = getResources().getIdentifier(SubjectList, "array", getActivity().getPackageName());
                    List<String> minorsubjectList = Arrays.asList(getResources().getStringArray(arryid)); //minorsubjectlist 불러오기
                    List<String> minorsubjectcodeList = Arrays.asList(getResources().getStringArray(arrycodeid)); //minorsubjectcodelist 불러오기


                    for (int n = 0; n < majorsubjectcodeList.size(); n++) {
                        if (majorSubjectCode == majorsubjectcodeList.get(n)) {
                            majorsubject = majorsubjectList.get(n);
                            for (int m = 0; m < minorsubjectList.size(); m++) {
                                if (subjectcode == minorsubjectcodeList.get(m)) {
                                    minorsubject = minorsubjectList.get(m);
                                }
                            }
                        }
                    }


                        //ID에서 데이터 받아오기!!!!!!!!
                        String Name = "김포항";
                        int Age = 16;
                        String Sex = "여";

                        studentList.add(new Person(ID, R.drawable.profile, Name, Age, Sex, majorsubject, minorsubject, content, ability, time, etc));

                    mAdapter.notifyDataSetChanged();
                    count++;
                }


            }
            mAdapter = new StudentAdapter(Postechian_main.this.getContext(), studentList);
            mrecyclerView.setAdapter(mAdapter);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/


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

            //String id = (String)params[0];
            String id = "jim0307";
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







/*
    public String performPostCall(String requestURL, HashMap<String, String> postDataParams){
        URL url;
        String response = "";
        try{
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if(responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {

                    response+=line;
                }
            }
            else {
                response ="";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(first)
                first=false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

        }
        return result.toString();
    }

*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 어떤 request확인
        if(requestCode == ADDORDER) {
            if(resultCode == -1){
                /*
                Bundle b = data.getExtras();
                Person pstudent;
                pstudent = b.getParcelable("studenttoAdd");

                //String id = pstudent.getIDdata();
                String id = "jim0307";
                String subject = pstudent.getminorSubject();
                String content = pstudent.getContents();
                String ability = pstudent.getAbility();
                String time = pstudent.getavailableTime();
                String etc = pstudent.getetcData();*/



                Bundle b = data.getExtras();
                String id = b.getString("ID");
                String majorsubject = b.getString("majorsubject");
                String subject = b.getString("minorsubject");

                String content = b.getString("contents");
                String ability = b.getString("ability");
                String time = b.getString("time");
                String etc = b.getString("etc");

                /*
                String tempdata = id+" + "+subject+" + "+content+" + "+ability+" + "+time+" + "+etc;
                Toast toast = Toast.makeText(Postechian_main.this.getContext(), tempdata, Toast.LENGTH_LONG);
                toast.show();*/

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
            }
        }

        else if(requestCode == DELETEORDER) {
            Bundle b = data.getExtras();
            if(b!=null) {
                Person pstudent = b.getParcelable("studenttoDelete");
                //DeleteStudent(pstudent);
            }
        }

    }


        //버튼 클릭했을 때
        public void onClick(View view) {
            if (view == addStudentButton) {
                Intent intent = new Intent(getActivity(), Add_student.class);
                intent.putExtra("ID", "jim0307");
                intent.putExtra("Name", "김포항"); //ID에서 이름 가져오기
                intent.putExtra("Age", 23); //ID에서 나이 가져오기
                intent.putExtra("Sex", "남"); //ID에서 성별 가져오기
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
        startActivity(detailIntent);
    }
}



