package kr.ac.postech.jelee.poddk;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Add_teacher extends AppCompatActivity  implements View.OnClickListener{

    Button cancelButton;
    Button addButton;

    String majorsubjectdata;
    String minorsubjectdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_teacher);
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

        //세부과목 스피너
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
        final Spinner minorSubjectSpinner = (Spinner)findViewById(R.id.minorSubject);

        majorSubjectSpinner.setAdapter(majorAdapter);
        majorSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //선택된 아이템 팝업 Toast.makeText(Add_student.this, "선택된 아이템: "+majorSubjectSpinner.getItemAtPosition(i),Toast.LENGTH_SHORT).show();

                //세부과목 스피너 설정
                if(majorSubjectSpinner.getSelectedItemPosition() == 0){ //'선택사항없음'
                    minorSubjectSpinner.setAdapter(etcAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition()== 1){
                    minorSubjectSpinner.setAdapter(linguisticAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 2) { //'수학'
                    minorSubjectSpinner.setAdapter(mathAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 3) { //물리
                    minorSubjectSpinner.setAdapter(physicsAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 4) { //화학
                    minorSubjectSpinner.setAdapter(chemistryAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 5) { //생명
                    minorSubjectSpinner.setAdapter(lifescienceAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 6) { //기계공학
                    minorSubjectSpinner.setAdapter(mechanicAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 7) { //산업경영공학
                    minorSubjectSpinner.setAdapter(imeAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 8) { //신소재공학
                    minorSubjectSpinner.setAdapter(materialAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 9) { //전자전기공학
                    minorSubjectSpinner.setAdapter(electricAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 10) { //컴퓨터공학
                    minorSubjectSpinner.setAdapter(cseAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 11) { //화학공학
                    minorSubjectSpinner.setAdapter(chemiengineeringAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 12) { //창의IT
                    minorSubjectSpinner.setAdapter(citeAdapter);
                }
                else if(majorSubjectSpinner.getSelectedItemPosition() == 13) { //기타
                    minorSubjectSpinner.setAdapter(etcAdapter);
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

        majorsubjectdata = majorSubjectSpinner.getSelectedItem().toString(); //주요과목 데이터
        minorsubjectdata = minorSubjectSpinner.getSelectedItem().toString(); //세부과목 데이터
    }

    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == cancelButton){
            finish();
        }
        if(view == addButton){
            //데이터 전달하기
            finish();
        }
    }

}
