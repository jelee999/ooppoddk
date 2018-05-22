package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Student_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addTeacherButton;

    public Student_main() {

    }

    //FloatingActionButton addStudentButton;

    private ListView teacherListView;
    private TeacherListAdapter adapter;
    private List<Teacher> teacherList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.student_main, container, false);


        teacherListView = (ListView) view.findViewById(R.id.teacherListView);
        teacherList = new ArrayList<Teacher>();
        teacherList.add(new Teacher("jelee1", R.drawable.profile, "김포스", 21, "여", "수학"));
        teacherList.add(new Teacher("jelee2", R.drawable.profile, "김포식", 21, "남", "수학"));

        adapter = new TeacherListAdapter(getContext(), teacherList);
        teacherListView.setAdapter(adapter);

        addTeacherButton = (FloatingActionButton)view.findViewById(R.id.addTeacher);
        addTeacherButton.setOnClickListener(this);

        return view;
    }

    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addTeacherButton){
            Intent intent = new Intent(getActivity(), Add_teacher.class);
            startActivity(intent);
        }
    }
}


