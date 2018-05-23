package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Student_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addTeacherButton;

    public Student_main() {

    }

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<Teacher> teacherList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.student_main, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.teacherRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        teacherList = new ArrayList<Teacher>();
        teacherList.add(new Teacher("jelee1", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Teacher("jelee1", R.drawable.profile, "김과학", 16, "여", "컴퓨터공학", "객체지향프로그래밍",
                "blank", "blank", "blank", "blank"));
        teacherList.add(new Teacher("jelee1", R.drawable.profile, "이산수", 17, "여", "컴퓨터공학", "데이터구조",
                "blank", "blank", "blank", "blank"));



        layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new TeacherAdapter(teacherList, getContext());
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();

        addTeacherButton = (FloatingActionButton)view.findViewById(R.id.addTeacher);
        addTeacherButton.setOnClickListener(this);

        return view;
    }

    class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ItemViewHolder>
    {
        private Context context;
        private List<Teacher> mItems;
        private int lastPosition = -1;


        public TeacherAdapter(List<Teacher> items, Context mContext)
        {
            mItems = items;
            context = mContext;
        }

        //필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
        public ItemViewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher, parent, false);
            return new ItemViewHolder(v);
        }

        // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드 (View의 내용을 해당 포지션의 데이터로 바꿈)
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.teacherProfile.setImageResource(mItems.get(position).getImageID());
            holder.teacherName.setText(mItems.get(position).getName());
            holder.teacherAge.setText(String.valueOf(mItems.get(position).getAge()));
            holder.teacherSex.setText(mItems.get(position).getSex());
            holder.teachermajorSubject.setText(mItems.get(position).getmajorSubject());
            holder.teacherminorSubject.setText(mItems.get(position).getminorSubject());

            setAnimation(holder.teacherProfile, position);
        }

        // 필수로 Generate 되어야 하는 메소드 3 (데이터의 크기를 리턴)
        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView teacherProfile;
            TextView teacherName;
            TextView teacherAge;
            TextView teacherSex;
            TextView teachermajorSubject;
            TextView teacherminorSubject;

            public ItemViewHolder(View view) {
                super(view);
                teacherProfile = (ImageView) view.findViewById(R.id.teacherProfile);
                teacherName = (TextView) view.findViewById(R.id.teacherNameData);
                teacherAge = (TextView) view.findViewById(R.id.teacherAgeData);
                teacherSex = (TextView) view.findViewById(R.id.teacherSexData);
                teachermajorSubject = (TextView) view.findViewById(R.id.teachermajorSubjectData);
                teacherminorSubject = (TextView) view.findViewById(R.id.teacherminorSubjectData);
            }
        }

        private void setAnimation(View viewToAnimate, int position)
        {
            // 새로 보여지는 뷰라면 애니메이션을 해줍니다
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

    }

    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addTeacherButton){
            Intent intent = new Intent(getActivity(), Add_teacher.class);
            startActivity(intent);
        }
    }
}
