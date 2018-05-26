package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LG on 2018-05-25.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context mContext;
    private ArrayList<Person> mStudentList;
    private ArrayList<Person> currentStudentList;
    private OnItemClickListener mListener;

    public StudentAdapter(Context context, ArrayList<Person> personList) {
        mContext = context;
        mStudentList = personList;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickLIstener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        public ImageView studentProfile;
        public TextView studentName;
        public TextView studentAge;
        public TextView studentSex;
        public TextView studentmajorSubject;
        public TextView studentminorSubject;

        public StudentViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            studentProfile = (ImageView) itemView.findViewById(R.id.Profile);
            studentName = (TextView) itemView.findViewById(R.id.NameData);
            studentAge = (TextView) itemView.findViewById(R.id.AgeData);
            studentSex = (TextView) itemView.findViewById(R.id.SexData);
            studentmajorSubject = (TextView) itemView.findViewById(R.id.majorSubjectData);
            studentminorSubject = (TextView) itemView.findViewById(R.id.minorSubjectData);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public StudentAdapter(ArrayList<Person> studentList) {
        mStudentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        //View v = LayoutInflater.from(mContext).inflate(R.layout.person, parent, false);
        StudentViewHolder svh = new StudentViewHolder(v, mListener);

        return svh;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Person currentItem = mStudentList.get(position);

        holder.studentProfile.setImageResource(currentItem.getImageID());
        holder.studentName.setText(currentItem.getName());
        holder.studentAge.setText(String.valueOf(currentItem.getAge()));
        holder.studentSex.setText(currentItem.getSex());

        holder.studentmajorSubject.setText(currentItem.getmajorSubject());
        holder.studentminorSubject.setText(currentItem.getminorSubject());
    }

    @Override
    public int getItemCount() {return mStudentList.size();
    }


}


