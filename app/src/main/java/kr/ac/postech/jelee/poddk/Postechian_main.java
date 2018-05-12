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


public class Postechian_main extends Fragment {

    private FloatingActionButton addStudent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.postechian_main, container, false);

        return rootView;
    }



    //+ 버튼 클릭했을 때
    public void onaddStudentClicked(View view){
        switch (view.getId()) {
            case R.id.button:
               // startActivity(new Intent(this, Add_student.class));
        }
    }


}
