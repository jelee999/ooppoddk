package kr.ac.postech.jelee.poddk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class Profile extends Fragment implements View.OnClickListener {
    Button mailboxBtn;
    Button managementBtn;
    Button developerInfoBtn;
    Button noticeBoardBtn;
    ImageButton settingBtn;
    RelativeLayout settingLayout;
    int v = 0;

    RadioGroup rg;
    AudioManager am;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (RelativeLayout) inflater.inflate(R.layout.activity_profile, container, false);
        mailboxBtn = (Button) rootView.findViewById(R.id.mailboxButton);
        managementBtn = (Button) rootView.findViewById(R.id.managementButton);
        developerInfoBtn = (Button) rootView.findViewById(R.id.developerInfoButton);
        noticeBoardBtn = (Button) rootView.findViewById(R.id.NoticeBoardButton);
        settingBtn = (ImageButton) rootView.findViewById(R.id.setting);
        settingLayout = (RelativeLayout) rootView.findViewById(R.id.settingLayout);



        mailboxBtn.setOnClickListener(this);
        managementBtn.setOnClickListener(this);
        developerInfoBtn.setOnClickListener(this);
        noticeBoardBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        if (view == mailboxBtn) {
            Intent intent = new Intent(getActivity(), MailboxActivity.class);
            startActivity(intent);
        } else if (view == managementBtn) {
            Intent intent = new Intent(getActivity(), ManagementActivity.class);
            startActivity(intent);
        } else if (view == developerInfoBtn) {
            Intent intent = new Intent(getActivity(), DeveloperInfoActivity.class);
            startActivity(intent);
        } else if (view == noticeBoardBtn) {
            Intent intent = new Intent(getActivity(), NoticeBoardActivity.class);
            startActivity(intent);
        } else if (view == settingBtn) {
            if (v == 0) v++;
            else if (v == 1) v--;

            if (v == 1) {
                settingLayout.setVisibility(VISIBLE);
            } else {
                settingLayout.setVisibility(INVISIBLE);
            }
        }
    }
}


