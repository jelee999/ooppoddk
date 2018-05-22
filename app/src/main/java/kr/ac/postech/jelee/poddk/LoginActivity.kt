package kr.ac.postech.jelee.poddk

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var registerButton = findViewById<View>(R.id.registerButton) as Button

        registerButton.setOnClickListener {
            val nextIntent = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
        }




    }
}