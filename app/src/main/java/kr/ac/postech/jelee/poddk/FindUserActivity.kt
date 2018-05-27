package kr.ac.postech.jelee.poddk

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_find_user.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject


class FindUserActivity : AppCompatActivity() {

    private var findIDname: String? = null
    private var findIDemail: String? = null
    private var findPWname: String? = null
    private var findPWid: String? = null
    private var findPWquestion : String?= null
    private var findPWanswer : String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val questionspinner = findViewById<View>(R.id.findPW_question) as Spinner
        val questionadapter = ArrayAdapter.createFromResource(this, R.array.questionList, R.layout.support_simple_spinner_dropdown_item)
        questionspinner.adapter = questionadapter


        val findIDButton = findViewById<Button>(R.id.findIDButton)



        var IDnameText = findViewById<EditText>(R.id.findID_name)
        var IDemailText = findViewById<EditText>(R.id.findID_email)
        var PWnameText = findViewById<EditText>(R.id.findPW_name)
        var PWidText = findViewById<EditText>(R.id.findPW_ID)
        var PWaText = findViewById<EditText>(R.id.findPW_answer)



        findIDButton.setOnClickListener{
            if(!IDnameText.text.toString().equals("")&&!IDemailText.text.toString().equals("")){
                val responseListener = Response.Listener<String> { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        val success = jsonResponse.getBoolean("success")
                        val id:String = jsonResponse.getString("id")
                        if (success) {
                            val builder = AlertDialog.Builder(this@FindUserActivity)
                            builder.setMessage("아이디는"+id)
                            builder.setPositiveButton("예"){dialog, which->}


                        } else {
                            Toast.makeText(applicationContext, "입력 정보가 틀립니다", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val FindIDRequest = FindID(IDnameText.text.toString(), IDemailText.text.toString(), responseListener)
                val queue = Volley.newRequestQueue(this@FindUserActivity)
                queue.add(FindIDRequest)

            }
            else
                Toast.makeText(applicationContext, "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show()

        }





    }
}