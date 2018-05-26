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
        EditMailContent = findViewById(R.id.WriteMailContent);
        EditMailTitle = findViewById(R.id.WriteMailTitle);
        imgBtn = findViewById(R.id.SendMailImgBtn);
        receiverID = intent.getExtras().getString("receiverID");
        senderID = intent.getExtras().getString("userID");
        imgBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == imgBtn) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            currentTime = simpleDateFormat.format(date);
            StrMailTitle = EditMailTitle.getText().toString();
            StrMailContent = EditMailContent.getText().toString();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            AlertDialog dialog = builder.setMessage("메일이 전송되었습니다.")
                                    .setPositiveButton("확인", null)
                                    .create();
                            dialog.show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            AlertDialog dialog = builder.setMessage("메일 전송 실패하였습니다.")
                                    .setNegativeButton("다시 시도", null)
                                    .create();
                            dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            sendMailRequest sendmailRequest = new sendMailRequest("jim0307", StrMailTitle + "",
                    StrMailContent + "",senderID + "",currentTime + "", responseListener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(sendmailRequest);
        }
    }
}
