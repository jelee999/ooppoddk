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
import android.widget.Toast;
import android.widget.Button;


public class Postechian_main extends Fragment implements View.OnClickListener {

    FloatingActionButton addStudentButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.postechian_main, container, false);

        addStudentButton = (FloatingActionButton)rootView.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        return rootView;
    }



    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addStudentButton){
            Intent intent = new Intent(getActivity(), Add_student.class);
            startActivity(intent);
        }
    }


}
