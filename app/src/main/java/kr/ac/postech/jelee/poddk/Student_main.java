package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Student_main extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student_main, container, false);
        return rootView;
    }

    public void onaddTeacherClicked(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        startActivity(intent);
    }
}
