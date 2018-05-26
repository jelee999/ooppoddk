package kr.ac.postech.jelee.poddk

import android.app.Activity
import android.content.Intent

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var auto : SharedPreferences = getSharedPreferences("auto", Activity.MODE_PRIVATE)

        var loginID = auto.getString("inputID",null)
        var loginPW = auto.getString("inputPW",null)





        if(loginID != null&& loginPW != null){

            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    if (success) {
                        Toast.makeText(applicationContext, loginID+"님 자동로그인 되었습니다", Toast.LENGTH_LONG).show()
                        val nextIntent = Intent(this,MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "자동로그인 실패", Toast.LENGTH_LONG).show()
                        var auto : SharedPreferences = getSharedPreferences("auto",Activity.MODE_PRIVATE)
                        var autoLogin : SharedPreferences.Editor = auto.edit()
                        autoLogin.putString("inputID", null)
                        autoLogin.putString("inputPW", null)
                        autoLogin.commit()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val loginRequest = LoginRequest(loginID, loginPW, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)
        }





        else if(loginID == null && loginPW == null){


            var id = findViewById<EditText>(R.id.ID)
            var pw = findViewById<EditText>(R.id.PW)



            Login.setOnClickListener {

                if(!id.text.toString().equals("")&&!pw.text.toString().equals("")) {
                    val responseListener = Response.Listener<String> { response ->
                        try {
                            val jsonResponse = JSONObject(response)
                            val success = jsonResponse.getBoolean("success")
                            if (success) {
                                Toast.makeText(this, id.text.toString() + "님 환영합니다", Toast.LENGTH_LONG).show()

                                var auto: SharedPreferences = getSharedPreferences("auto", Activity.MODE_PRIVATE)
                                var autoLogin: SharedPreferences.Editor = auto.edit()
                                autoLogin.putString("inputID", id.text.toString())
                                autoLogin.putString("inputPW", pw.text.toString())

                                autoLogin.commit()

                                val nextIntent = Intent(this, MainActivity::class.java)
                                startActivity(nextIntent)

                                finish()

                            } else {
                                Toast.makeText(applicationContext, "ID 또는 비밀번호가 틀립니다", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    val loginRequest = LoginRequest(id.text.toString(), pw.text.toString(), responseListener)
                    val queue = Volley.newRequestQueue(this@LoginActivity)
                    queue.add(loginRequest)


                }
                else
                    Toast.makeText(applicationContext, "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show()
            }
        }




        registerButton.setOnClickListener {
            val nextIntent = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
        }


    }
}