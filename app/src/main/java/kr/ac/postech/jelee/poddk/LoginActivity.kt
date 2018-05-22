package kr.ac.postech.jelee.poddk

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var id = findViewById<EditText>(R.id.ID)
        var pw = findViewById<EditText>(R.id.PW)

        var auto : SharedPreferences = getSharedPreferences("auto", Activity.MODE_PRIVATE)

        var loginID = auto.getString("inputID",null)
        var loginPW = auto.getString("inputPW",null)

        if(loginID != null&& loginPW != null){
            //if(loginID.equals(savedID)&&loginPW.equals(savedPW)){
                Toast.makeText(this,loginID+"님 자동로그인 되었습니다.", Toast.LENGTH_LONG).show()
                val nextIntent = Intent(this,MainActivity::class.java)
                startActivity(nextIntent)
                finish()
            //}
        }
        else if(loginID == null && loginPW == null){
            Login.setOnClickListener {

                //서버에 이 아이디랑 비밀번호가 있는지 이프문 추가할 것





                var auto : SharedPreferences = getSharedPreferences("auto",Activity.MODE_PRIVATE)
                var autoLogin : SharedPreferences.Editor = auto.edit()
                autoLogin.putString("inputID", id.text.toString())
                autoLogin.putString("inputPW", id.text.toString())

                autoLogin.commit()
                Toast.makeText(this, id.text.toString()+"님 환영합니다", Toast.LENGTH_LONG).show()

                val nextIntent = Intent(this,MainActivity::class.java)
                startActivity(nextIntent)
            }
        }



        registerButton.setOnClickListener {
            val nextIntent = Intent(this,RegisterActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
