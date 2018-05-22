package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class TeacherListAdapter extends BaseAdapter{

    private Context context;
    private List<Teacher> teacherList;

    public TeacherListAdapter(Context context, List<Teacher> teacherList) {
        this.context= context;
        this.teacherList = teacherList;
    }

    @Override
    public int getCount() {
        return teacherList.size();
    }

    @Override
    public Object getItem(int i) {
        return teacherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =View.inflate(context, R.layout.teacher, null);
        ImageView profileImage = (ImageView)v.findViewById(R.id.teacherProfile);
        TextView nameText = (TextView)v.findViewById(R.id.teacherNameData);
        TextView ageText = (TextView)v.findViewById(R.id.teacherAgeData);
        TextView sexText = (TextView)v.findViewById(R.id.teacherSexData);
        TextView subjectText = (TextView)v.findViewById(R.id.teacherSubjectData);

        profileImage.setImageResource(teacherList.get(i).getImageID());
        nameText.setText(teacherList.get(i).getName());
        ageText.setText(String.valueOf(teacherList.get(i).getAge()));
        sexText.setText(teacherList.get(i).getSex());
        subjectText.setText(teacherList.get(i).getSubject());

        v.setTag(teacherList.get(i).getTeacherIDdata());

        RelativeLayout teacherLayout = (RelativeLayout)v.findViewById(R.id.OneTeacher);
        teacherLayout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        GridLayout teachergrid = (GridLayout)v.findViewById(R.id.teachergrid);

        return v;
    }



}
