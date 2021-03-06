package kr.ac.postech.jelee.poddk


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register_postech.*
import org.json.JSONException
import org.json.JSONObject

class RegisterPostechActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_postech)

        var IDchecked : Int = 0
        var TorF : Boolean = true


        var name: String? = null
        var id: String? = null
        var pw: String? = null
        var pw_check: String? = null
        var birth_year: String? = null
        var birth_month: String? = null
        var birth_day: String? = null
        var major: String? = null
        var gender: String? = null
        var pw_question: String? = null
        var pw_answer: String? = null
        var email: String? = null
        var phonenum: String? = null


        val yearSpinner = findViewById<Spinner>(R.id.user_birthday_year)
        yearSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.yearsList))
        val monthSpinner = findViewById<Spinner>(R.id.user_birthday_month)
        monthSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.monthList))
        val dateSpinner = findViewById<Spinner>(R.id.user_birthday_date)
        dateSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.dateList))
        val majorSpinner = findViewById<Spinner>(R.id.user_major)
        majorSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.majorList1))
        val questionSpinner = findViewById<Spinner>(R.id.user_pw_question)
        questionSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.questionList))




        if (!user_woman.isChecked.not())
            gender = "female"
        else
            gender = "male"


        fun checkBlank(): Boolean {
            return (!name.isNullOrBlank() && !id.isNullOrBlank() && !pw.isNullOrBlank() && !pw_check.isNullOrBlank() && !pw_answer.isNullOrBlank()&& !email.isNullOrBlank()&& !phonenum.isNullOrBlank())

        }


        fun create() {

            name = user_name.text.toString()
            id = user_id.text.toString()
            pw = user_pw.text.toString()
            pw_check = user_pw_check.text.toString()
            birth_year = yearSpinner.selectedItem.toString()
            birth_month = monthSpinner.selectedItem.toString()
            birth_day = dateSpinner.selectedItem.toString()
            major = majorSpinner.selectedItem.toString()
            pw_question = questionSpinner.selectedItem.toString()
            pw_answer = user_pw_answer.text.toString()
            phonenum = user_phone.text.toString()
            email = user_email.text.toString()



            if (checkBlank()) {
                if (pw == pw_check) {


                    val stringRequest = object : StringRequest(Request.Method.POST, "http://ljh453.cafe24.com/podduk_checksignup.php",
                            Response.Listener<String> { response ->
                                try {
                                    val obj = JSONObject(response)
                                    val success = obj.getBoolean("success")


                                    if(success){
                                        Toast.makeText(applicationContext,"회원 가입에 성공했습니다",Toast.LENGTH_LONG).show()
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(applicationContext,"회원 가입에 실패했습니다",Toast.LENGTH_LONG).show()
                                    }
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }
                            },
                            object : Response.ErrorListener {
                                override fun onErrorResponse(error: VolleyError) {
                                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                                }
                            }) {
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params.put("name", name.toString())
                            params.put("id", id.toString())
                            params.put("pw", pw.toString())
                            params.put("pw_check", pw_check.toString())
                            params.put("email", email.toString())
                            params.put("phonenum", phonenum.toString())
                            params.put("gender", gender)
                            params.put("birth_year", birth_year.toString())
                            params.put("birth_month", birth_month.toString())
                            params.put("birth_day", birth_day.toString())
                            params.put("major", major.toString())
                            params.put("pw_question", pw_question.toString())
                            params.put("pw_answer", pw_answer.toString())
                            return params
                        }
                    }
                    VolleySingleton.instance?.addToRequestQueue(stringRequest)
                } else
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "입력되지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()
        }


        fun id_check(user_id: String?=null){
            var url : String = "ftp://141.223.163.207/var/www/podduk_checksignup.php"
            url = url+"?user_id="+user_id
            var ret = false


            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        try {
                            val obj = JSONObject(response)


                            ret = obj.getBoolean("success")

                                if (!ret) {
                                    IDchecked=1
                                    Toast.makeText(this, "사용가능한 ID입니다.", Toast.LENGTH_SHORT).show()
                                }
                                    else
                                    Toast.makeText(this, "이미 사용중인 ID입니다.", Toast.LENGTH_SHORT).show()


                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                        }
                    },
                    object : Response.ErrorListener {
                        override fun onErrorResponse(volleyError: VolleyError) {
                            Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                        }
                    })
            Volley.newRequestQueue(this).add(stringRequest)
        }

        validateButton.setOnClickListener{
            val input = id
            id_check(input)
        }
        registerButton.setOnClickListener {
            if(IDchecked==0)
                create()
            else
                Toast.makeText(this, "ID 확인을 해주세요.", Toast.LENGTH_SHORT).show()

        }
    }


}
