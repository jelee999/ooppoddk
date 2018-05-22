package kr.ac.postech.jelee.poddk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ljh45 on 2018-05-20.
 */

public class NoticeListAdapter extends BaseAdapter {
    private Context context;
    private List<Notice>  noticeList;

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {

        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v =View.inflate(context, R.layout.notice, null);
        TextView titleText = (TextView)v.findViewById(R.id.noticeTitle);
        TextView dateText = (TextView)v.findViewById(R.id.noticeDate);
        TextView contentText = (TextView)v.findViewById(R.id.noticeContent);

        titleText.setText(noticeList.get(i).getTitle());
        dateText.setText(noticeList.get(i).getDate());
        contentText.setText(noticeList.get(i).getContent());

        v.setTag(noticeList.get(i).getTitle());
        return v;
    }

}
