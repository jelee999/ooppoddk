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


public class StudentListAdapter extends BaseAdapter{

    private Context context;
    private List<Student> studentList;

    public StudentListAdapter(Context context, List<Student> studentList) {
        this.context= context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =View.inflate(context, R.layout.student, null);
        ImageView profileImage = (ImageView)v.findViewById(R.id.studentProfile);
        TextView nameText = (TextView)v.findViewById(R.id.studentNameData);
        TextView ageText = (TextView)v.findViewById(R.id.studentAgeData);
        TextView sexText = (TextView)v.findViewById(R.id.studentSexData);
        TextView subjectText = (TextView)v.findViewById(R.id.studentSubjectData);

        profileImage.setImageResource(studentList.get(i).getImageID());
        nameText.setText(studentList.get(i).getName());
        ageText.setText(String.valueOf(studentList.get(i).getAge()));
        sexText.setText(studentList.get(i).getSex());
        subjectText.setText(studentList.get(i).getSubject());

        v.setTag(studentList.get(i).getStudentIDdata());

        RelativeLayout studentLayout = (RelativeLayout)v.findViewById(R.id.OneStudent);
        studentLayout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        GridLayout studentgrid = (GridLayout)v.findViewById(R.id.studentgrid);

        return v;
    }



}
