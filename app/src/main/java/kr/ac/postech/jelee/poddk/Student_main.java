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


public class Student_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addTeacherButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student_main, container, false);

        addTeacherButton = (FloatingActionButton)rootView.findViewById(R.id.addTeacher);
        addTeacherButton.setOnClickListener(this);

        return rootView;
    }


    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addTeacherButton){
            Intent intent = new Intent(getActivity(), Add_teacher.class);
            startActivity(intent);
        }
    }
}






