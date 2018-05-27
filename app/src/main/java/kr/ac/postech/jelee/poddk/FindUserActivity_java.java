package kr.ac.postech.jelee.poddk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FindUserActivity_java extends AppCompatActivity{

    private String findIDname;
    private String findIDemail;
    private String findPWname;
    private String findPWid;
    private String findPWquestion;
    private String findPWanswer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);


        final Spinner questionspinner = (Spinner) findViewById(R.id.findPW_question);
        ArrayAdapter questionadapter = ArrayAdapter.createFromResource(this, R.array.questionList, R.layout.support_simple_spinner_dropdown_item);
        questionspinner.setAdapter(questionadapter);


        final EditText IDnameText = findViewById(R.id.findID_name);
        final EditText IDemailText = findViewById(R.id.findID_email);
        final EditText PWnameText = findViewById(R.id.findPW_name);
        final EditText PWidText = findViewById(R.id.findPW_ID);
        final EditText PWaText = findViewById(R.id.findPW_answer);


        final Button findIDButton = findViewById(R.id.findIDButton);
        final Button findPWButton = findViewById(R.id.findPWButton);


        findIDButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                findIDname = IDnameText.getText().toString();
                findIDemail = IDemailText.getText().toString();

                if(!findIDname.equals("")&&!findIDemail.equals("")){
                    Response.Listener<String> responseLisener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                String id = jsonResponse.getString("id");
                                if(success){
                                    Toast.makeText(getApplicationContext(),"아이디는 "+id,Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "해당 계정이 존재하지 않습니다", Toast.LENGTH_LONG).show();

                                }
                            }
                            catch (JSONException e){
                                Toast.makeText(getApplicationContext(), "e", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    };
                    FindIDRequest findIDRequest = new FindIDRequest(findIDname, findIDemail, responseLisener);
                    RequestQueue queue = Volley.newRequestQueue(FindUserActivity_java.this);
                    queue.add(findIDRequest);
                }
                else
                    Toast.makeText(getApplicationContext(), "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show();



            }

        });








    }

}
