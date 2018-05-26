package kr.ac.postech.jelee.poddk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Student_main extends Fragment implements View.OnClickListener, TeacherAdapter.OnItemClickListener {

    FloatingActionButton addTeacherButton;
    TextView clicktosearch;
    LinearLayout hidesearchlayout;
    private View rootView;

    public Student_main() {

    }

    private RecyclerView mrecyclerView;
    private TeacherAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private ArrayList<Person> teacherList;
    private RequestQueue mRequestQueue;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.student_main, container, false);
        rootView = view;


        createTeacherList();
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
        final ArrayList<String> etcList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ETCS)));


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


        addTeacherButton = (FloatingActionButton) view.findViewById(R.id.addTeacher);
        addTeacherButton.setOnClickListener(this);

        clicktosearch = (TextView) view.findViewById(R.id.clickbutton);
        clicktosearch.setOnClickListener(this);

        hidesearchlayout = (LinearLayout) view.findViewById(R.id.hidesearchBar);
        hidesearchlayout.setVisibility(View.GONE);


        return view;
    }

    public void insertItem(int position) {
        teacherList.add(position, new Person("jelee", R.drawable.profile, " 김이름", 23, "여", "수학", "선형대수학",
                "blank", "blank", "blank", "blank"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        teacherList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    //IDdata가 list안에 있으면 그 index를 반환함 & 없으면 -1 반환
    public int checkPosition(String teacherIDData){
        for(int i=0;i<teacherList.size();i++){
            if(teacherList.get(i).getIDdata() == teacherIDData) {
                return i;
            }
        }
        return -1;
    }

    public void createTeacherList() {
        teacherList = new ArrayList<Person>();
        teacherList.add(new Person("jelee", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee", R.drawable.profile, "김과학", 25, "여", "물리", "양자물리",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee", R.drawable.profile, "김과학", 25, "여", "컴공", "객체지향",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee", R.drawable.profile, "김과학", 25, "여", "컴공", "양자물리",
                "blank", "blank", "blank", "blank"));
    }

    public void buildRecyclerView() {

        mrecyclerView = rootView.findViewById(R.id.teacherRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new TeacherAdapter(teacherList);

        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setAdapter(mAdapter);

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();

        /*

        mAdapter.setOnItemClickLIstener(new TeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), View_teacher.class);
                intent.putExtra("teacherInfo", teacherList.get(position));
                startActivity(intent);

            }
        });*/
    }




    //JSON에서 정보 받아오기
    private void parseJSON() {
        String url = "http://ljh453.cafe24.com/podduk_showteacher_init.php"; //url 연결

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
                                int Age = 24;

                                teacherList.add(new Person(ID, R.drawable.profile, Name, Age, Sex, majorsubject, minorsubject, content, ability, time, etc));
                            }
                            mAdapter = new TeacherAdapter(Student_main.this.getContext(), teacherList);
                            mrecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickLIstener(Student_main.this);

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


    //+ 버튼 클릭했을 때
    public void onClick(View view) {
        if (view == addTeacherButton) {
            Intent intent = new Intent(getActivity(), Add_teacher.class);
            startActivity(intent);
        } else if (view == clicktosearch) {
            if (hidesearchlayout.getVisibility() == View.GONE) {
                hidesearchlayout.setVisibility(View.VISIBLE); //searchbar 보이게 함

            } else {
                hidesearchlayout.setVisibility(View.GONE); //searchbar 사라지게 함

            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), View_teacher.class);
        Person clickedItem = teacherList.get(position);

        detailIntent.putExtra("teacherInfo", teacherList.get(position));
        startActivity(detailIntent);
    }
}



