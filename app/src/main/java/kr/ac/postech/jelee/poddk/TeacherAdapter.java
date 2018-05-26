package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LG on 2018-05-25.
 */

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private Context mContext;
    private ArrayList<Person> mTeacherList;
    private OnItemClickListener mListener;

    public TeacherAdapter(Context context, ArrayList<Person> personList) {
        mContext = context;
        mTeacherList = personList;
    }


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
        mTeacherList = teacherList;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person, parent, false);
        TeacherViewHolder tvh = new TeacherViewHolder(v, mListener);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        Person currentItem = mTeacherList.get(position);

        holder.teacherProfile.setImageResource(currentItem.getImageID());
        holder.teacherName.setText(currentItem.getName());
        holder.teacherAge.setText(String.valueOf(currentItem.getAge()));
        holder.teacherSex.setText(currentItem.getSex());
        holder.teachermajorSubject.setText(currentItem.getmajorSubject());
        holder.teacherminorSubject.setText(currentItem.getminorSubject());
    }

    @Override
    public int getItemCount() {
        return mTeacherList.size();
    }
}
