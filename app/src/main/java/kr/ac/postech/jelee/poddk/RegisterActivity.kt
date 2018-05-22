package kr.ac.postech.jelee.poddk

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var postechButton = findViewById<View>(R.id.postechButton) as Button
        var nonpostechButton = findViewById<View>(R.id.nonpostechButton) as Button

        postechButton.setOnClickListener {
            val nextIntent = Intent(this, RegisterPostechActivity::class.java)
            startActivity(nextIntent)
        }

        nonpostechButton.setOnClickListener {
            val nextIntent = Intent(this, RegisterStudentActivity::class.java)
            startActivity(nextIntent)
        }
    }
}