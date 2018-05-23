package kr.ac.postech.jelee.poddk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class Postechian_main extends Fragment implements View.OnClickListener{

    FloatingActionButton addStudentButton;
    Button clicktosearch;

    public Postechian_main() {

    }


    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<Student> studentList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.postechian_main, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.studentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        studentList = new ArrayList<Student>();
        studentList.add(new Student("jelee1", R.drawable.profile, "김수학", 17, "여", "수학", "미분방정식",
        "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "물리", "양자물리",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "객체지향",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "컴공", "양자물리",
                "blank", "blank", "blank", "blank"));
        studentList.add(new Student("jelee2", R.drawable.profile, "김과학", 25, "여", "과학", "전자기학",
                "blank", "blank", "blank", "blank"));

        layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(studentList, getContext());
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();

        addStudentButton = (FloatingActionButton)view.findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);

        return view;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder>
    {
        private Context context;
        private List<Student> mItems;
        private int lastPosition = -1;


        public MyAdapter(List<Student> items, Context mContext)
        {
            mItems = items;
            context = mContext;
        }

        //필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
        public ItemViewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student, parent, false);
            return new ItemViewHolder(v);
        }

        // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드 (View의 내용을 해당 포지션의 데이터로 바꿈)
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.studentProfile.setImageResource(mItems.get(position).getImageID());
            holder.studentName.setText(mItems.get(position).getName());
            holder.studentAge.setText(String.valueOf(mItems.get(position).getAge()));
            holder.studentSex.setText(mItems.get(position).getSex());
            holder.studentmajorSubject.setText(mItems.get(position).getmajorSubject());
            holder.studentminorSubject.setText(mItems.get(position).getminorSubject());

            setAnimation(holder.studentProfile, position);
        }

        // 필수로 Generate 되어야 하는 메소드 3 (데이터의 크기를 리턴)
        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            ImageView studentProfile;
            TextView studentName;
            TextView studentAge;
            TextView studentSex;
            TextView studentmajorSubject;
            TextView studentminorSubject;

            public ItemViewHolder(View view) {
                super(view);
                studentProfile = (ImageView) view.findViewById(R.id.studentProfile);
                studentName = (TextView) view.findViewById(R.id.studentNameData);
                studentAge = (TextView) view.findViewById(R.id.studentAgeData);
                studentSex = (TextView) view.findViewById(R.id.studentSexData);
                studentmajorSubject = (TextView) view.findViewById(R.id.studentmajorSubjectData);
                studentminorSubject = (TextView) view.findViewById(R.id.studentminorSubjectData);
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

        /*
        //filter구현 - 이름 / 과목 / 나이 / 성별
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        studentListFiltered = studentList;
                    } else {
                        List<Student> filteredList = new ArrayList<Student>();
                        for (Student row : studentList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }

                        studentListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = studentListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    studentListFiltered = (ArrayList<Student>) filterResults.values;

                    // refresh the list with filtered data
                    notifyDataSetChanged();
                }
            };
        }
       */

    }

    //+ 버튼 클릭했을 때
    public void onClick(View view){
        if(view == addStudentButton){
            Intent intent = new Intent(getActivity(), Add_student.class);
            startActivity(intent);
        }
    }


}


