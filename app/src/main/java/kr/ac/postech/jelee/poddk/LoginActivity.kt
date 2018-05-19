package kr.ac.postech.jelee.poddk

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kr.ac.postech.jelee.poddk.R.id.registerButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerButton.setOnClickListener {
            val nextIntent = Intent(this,RegisterActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
