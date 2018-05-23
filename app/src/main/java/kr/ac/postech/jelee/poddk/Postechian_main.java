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


public class Postechian_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addStudentButton;
    TextView clicktosearch;
    LinearLayout hidesearchlayout;

    public Postechian_main() {

    }

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<Student> studentList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.postechian_main, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.studentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        studentList = new ArrayList<Student>();
        studentList.add(new Student("jelee1", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
        "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "물리", "양자물리",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "객체지향",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "양자물리",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "과학", "전자기학",
                "blank", "blank", "blank", "blank"));

        layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(studentList, getContext());
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();


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
        final Spinner majorSubjectSpinner = (Spinner)view.findViewById(R.id.majorSubjectsearch);

        final Spinner minorSubjectSpinner = (Spinner)view.findViewById(R.id.minorSubjectsearch);
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
                    minorSubjectSpinner.setAdapter(chemicalengineeringAdapter);
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


        addStudentButton = (FloatingActionButton)view.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        clicktosearch = (TextView)view.findViewById(R.id.clickbutton);
        clicktosearch.setOnClickListener(this);

        hidesearchlayout = (LinearLayout) view.findViewById(R.id.hidesearchBar);
        hidesearchlayout.setVisibility(View.GONE);


        return view;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder>
    {
        private Context context;
        private List<Student> mItems;
        private int lastPosition = -1;


        public MyAdapter(List<Student> items, Context mContext)
        {
            mItems = items;
            context = mContext;
        }


        //필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
        public ItemViewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student, parent, false);

            return new ItemViewHolder(v);
        }


        // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드 (View의 내용을 해당 포지션의 데이터로 바꿈)
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.studentProfile.setImageResource(mItems.get(position).getImageID());
            holder.studentName.setText(mItems.get(position).getName());
            holder.studentAge.setText(String.valueOf(mItems.get(position).getAge()));
            holder.studentSex.setText(mItems.get(position).getSex());
            holder.studentmajorSubject.setText(mItems.get(position).getmajorSubject());
            holder.studentminorSubject.setText(mItems.get(position).getminorSubject());

            setAnimation(holder.studentProfile, position);
        }

        // 필수로 Generate 되어야 하는 메소드 3 (데이터의 크기를 리턴)
        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView studentProfile;
            TextView studentName;
            TextView studentAge;
            TextView studentSex;
            TextView studentmajorSubject;
            TextView studentminorSubject;

            public ItemViewHolder(View view) {
                super(view);
                studentProfile = (ImageView) view.findViewById(R.id.studentProfile);
                studentName = (TextView) view.findViewById(R.id.studentNameData);
                studentAge = (TextView) view.findViewById(R.id.studentAgeData);
                studentSex = (TextView) view.findViewById(R.id.studentSexData);
                studentmajorSubject = (TextView) view.findViewById(R.id.studentmajorSubjectData);
                studentminorSubject = (TextView) view.findViewById(R.id.studentminorSubjectData);
            }
        }

        private void setAnimation(View viewToAnimate, int position)
        {
            // 새로 보여지는 뷰라면 애니메이션을 해줍니다
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

        /*
        //filter구현 - 이름 / 과목 / 나이 / 성별
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        studentListFiltered = studentList;
                    } else {
                        List<Student> filteredList = new ArrayList<Student>();
                        for (Student row : studentList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }

                        studentListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = studentListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    studentListFiltered = (ArrayList<Student>) filterResults.values;

                    // refresh the list with filtered data
                    notifyDataSetChanged();
                }
            };
        }
       */

    }

    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addStudentButton){
            Intent intent = new Intent(getActivity(), Add_student.class);
            startActivity(intent);
        }

        else if(view == clicktosearch){
            if(hidesearchlayout.getVisibility() == View.GONE){
                hidesearchlayout.setVisibility(View.VISIBLE);
            }
            else{
                hidesearchlayout.setVisibility(View.GONE);
            }
        }
    }


}


