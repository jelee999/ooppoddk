package kr.ac.postech.jelee.poddk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://ljh453.cafe24.com/podduk_checksignup.php";
    private Map<String,String> params;

    public RegisterRequest(String name, String id, String pw, String email, String phonenum, String gender, String birth_year, String birth_month, String birth_day, String major,String pw_question, String pw_answer, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("id", id);
        params.put("pw", pw);
        params.put("email", email);
        params.put("phonenum", phonenum);
        params.put("gender", gender);
        params.put("birth_year", birth_year);
        params.put("birth_month", birth_month);
        params.put("birth_day", birth_day);
        params.put("major", major);
        params.put("pw_question", pw_question);
        params.put("pw_answer", pw_answer);
    }

    @Override
    public Map<String,String>getParams(){
        return params;
    }

}