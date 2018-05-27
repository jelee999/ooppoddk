package kr.ac.postech.jelee.poddk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FindIDRequest extends StringRequest {
    final static private String URL = "http://ljh453.cafe24.com/podduk_findid.php";
    private Map<String,String> params;

    public FindIDRequest(String id, String email, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("id", id);
        params.put("email",email);
    }

    @Override
    public Map<String,String>getParams(){
        return params;
    }

}