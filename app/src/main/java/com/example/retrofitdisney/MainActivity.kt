package com.example.retrofitdisney

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView=findViewById(R.id.textView)
    val llamada = getRetrofit().create(servicioApi::class.java).conseguirLista()

    if(llamada.isSuccessful){

        caratulas(llamada.body())
    }else{
        Log.i("ha habido problemas ","si")
    }
}


         fun caratulas(datos:disneyapi?) {

             val lista = StringBuilder()
             datos?.data?.forEach{
                 lista.append(it.name).append("\n")

             }

             textView.text = lista.toString()
             }




        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.disneyapi.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }}

