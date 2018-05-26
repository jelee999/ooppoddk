package kr.ac.postech.jelee.poddk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {
    final static private String URL = "http://ljh453.cafe24.com/podduk_checkid.php";
    private Map<String,String> params;

    public ValidateRequest(String id, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("id", id);
    }

    @Override
    public Map<String,String>getParams(){
        return params;
    }

}