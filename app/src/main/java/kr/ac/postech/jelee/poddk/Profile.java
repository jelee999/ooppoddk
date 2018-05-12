package kr.ac.postech.jelee.poddk;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class Profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (RelativeLayout)inflater.inflate(R.layout.activity_profile, container, false);
        return rootView;
    }
}
