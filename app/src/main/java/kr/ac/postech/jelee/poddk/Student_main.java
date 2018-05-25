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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Student_main extends Fragment implements View.OnClickListener {

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.student_main, container, false);
        rootView = view;


        createTeacherList();
        buildRecyclerView();

        //과목 스피너
        ArrayList<String> majorList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.majorSubjectList)));
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
        final ArrayList<String> etcList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.etcList)));


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
        teacherList.add(position, new Person("jelee3", R.drawable.profile, " 김이름", 23, "여", "수학", "선형대수학",
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
        teacherList.add(new Person("jelee1", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee2", R.drawable.profile, "김과학", 25, "여", "물리", "양자물리",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "객체지향",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "양자물리",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Person("jelee2", R.drawable.profile, "김과학", 25, "여", "과학", "전자기학",
                "blank", "blank", "blank", "blank"));
    }

    public void buildRecyclerView() {

        mrecyclerView = rootView.findViewById(R.id.teacherRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new TeacherAdapter(teacherList);

        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLIstener(new TeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), View_teacher.class);
                intent.putExtra("teacherInfo", teacherList.get(position));
                startActivity(intent);

            }
        });
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

}



