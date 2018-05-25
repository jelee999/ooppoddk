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

import static android.content.Intent.getIntent;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class Profile extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button mailboxBtn;
    Button profileEditBtn;
    Button developerInfoBtn;
    Button noticeBoardBtn;
    ImageButton settingBtn;
    RelativeLayout settingLayout;
    int v = 0;
    RadioGroup rg;

    @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SharedPreferences saved = getActivity().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        String ID = saved.getString("inputID", "0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (RelativeLayout) inflater.inflate(R.layout.activity_profile, container, false);
        mailboxBtn = (Button) rootView.findViewById(R.id.mailboxButton);
        profileEditBtn = (Button) rootView.findViewById(R.id.profileEditButton);
        developerInfoBtn = (Button) rootView.findViewById(R.id.developerInfoButton);
        noticeBoardBtn = (Button) rootView.findViewById(R.id.NoticeBoardButton);
        settingBtn = (ImageButton) rootView.findViewById(R.id.setting);
        settingLayout = (RelativeLayout) rootView.findViewById(R.id.settingLayout);
        rg = (RadioGroup)rootView.findViewById(R.id.settingGroup);
        /*am = (AudioManager)getContext().getSystemService(getActivity().AUDIO_SERVICE);*/




        mailboxBtn.setOnClickListener(this);
        profileEditBtn.setOnClickListener(this);
        developerInfoBtn.setOnClickListener(this);
        noticeBoardBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        if (view == mailboxBtn) {
            Intent intent = new Intent(getActivity(), MailboxActivity.class);
            startActivity(intent);
        } else if (view == profileEditBtn) {
            Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
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

            if (v == 1)
            {
                settingLayout.setVisibility(VISIBLE);
            }
            else
            {
                settingLayout.setVisibility(INVISIBLE);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.vibrationRadioButton)
        {
            /*am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);*/
        }
        else if( i == R.id.soundRadioButton)
        {

        }
        else if( i == R.id.muteRadioButton)
        {

        }

        Toast.makeText(getContext(), "모드가 변경되었습니다.", Toast.LENGTH_SHORT).show();
    }
}


