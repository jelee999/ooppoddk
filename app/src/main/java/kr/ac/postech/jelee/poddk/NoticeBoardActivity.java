package kr.ac.postech.jelee.poddk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        noticeListView = (ListView)findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        /*noticeList.add(new Notice("공지사항 제목.", "공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용  ", "2018-05-20"));
        noticeList.add(new Notice("공지사항 제목.", "공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용  ", "2018-05-20"));
        noticeList.add(new Notice("공지사항 제목.", "공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 ", "2018-05-20"));
        noticeList.add(new Notice("공지사항 제목.", "공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 ", "2018-05-20"));
        noticeList.add(new Notice("공지사항 제목.공지사항 제목공지사항 제목공지사항 제목공지사항 제목공지사항 제목", "공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용 공지사항 내용  ", "2018-05-20"));
*/
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);

        noticeListView.setAdapter(adapter);

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "서버주소/Notice.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeTitle, noticeContent, noticeDate;
                while(count<jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeTitle = object.getString("noticeTitle");
                    noticeContent = object.getString("noticeContent");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeTitle, noticeContent, noticeDate);
                    noticeList.add(notice);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
