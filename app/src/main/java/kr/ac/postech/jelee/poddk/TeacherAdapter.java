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


public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private Context mContext;
    private ArrayList<Person> mTeacherList;
    private ArrayList<Person> currentTeacherList=null;

    //private ArrayList<Person> tempnamelist=null;
    //private ArrayList<Person> tempsubjectlist=null;
    private OnItemClickListener mListener;

    public TeacherAdapter(Context context, ArrayList<Person> personList) {
        mContext = context;
        currentTeacherList = personList;
        //this.tempsubjectlist = personList;
        //this.tempnamelist = personList;
        mTeacherList = new ArrayList<Person>();
        mTeacherList.addAll(personList);
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        currentTeacherList.clear();
        if(charText.length() == 0) {
            currentTeacherList.addAll(mTeacherList);
        }else{
            for(Person person : mTeacherList) {
                String name = person.getName();
                if(name.toLowerCase().contains(charText)) {
                    currentTeacherList.add(person);
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

    public static class TeacherViewHolder extends RecyclerView.ViewHolder{
        public ImageView teacherProfile;
        public TextView teacherName;
        public TextView teacherAge;
        public TextView teacherSex;
        public TextView teachermajorSubject;
        public TextView teacherminorSubject;

        public TeacherViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            teacherProfile = (ImageView) itemView.findViewById(R.id.Profile);
            teacherName = (TextView) itemView.findViewById(R.id.NameData);
            teacherAge = (TextView) itemView.findViewById(R.id.AgeData);
            teacherSex = (TextView) itemView.findViewById(R.id.SexData);
            teachermajorSubject = (TextView) itemView.findViewById(R.id.majorSubjectData);
            teacherminorSubject = (TextView) itemView.findViewById(R.id.minorSubjectData);

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

    public TeacherAdapter(ArrayList<Person> teacherList) {
        currentTeacherList = teacherList;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        //View v = LayoutInflater.from(mContext).inflate(R.layout.person, parent, false);
        TeacherViewHolder svh = new TeacherViewHolder(v, mListener);

        return svh;
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        Person currentItem = currentTeacherList.get(position);

        holder.teacherProfile.setImageResource(currentItem.getImageID());
        holder.teacherName.setText(currentItem.getName());
        holder.teacherAge.setText(String.valueOf(currentItem.getAge()));
        holder.teacherSex.setText(currentItem.getSex());

        holder.teachermajorSubject.setText(currentItem.getmajorSubject());
        holder.teacherminorSubject.setText(currentItem.getminorSubject());
    }

    @Override
    public int getItemCount() {return currentTeacherList.size();
    }


}


