package com.example.miniapp.controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miniapp.R
import com.example.miniapp.model.Api
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
    val intentKeyId = "codigo"
    fun createIntent(context: Context, codigo: String): Intent {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(intentKeyId, codigo)
        return intent
    }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var codigos =""
        var nombres =""
        var unidad_medidas=""
        var fechas =""
        var valores =0.0
        var api =  Api(codigos, nombres, unidad_medidas, fechas, valores)


        val codigo = api.codigo
        val nombre = api.nombre
        val unidad_medida = api.unidad_medida
        val fecha = api.fecha
        val valor = api.valor

        indicator_code.text = codigo

        indicator_name.text = nombre

        indicator_unit.text = unidad_medida

        indicator_date.text = fecha

        indicator_value.text = valor.toString()
    }
}
