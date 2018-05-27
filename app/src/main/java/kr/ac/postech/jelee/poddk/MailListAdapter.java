package kr.ac.postech.jelee.poddk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ljh45 on 2018-05-21.
 */

public class MailListAdapter extends BaseAdapter {

    private Context context;
    private List<Mail> mailList;

    public MailListAdapter(Context context, List<Mail> mailList) {
        this.context = context;
        this.mailList = mailList;
    }

    @Override
    public int getCount() {
        return mailList.size();
    }

    @Override
    public Object getItem(int i) {
        return mailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.mail, null);
        TextView titleText = (TextView) v.findViewById(R.id.mailTitle);
        TextView dateText = (TextView) v.findViewById(R.id.mailDate);
        TextView contentText = (TextView) v.findViewById(R.id.mailContent);
        TextView senderIDText = (TextView) v.findViewById(R.id.MailSenderID);
        TextView senderNameText = (TextView) v.findViewById(R.id.MailSenderName);
        //mail.xml 파일의 뷰로 메일에 필요한 TextView(메일 제목, 메일 내용, 날짜, 송신인 ID, 송신인 이름)을 초기화.

        titleText.setText(mailList.get(i).getMailTitle());
        dateText.setText(mailList.get(i).getDate());
        contentText.setText(mailList.get(i).getMailContent());
        senderIDText.setText(mailList.get(i).getSenderID());
        senderNameText.setText(mailList.get(i).getSenderName());
        // 메일의 index가 i인 내용으로 TextView의 내용을 변경한다.

        v.setTag(mailList.get(i).getMailTitle());
        //i번째 메일 제목으로 태그 지정(사실상 불필요)

        final RelativeLayout mailLayout = (RelativeLayout) v.findViewById(R.id.OneMail);
        //mailLayout이라는 RelativeLayout을 mail.xml의 RelativeLayout으로 초기화
        //이는 메일 하나에 대한 레이아웃.
        final Button deleteButton = (Button) v.findViewById(R.id.mailDeleteButton);
        //deleteButton 을 mail.xml내 mailDeleteButton 뷰로 초기화
        deleteButton.setOnClickListener(new View.OnClickListener() {//deleteButton의 터치 에 대한 이벤트 처리
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {//서버로부터 받은 response를 받을 때
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(context, "메일이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                                //메일이 삭제가 성공했을 때 메일이 삭제되었다는 알림을 띄운다.
                                mailList.remove(mailList.remove(i));
                                //메일리스트에 index가 i인 메일을 제거
                                notifyDataSetChanged();
                                //메일이 제거되었으므로 데이터가 바뀌었다는 것을 알려줌.
                                //메일 리스트를 업데이트
                            } else {
                                Toast.makeText(context, "메일 삭제가 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                //메일이 삭제되지 않았을 경우 메일 삭제가 실패했다는 알림을 띄운다
                            }
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            //response를 못받았을 때 error라는 알림을 띄운다
                        }
                    }
                };

                DeleteRequest deleteRequest = new DeleteRequest("jim0307", mailList.get(i).getMailTitle() + "",
                        mailList.get(i).getMailContent() + "", mailList.get(i).getSenderID() + "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(deleteRequest);
            }
        });


        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MailMoreActivity.class);
                intent.putExtra("MailTitle", mailList.get(i).getMailTitle());
                intent.putExtra("MailContent", mailList.get(i).getMailContent());
                intent.putExtra("MailDate", mailList.get(i).getDate());
                intent.putExtra("MailSenderID", mailList.get(i).getSenderID());
                intent.putExtra("MailSenderName", mailList.get(i).getSenderName());
                context.startActivity(intent);
                //메일 터치하는 경우 이벤트 처리. 해당 메일의 정보를 다음 액티비티에 전달.
            }
        });

        return v;
    }


}
