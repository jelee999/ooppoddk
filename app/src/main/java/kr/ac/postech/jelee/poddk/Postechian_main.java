package kr.ac.postech.jelee.poddk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class Postechian_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addStudentButton;

    public Postechian_main() {

    }

    //FloatingActionButton addStudentButton;

    private ListView studentListView;
    private StudentListAdapter adapter;
    private List<Student> studentList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.postechian_main, container, false);


        studentListView = (ListView) view.findViewById(R.id.studentListView);
        studentList = new ArrayList<Student>();
        studentList.add(new Student("jelee1", R.drawable.profile, "김포항", 21, "여", "수학"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김포항", 21, "여", "수학"));

        adapter = new StudentListAdapter(getContext(), studentList);
        studentListView.setAdapter(adapter);

        addStudentButton = (FloatingActionButton)view.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        return view;
    }

    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addStudentButton){
            Intent intent = new Intent(getActivity(), Add_student.class);
            startActivity(intent);
        }
    }
}
