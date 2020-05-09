package com.example.miniapp.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.R
import com.example.miniapp.builder.ServiceBuilder
import com.example.miniapp.dao.MiniAppUsers
import com.example.miniapp.model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    val TAG_LOGS = "miniapp"
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)

        Login.setOnClickListener {
            val mail: String = txtUser.text.toString()
            val password: String = txtPassword.text.toString()
            val userDes: MiniAppUsers = ServiceBuilder.buildService(MiniAppUsers::class.java)
            val solicitudUser: Call<User> = userDes.login(mail, password)

            solicitudUser.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) =
                    if (response.isSuccessful) {

                        val user: User? = response.body()
                        Log.i(TAG_LOGS, Gson().toJson(user))

                        Toast.makeText(this@LoginActivity, "Usuario ha ingresado correctamente", Toast.LENGTH_LONG)
                            .show()
                        action()
                    } else {
                        Toast.makeText(this@LoginActivity, " Usuario invalido", Toast.LENGTH_LONG).show()
                    }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
private fun action() {
    startActivity(Intent(this, SplashActivity::class.java))
}
}
