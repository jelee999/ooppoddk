package kr.ac.postech.jelee.poddk

import android.app.Activity
import android.content.Intent

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject


class LoginActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var login_result: Int = 1
        var id: String? = null
        var pw: String? = null

        var a:Int


        id = ID.text.toString()
        pw = PW.text.toString()

        /*var id = findViewById<EditText>(R.id.ID)
        var pw = findViewById<EditText>(R.id.PW)*/


        var auto: SharedPreferences = getSharedPreferences("auto", Activity.MODE_PRIVATE)

        var saveID = auto.getString("inputID", null)
        var savePW = auto.getString("inputPW", null)







        if (saveID == null && savePW == null) {
            if (!id.isNullOrBlank() && !pw.isNullOrBlank()) {
                val stringRequest = object : StringRequest(Request.Method.POST, "http://",
                        Response.Listener<String> { response ->
                            try {
                                val obj = JSONObject(response)

                                login_result = obj.getInt("login_result")

                                if (login_result == 0) { //무슨 무슨 실패

                                }
                                else if (login_result == 1) { //무슨 무슨 실패
                                }
                            }
                            catch (e: JSONException) {
                                e.printStackTrace()
                                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                            }

                        },
                        object : Response.ErrorListener {
                            override fun onErrorResponse(volleyError: VolleyError) {
                                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                            }
                        }){
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params.put("user_id", id)
                        params.put("user_pw", pw)
                        return params
                    }
                    fun save(){
                        var auto : SharedPreferences = getSharedPreferences("auto",Activity.MODE_PRIVATE)
                        var autoLogin : SharedPreferences.Editor = auto.edit()

                        autoLogin.putString("inputID", id)
                        autoLogin.putString("inputPW", pw)

                        autoLogin.commit()
                    }
                }
                //adding request to queue
                VolleySingleton.instance?.addToRequestQueue(stringRequest)
            } else
                Toast.makeText(this, "입력되지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()
        }

/*
        fun check_login(){

        }

        Login.setOnClickListener {

            check_login()

            if(saveID != null&& savePW != null){
                //if(loginID.equals(savedID)&&loginPW.equals(savedPW)){
                Toast.makeText(this,saveID+"님 자동로그인 되었습니다.", Toast.LENGTH_LONG).show()
                val nextIntent = Intent(this,MainActivity::class.java)
                startActivity(nextIntent)
                finish()
                //}
            }
            //서버에 이 아이디랑 비밀번호가 있는지 이프문 추가할 것
            else if(saveID == null && savePW == null){

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
            val nextIntent = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
        }
*/
    }
}