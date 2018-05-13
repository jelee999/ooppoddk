package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Profile extends Fragment implements View.OnClickListener {
    Button mailboxBtn;
    Button managementBtn;
    Button developerInfoBtn;
    Button notificationBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (RelativeLayout) inflater.inflate(R.layout.activity_profile, container, false);
        mailboxBtn = (Button)rootView.findViewById(R.id.mailboxButton);
        managementBtn = (Button)rootView.findViewById(R.id.managementButton);
        developerInfoBtn = (Button)rootView.findViewById(R.id.developerInfoButton);
        notificationBtn = (Button)rootView.findViewById(R.id.developerInfoButton);

        mailboxBtn.setOnClickListener(this);
        managementBtn.setOnClickListener(this);
        developerInfoBtn.setOnClickListener(this);
        notificationBtn.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        if (view == mailboxBtn)
        {
            Intent intent = new Intent(getActivity(), MailboxActivity.class);
            startActivity(intent);
        }
        else if (view == managementBtn)
        {

        }
        else if (view == developerInfoBtn)
        {

        }
        else if (view == notificationBtn) {

        }
    }
}

