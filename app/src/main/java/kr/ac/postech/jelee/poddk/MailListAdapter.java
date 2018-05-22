package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ljh45 on 2018-05-21.
 */

public class MailListAdapter extends BaseAdapter{

    private Context context;
    private List<Mail> mailList;

    public MailListAdapter(Context context, List<Mail> mailList) {
        this.context= context;
        this.mailList = mailList;
    }

    @Override
    public int getCount() {
        return mailList.size();
    }

    @Override
    public Object getItem(int i) {
        return mailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =View.inflate(context, R.layout.mail, null);
        ImageView profileImage = (ImageView)v.findViewById(R.id.mailProfile);
        TextView titleText = (TextView)v.findViewById(R.id.mailTitle);
        TextView dateText = (TextView)v.findViewById(R.id.mailDate);
        TextView contentText = (TextView)v.findViewById(R.id.mailContent);

        profileImage.setImageResource(mailList.get(i).getImageID());
        titleText.setText(mailList.get(i).getTitle());
        dateText.setText(mailList.get(i).getDate());
        contentText.setText(mailList.get(i).getContent());

        v.setTag(mailList.get(i).getTitle());


        RelativeLayout mailLayout = (RelativeLayout)v.findViewById(R.id.OneMail);
        mailLayout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        return v;
    }



}
