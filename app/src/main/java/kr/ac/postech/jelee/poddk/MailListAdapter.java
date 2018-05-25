package kr.ac.postech.jelee.poddk;

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
        /*ImageView profileImage = (ImageView) v.findViewById(R.id.mailProfile);*/
        TextView titleText = (TextView) v.findViewById(R.id.mailTitle);
        TextView dateText = (TextView) v.findViewById(R.id.mailDate);
        final TextView contentText = (TextView) v.findViewById(R.id.mailContent);
        TextView senderIDText = (TextView)v.findViewById(R.id.MailSenderID);
        //mail.xml 파일의 뷰로 각각 초기화

        titleText.setText(mailList.get(i).getMailTitle());
        dateText.setText(mailList.get(i).getDate());
        contentText.setText(mailList.get(i).getMailContent());
        senderIDText.setText(mailList.get(i).getSenderID());

        // i 번째 메일 내용 지정

        v.setTag(mailList.get(i).getMailTitle());


        final RelativeLayout mailLayout = (RelativeLayout) v.findViewById(R.id.OneMail);
        final Button deleteButton = (Button) v.findViewById(R.id.mailDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                AlertDialog dialog = builder.setMessage("메일이 삭제되었습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                mailList.add(mailList.remove(i));
                                notifyDataSetChanged();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                AlertDialog dialog = builder.setMessage("메일 삭제 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest("jim0307", mailList.get(i).getMailTitle() + "",
                        mailList.get(i).getMailContent()+"", mailList.get(i).getSenderID()+"",responseListener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(deleteRequest);
            }
        });




        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, MailMoreActivity.class);
                intent.putExtra("MailTitle", mailList.get(i).getMailTitle());
                intent.putExtra("MailContent", mailList.get(i).getMailContent());
                intent.putExtra("MailDate", mailList.get(i).getDate());
                intent.putExtra("MailSenderID", mailList.get(i).getSenderID());
                context.startActivity(intent);
                //메일 터치하는 경우 이벤트 처리. 해당 메일의 정보를 다음 액티비티에 전달.
            }
        });

        return v;
    }


}
