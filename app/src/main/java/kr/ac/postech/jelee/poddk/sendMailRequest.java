package kr.ac.postech.jelee.poddk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljh45 on 2018-05-22.
 */

public class sendMailRequest extends StringRequest{
    final static private String URL = "http://ljh453.cafe24.com/podduk_mailwrite.php";
    private Map<String, String> parameters;

    public sendMailRequest(String userID, String mailTitle, String mailContent, String senderID,String currentTime, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("receive_ID", userID);
        parameters.put("mail_title", mailTitle);
        parameters.put("mail_content", mailContent);
        parameters.put("receive_id", senderID);
        parameters.put("receive_date", currentTime);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
