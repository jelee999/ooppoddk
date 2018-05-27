package kr.ac.postech.jelee.poddk

import android.app.AlertDialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Response
import com.android.volley.toolbox.Volley
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
        setContentView(R.layout.activity_find_user)

        val questionspinner = findViewById<View>(R.id.findPW_question) as Spinner
        val questionadapter = ArrayAdapter.createFromResource(this, R.array.questionList, R.layout.support_simple_spinner_dropdown_item)
        questionspinner.adapter = questionadapter


        val findIDButton = findViewById<Button>(R.id.findIDButton)
        val findPWButton = findViewById<Button>(R.id.findPWButton)


        var IDnameText = findViewById<EditText>(R.id.findID_name)
        var IDemailText = findViewById<EditText>(R.id.findID_email)
        var PWnameText = findViewById<EditText>(R.id.findPW_name)
        var PWidText = findViewById<EditText>(R.id.findPW_ID)
        var PWaText = findViewById<EditText>(R.id.findPW_answer)



        findIDButton.setOnClickListener{

            findIDname = IDnameText.text.toString()
            findIDemail = IDemailText.text.toString()


            if(!findIDname.isNullOrBlank()&&!findIDemail.isNullOrBlank()){
                val responseListener = Response.Listener<String> { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        val success = jsonResponse.getBoolean("success")
                        val id = jsonResponse.getString("id")

                        if (success) {
                            Toast.makeText(applicationContext,"아이디는 "+id,Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(applicationContext, "해당 계정이 존재하지 않습니다", Toast.LENGTH_LONG).show()
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val findIDRequest = FindIDRequest(findIDname, findIDemail, responseListener)

                val queue = Volley.newRequestQueue(this@FindUserActivity)
                queue.add(findIDRequest)

            }
            else
                Toast.makeText(applicationContext, "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show()

        }




        findPWButton.setOnClickListener{

            if(!PWnameText.text.toString().equals("")&&!PWidText.text.toString().equals("")&&!PWaText.text.toString().equals("")){
                val responseListener = Response.Listener<String> { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        val success = jsonResponse.getBoolean("success")
                        val id = jsonResponse.getString("id")
                        if (success) {
                            Toast.makeText(this,"아이디는 "+id,Toast.LENGTH_LONG).show()
                            val builder = AlertDialog.Builder(this@FindUserActivity)
                            builder.setMessage("아이디는"+id)
                            builder.setPositiveButton("예"){dialog, which->}


                        } else {
                            Toast.makeText(applicationContext, "해당 계정이 존재하지 않습니다", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val FindIDRequest = FindIDRequest(IDnameText.text.toString(), IDemailText.text.toString(), responseListener)

                val queue = Volley.newRequestQueue(this@FindUserActivity)
                queue.add(FindIDRequest)

            }
            else
                Toast.makeText(applicationContext, "입력되지 않은 항목이 있습니다", Toast.LENGTH_LONG).show()

        }





    }
}