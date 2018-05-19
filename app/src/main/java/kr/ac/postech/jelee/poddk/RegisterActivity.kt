package kr.ac.postech.jelee.poddk

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import kr.ac.postech.jelee.poddk.R
import kr.ac.postech.jelee.poddk.R.id.nonpostechButton
import kr.ac.postech.jelee.poddk.R.id.postechButton

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        postechButton.setOnClickListener {
            val nextIntent = Intent(this,RegisterPostechActivity::class.java)
            startActivity(nextIntent)
        }

        nonpostechButton.setOnClickListener {
            val nextIntent = Intent(this,RegisterStudentActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
