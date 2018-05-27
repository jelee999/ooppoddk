package kr.ac.postech.jelee.poddk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteMail extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtn;
    String currentTime;
    String senderID;
    String receiverID;
    String StrMailTitle;
    String StrMailContent;
    EditText EditMailTitle;
    EditText EditMailContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_mail);
        Intent intent = getIntent();
        //View_student 및 View_teacher에서 연락하기 버튼을 눌렀을 때 메일 쓰는 화면이 나오는 형식이므로
        //intent를 통해 사용자 및 대상의 정보를 intent로 넘겨받는다.
        EditMailContent = findViewById(R.id.WriteMailContent);
        //메일 내용에 관한 뷰로 초기화
        EditMailTitle = findViewById(R.id.WriteMailTitle);
        //메일 제목에 관한 뷰로 초기화
        imgBtn = findViewById(R.id.SendMailImgBtn);
        //전송버튼에 해당하는 뷰로 초기화
        receiverID = intent.getExtras().getString("receiverID");
        //메일을 받는 사람의 ID를 intent로 받은 정보로 초기화
        senderID = intent.getExtras().getString("userID");
        //메일을 보낸 사람의 ID를 intent로 받은 유저의 ID로 초기화
        imgBtn.setOnClickListener(this);
        //이미지 (전송)버튼에 대한 이벤트 처리를 하기 위해 Listener 선언
    }


    @Override
    public void onClick(View view) {
        if (view == imgBtn) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            currentTime = simpleDateFormat.format(date);
            //yyyy년 mm월 dd일의 형식으로 메일을 보내는 날짜를 저장.
            StrMailTitle = EditMailTitle.getText().toString();
            //버튼을 클릭했을 때, EditText 뷰에 저장된 텍스트를 문자열 형태로 저장.
            StrMailContent = EditMailContent.getText().toString();
            //버튼을 클릭했을 때, EditText 뷰에 저장된 텍스트를 문자열 형태로 저장.


            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {//서버로부터 response를 받도록 함
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        //success 받으면
                        if (success) {
                            Toast.makeText(getApplicationContext(), "메일이 전송되었습니다.", Toast.LENGTH_SHORT).show();
                            //메일이 전송되었다는 알림 출력
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            //전송되었는데도 메일 작성 화면에 남아있지 않도록 MainActivity로 화면 전환
                        } else {
                            //메일 전송 실패한 경우
                            Toast.makeText(getApplicationContext(), "메일 전송이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            //메일 전송이 실패했다는 알림 출력
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "서버로부터 응답이 없습니다.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        //서버로 부터 응답이 없는 경우, 응답이 없다는 알림 출력
                    }
                }
            };
            sendMailRequest sendmailRequest = new sendMailRequest(senderID+"", StrMailTitle + "",
                    StrMailContent + "",senderID + "",currentTime + "", responseListener);
            //메일을 보내는 요청 생성
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(sendmailRequest);
            //메일 보내는 요청(SendMailRequest) 실행

        }
    }
}
