package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljh45 on 2018-05-22.
 */

public class DeleteRequest extends StringRequest{
    final static private String URL = "http://ljh453.cafe24.com/podduk_maildelete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String userID, String mailTitle,String mailContent, String senderID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("send_id", senderID);//메일 발송인 ID
        parameters.put("receive_id", userID);//메일 송신인 ID(user ID)
        parameters.put("mail_title", mailTitle);//메일 제목
        parameters.put("mail_content", mailContent);//메일 내용
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
