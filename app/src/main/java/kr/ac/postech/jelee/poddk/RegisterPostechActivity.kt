package kr.ac.postech.jelee.poddk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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



        val years= arrayOf("1980",	"1981",	"1982",	"1983",	"1984",	"1985",	"1986",	"1987",	"1988",	"1989",	"1990",	"1991",	"1992",	"1993",	"1994",	"1995",	"1996",	"1997",	"1998",	"1999",	"2000",	"2001",	"2002",	"2003",	"2004",	"2005",	"2006",	"2007",	"2008",	"2009",	"2010",	"2011",	"2012",	"2013",	"2014",	"2015",	"2016",	"2017",	"2018")
        val yearSpinner = findViewById<Spinner>(R.id.user_birthday_year)
        val yearAdapter = ArrayAdapter<Any>(this,R.layout.support_simple_spinner_dropdown_item,years)
        yearSpinner.adapter=yearAdapter

        val months=arrayOf("1",	"2","3","4","5","6","7","8","9","10","11","12")
        val monthSpinner = findViewById<Spinner>(R.id.user_birthday_month)
        val monthAdapter = ArrayAdapter<Any>(this, android.R.layout.simple_spinner_dropdown_item,months)
        monthSpinner.adapter=monthAdapter

        val dates= arrayOf("1",	"2",	"3",	"4",	"5",	"6",	"7",	"8",	"9",	"10",	"11",	"12",	"13",	"14",	"15",	"16",	"17",	"18",	"19",	"20",	"21",	"22",	"23",	"24",	"25",	"26",	"27",	"28",	"29",	"30",	"31")
        val dateSpinner = findViewById<Spinner>(R.id.user_birthday_date)
        val dateAdapter = ArrayAdapter<Any>(this, android.R.layout.simple_spinner_dropdown_item,dates)
        dateSpinner.adapter=dateAdapter

        val majors=arrayOf("수학과","물리학과","화학과","생명과학과","신소재공학과","기계공학과","산업경영공학과","전자전기공학과","컴퓨터공학과","화학공학과","창의아이티융합공학과")
        val majorSpinner = findViewById<Spinner>(R.id.user_major)
        val majorAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,majors)
        majorSpinner.adapter=majorAdapter

        val questions=arrayOf("다른 이메일 주소는?","나의 보물 1호는?","나의 출신 고등학교는?","나의 출신 고향은?","나의 이상형은?","어머니 성함은?","아버지 성함은?","내가 가장 좋아하는 색깔은?")
        val questionSpinner = findViewById<Spinner>(R.id.user_pw_question)
        val questionAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,questions)
        questionSpinner.adapter=questionAdapter





        fun check_blank() : Boolean{
            return(user_name!=null&&user_id!=null&&user_pw!=null&&user_pw_check!=null&&user_birthday_year!=null&&user_birthday_month!=null&&user_birthday_date!=null&&user_major!=null&&user_pw_question!=null&&user_pw_answer!=null)
        }


        if(check_blank()){
                if(user_pw==user_pw_check){
                    val stringRequest = object : StringRequest(Request.Method.POST, "ftp://141.223.163.207/php/main.html",
                            Response.Listener<String>{response ->
                                try {
                                    val obj = JSONObject(response)
                                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                                } catch (e:JSONException){
                                    e.printStackTrace()
                                }

                    },
                    object : Response.ErrorListener{
                        override fun onErrorResponse(error: VolleyError) {
                            Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                        }
                    }){
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params.put("user_name", user_name.toString())
                            params.put("user_id", user_id.toString())
                            params.put("user_pw", user_pw.toString())
                            params.put("user_pw_check", user_pw_check.toString())
                            params.put("user_birthday_year", user_birthday_year.toString())
                            params.put("user_birthday_month", user_birthday_month.toString())
                            params.put("user_birthday_date", user_birthday_date.toString())
                            params.put("user_major", user_major.toString())
                            params.put("user_pw_question", user_pw_question.toString())
                            params.put("user_pw_answer", user_pw_answer.toString())
                            return params
                        }
                    }

                    VolleySingleton.instance?.addToRequestQueue(stringRequest)


                }
                else
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        else
            Toast.makeText(this, "입력되지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show()

    }


    }




