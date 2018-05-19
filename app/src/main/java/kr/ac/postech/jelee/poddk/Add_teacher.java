package kr.ac.postech.jelee.poddk;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Add_teacher extends AppCompatActivity  implements View.OnClickListener{

    Button cancelButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_teacher);

        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
    }

    // 버튼 클릭했을 때
    public void onClick(View view){
        if(view == cancelButton){
            finish();
        }
        if(view == addButton){
            //데이터 전달하기
            finish();
        }
    }

}
