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
import java.util.Locale;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context mContext;
    private ArrayList<Person> mStudentList;
    private ArrayList<Person> currentStudentList=null;

    //private ArrayList<Person> tempnamelist=null;
    //private ArrayList<Person> tempsubjectlist=null;
    private OnItemClickListener mListener;

    public StudentAdapter(Context context, ArrayList<Person> personList) {
        mContext = context;
        this.currentStudentList = new ArrayList<Person>();
        currentStudentList.addAll(personList);
        //this.tempsubjectlist = personList;
        //this.tempnamelist = personList;
        mStudentList = new ArrayList<Person>();
        mStudentList.addAll(personList);
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        currentStudentList.clear();
        if(charText.length() == 0) {
            currentStudentList.addAll(mStudentList);
        }else{
            for(Person person : mStudentList) {
                String name = person.getName();
                if(name.toLowerCase().contains(charText)) {
                    currentStudentList.add(person);
                }
            }
        }
        notifyDataSetChanged();
    }
/*
    public void filtermajorsubject(String majorsubject, String minorsubject ){
        currentStudentList.clear();
        if(majorsubject !=null)
            majorsubject = majorsubject.toLowerCase(Locale.getDefault());
        if(minorsubject !=null)
            minorsubject = minorsubject.toLowerCase(Locale.getDefault());
        if(majorsubject == null) {
           currentStudentList.addAll(mStudentList);
        }else{
            if(minorsubject == null){
                for(Person person : mStudentList) {
                    String major = person.getmajorSubject();
                    if(major.toLowerCase().equals(majorsubject)) {
                        currentStudentList.add(person);
                    }
                }
            }else{
                for (Person person :mStudentList) {
                    String minor = person.getminorSubject();
                    if(minor.toLowerCase().equals(minorsubject)) {
                        currentStudentList.add(person);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
*/

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
        currentStudentList = studentList;
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
        Person currentItem = currentStudentList.get(position);

        holder.studentProfile.setImageResource(currentItem.getImageID());
        holder.studentName.setText(currentItem.getName());
        holder.studentAge.setText(String.valueOf(currentItem.getAge()));
        holder.studentSex.setText(currentItem.getSex());

        holder.studentmajorSubject.setText(currentItem.getmajorSubject());
        holder.studentminorSubject.setText(currentItem.getminorSubject());
    }

    @Override
    public int getItemCount() {return currentStudentList.size();
    }


}


