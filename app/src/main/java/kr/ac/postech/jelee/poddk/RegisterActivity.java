package kr.ac.postech.jelee.poddk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class RegisterActivity extends AppCompatActivity {

    boolean isidchecked=false;

    private String name;
    private String id;
    private String pw;
    private String pw_check;
    private String birth_year;
    private String birth_month;
    private String birth_day;
    private String major;
    private String gender;
    private String pw_question;
    private String pw_answer;
    private String email;
    private String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_postech);

        final Spinner yearspinner = (Spinner) findViewById(R.id.user_birthday_year);
        ArrayAdapter yearadapter = ArrayAdapter.createFromResource(this, R.array.yearsList, R.layout.support_simple_spinner_dropdown_item);
        yearspinner.setAdapter(yearadapter);

        final Spinner monthspinner = (Spinner) findViewById(R.id.user_birthday_month);
        ArrayAdapter monthadapter = ArrayAdapter.createFromResource(this, R.array.monthList, R.layout.support_simple_spinner_dropdown_item);
        monthspinner.setAdapter(monthadapter);

        final Spinner dayspinner = (Spinner) findViewById(R.id.user_birthday_date);
        ArrayAdapter dayadapter = ArrayAdapter.createFromResource(this, R.array.dateList, R.layout.support_simple_spinner_dropdown_item);
        dayspinner.setAdapter(dayadapter);

        final Spinner majorspinner = (Spinner) findViewById(R.id.user_major);
        ArrayAdapter majoradapter = ArrayAdapter.createFromResource(this, R.array.majorList1, R.layout.support_simple_spinner_dropdown_item);
        majorspinner.setAdapter(majoradapter);

        final Spinner questionspinner = (Spinner) findViewById(R.id.user_pw_question);
        ArrayAdapter questionadapter = ArrayAdapter.createFromResource(this, R.array.questionList, R.layout.support_simple_spinner_dropdown_item);
        questionspinner.setAdapter(questionadapter);

        final EditText nameText = (EditText) findViewById(R.id.user_name);
        final EditText idText = (EditText) findViewById(R.id.user_id);
        final EditText pwText = (EditText) findViewById(R.id.user_pw);
        final EditText pwchkText = (EditText) findViewById(R.id.user_pw_check);
        final EditText emailText = (EditText) findViewById(R.id.user_email);
        final EditText phoneText = (EditText) findViewById(R.id.user_phone);
        final EditText ansText = (EditText) findViewById(R.id.user_pw_answer);

/*
        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.user_sex);
        int genderGroupID = genderGroup.getCheckedRadioButtonId();
        gender = ((RadioButton) findViewById(genderGroupID)).getText().toString();
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                gender = genderButton.getText().toString();
            }
        });
*/
        RadioButton womanButton = (RadioButton) findViewById(R.id.user_woman);
        if(womanButton.isChecked())
            gender = "female";
        else
            gender = "male";


        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                id = idText.getText().toString();
                pw = pwText.getText().toString();
                pw_check = pwchkText.getText().toString();
                birth_year = yearspinner.getSelectedItem().toString();
                birth_month = monthspinner.getSelectedItem().toString();
                birth_day = dayspinner.getSelectedItem().toString();
                major = majorspinner.getSelectedItem().toString();
                pw_question = questionspinner.getSelectedItem().toString();
                pw_answer = ansText.getText().toString();
                email = emailText.getText().toString();
                phonenum = phoneText.getText().toString();


                if(isidchecked) {
                    if (!name.equals("") && !id.equals("") && !pw.equals("") && !pw_check.equals("") && !email.equals("") && !phonenum.equals("") && !pw_answer.equals("")) {
                        if (pw.length() > 3 && pw.length() < 17) {
                            if (pw.equals(pw_check)) {

                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");
                                            if (success) {
                                                Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                RegisterRequest registerRequest = new RegisterRequest(name, id, pw, email, phonenum, gender, birth_year, birth_month, birth_day, major, pw_question, pw_answer, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                                queue.add(registerRequest);


                            } else
                                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "비밀번호의 길이는 4~16이어야 합니다", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplicationContext(), "ID 중복 확인을 하지 않았습니다", Toast.LENGTH_LONG).show();
            }
        });



        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = idText.getText().toString();

                if (!id.equals("")) {

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Toast.makeText(getApplicationContext(), "사용할 수 있는 ID입니다", Toast.LENGTH_LONG).show();
                                        idText.setEnabled(false);
                                        isidchecked=true;
                                        idText.setTextColor(getResources().getColor(R.color.colorGray));
                                        validateButton.setEnabled(false);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "사용할 수 없는 ID입니다", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        ValidateRequest validateRequest = new ValidateRequest(id, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(validateRequest);


                    } else

                    Toast.makeText(getApplicationContext(), "ID를 입력해주세요", Toast.LENGTH_LONG).show();
            }
        });


    }

}
