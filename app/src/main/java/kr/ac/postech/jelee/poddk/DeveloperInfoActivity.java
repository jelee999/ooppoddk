package kr.ac.postech.jelee.poddk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class DeveloperInfoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView jeFacebook;
    TextView jhFacebook;
    TextView smFacebook;
    TextView jyFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        jeFacebook = (TextView) findViewById(R.id.jeFacebook);
        jhFacebook = (TextView) findViewById(R.id.jhFacebook);
        smFacebook = (TextView) findViewById(R.id.smFacebook);
        jyFacebook = (TextView) findViewById(R.id.jyFacebook);

        jeFacebook.setOnClickListener(this);
        jhFacebook.setOnClickListener(this);
        smFacebook.setOnClickListener(this);
        jyFacebook.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == jeFacebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100021472042981&ref=br_rs"));
            startActivity(intent);
        } else if (view == jhFacebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100004599844560&ref=br_rs"));
            startActivity(intent);
        } else if (view == smFacebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100004061031516&ref=br_rs"));
            startActivity(intent);
        } else if (view == jyFacebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100006157878479&ref=br_rs"));
            startActivity(intent);
        }
    }
}
