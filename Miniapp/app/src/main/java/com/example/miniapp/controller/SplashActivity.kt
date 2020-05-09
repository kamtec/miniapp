package com.example.miniapp.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniapp.R
import com.example.miniapp.builder.ServiceBuilder
import com.example.miniapp.dao.MiniAppIndicator
import com.example.miniapp.model.Api
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    val TAG_LOGS = "miniapp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var apis:List<Api> = emptyList()
        val indDes: MiniAppIndicator = ServiceBuilder.buildService(MiniAppIndicator::class.java)
        val solicitudIndicator: Call<List<Api>>  = indDes.indicator(apis)
        solicitudIndicator.enqueue(object : Callback<List<Api>> {
            override fun onResponse(call: Call<List<Api>> , response: Response<List<Api>> ) =
                if (response.isSuccessful) {

                    val api: List<Api>? = response.body()
                    Log.i(TAG_LOGS, Gson().toJson(api))


                    startListActivity()
                } else {
                    Toast.makeText(this@SplashActivity, " Error al cargar datos", Toast.LENGTH_LONG).show()
                }

            override fun onFailure(call: Call<List<Api>> , t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@SplashActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)
    }




        private fun startListActivity() {
            startActivity(Intent(this, ListActivity::class.java))
        }



}