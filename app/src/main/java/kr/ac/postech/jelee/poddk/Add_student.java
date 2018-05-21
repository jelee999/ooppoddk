package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Add_student extends AppCompatActivity implements View.OnClickListener{

    Button cancelButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

    /*array.xml에 넣었음
        //input array data
        final ArrayList<String> majorList = new ArrayList<>();
        majorList.add("선택사항없음");
        majorList.add("언어");
        majorList.add("수학");
        majorList.add("물리");
        majorList.add("화학");
        majorList.add("생명");
        majorList.add("기계공학");
        majorList.add("산업경영공학");
        majorList.add("신소재공학");
        majorList.add("전자전기공학");
        majorList.add("컴퓨터공학");
        majorList.add("화학공학");
        majorList.add("창의IT");
        majorList.add("기타");

        final ArrayList<String> linguisticList = new ArrayList<>();
        linguisticList.add("영어");
        linguisticList.add("중국어");
        linguisticList.add("일본어");
        linguisticList.add("독일어");
        linguisticList.add("프랑스어");
        linguisticList.add("스페인어");
        linguisticList.add("기타");

        final ArrayList<String> mathList = new ArrayList<>();
        mathList.add("미적분학");
        mathList.add("미분방정식");
        mathList.add("응용선형대수");
        mathList.add("응용복소함수론");
        mathList.add("확률및통계");
        mathList.add("이산수학");
        mathList.add("기타");

        final ArrayList<String> physicsList = new ArrayList<>();
        physicsList.add("일반물리");
        physicsList.add("역학");
        physicsList.add("전자기학");
        physicsList.add("수리물리");
        physicsList.add("양자물리");
        physicsList.add("기타");

        final ArrayList<String> chemistryList = new ArrayList<>();
        chemistryList.add("일반화학");
        chemistryList.add("물리화학");
        chemistryList.add("유기화학");
        chemistryList.add("화학분석");
        chemistryList.add("기타");

        final ArrayList<String> lifeScienceList = new ArrayList<>();
        lifeScienceList.add("일반생명과학");
        lifeScienceList.add("세포생물학");
        lifeScienceList.add("생태학");
        lifeScienceList.add("생명과학의원리");
        lifeScienceList.add("기타");

        final ArrayList<String> mechanicList = new ArrayList<>();
        mechanicList.add("전산제도및설계");
        mechanicList.add("고체역학");
        mechanicList.add("열역학");
        mechanicList.add("동역학");
        mechanicList.add("기계재료학");
        mechanicList.add("센서및측정");
        mechanicList.add("기타");

        final ArrayList<String> imeList = new ArrayList<>();
        imeList.add("재무회계");
        imeList.add("제품생산공정설계");
        imeList.add("경영과학I");
        imeList.add("경영과학II");
        imeList.add("실험계획개론");
        imeList.add("최적화개론");
        imeList.add("기타");

        final ArrayList<String> materialList = new ArrayList<>();
        materialList.add("신소재과학");
        materialList.add("소재양자론");
        materialList.add("소재의결함론");
        materialList.add("소재구조론");
        materialList.add("열역학");
        materialList.add("소재미세조직발현");
        materialList.add("기타");

        final ArrayList<String> electricList = new ArrayList<>();
        electricList.add("반도체전자공학I");
        electricList.add("회로이론");
        electricList.add("전자기학개론");
        electricList.add("신호및시스템");
        electricList.add("디지털시스템설계");
        electricList.add("기타");

        final ArrayList<String> cseList = new ArrayList<>();
        cseList.add("프로그래밍과문제해결");
        cseList.add("객체지향프로그래밍");
        cseList.add("데이터구조");
        cseList.add("전산수학");
        cseList.add("컴퓨터SW시스템개론");
        cseList.add("기타");

        final ArrayList<String> chemiengineeringList = new ArrayList<>();
        chemiengineeringList.add("화공물리화학");
        chemiengineeringList.add("화공열역학");
        chemiengineeringList.add("유기화학I");
        chemiengineeringList.add("유기화학II");
        chemiengineeringList.add("화학생명공학");
        chemiengineeringList.add("기타");

        final ArrayList<String> citeList = new ArrayList<>();
        citeList.add("기타");

        final ArrayList<String> etcList = new ArrayList<>();
        citeList.add("선택사항없음");
    */
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



        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
    }

    // 취소버튼 클릭했을 때
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
