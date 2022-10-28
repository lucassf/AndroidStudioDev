package com.fiapon.helloworld

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.fiapon.helloworld.data.CepData
import com.fiapon.helloworld.services.CepFactory
import kotlinx.android.synthetic.main.activity_retrofit_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_layout)

        setSharedPreferences()
        btnCepSearch.setOnClickListener {
            cepRetrofitCall(editTextCep.editableText.toString())
        }
    }

    private fun setSharedPreferences() {
        val greetDict = this.getSharedPreferences("greeting", Context.MODE_PRIVATE)
        val name = greetDict.getString("name", "")
        val greeting = greetDict.getString("greet", "")

        val greetText: TextView = findViewById(R.id.txtUser)
        if (name == null || name.isEmpty()) greetText.text = "Anonymous"
        else if (greeting == "None") greetText.text = name
        else greetText.text = "$greeting $name"
    }

    private fun cepRetrofitCall(cep: String) {
        if (cep.length != 8) {
            Toast.makeText(
                this@RetrofitLayoutActivity, "Invalid CEP provided", Toast.LENGTH_SHORT
            ).show()
            return
        }
        val call = CepFactory().retrofitService().getCep(cep)
        progressBar.visibility = View.VISIBLE

        call.enqueue(object : Callback<CepData> {
            override fun onResponse(call: Call<CepData>, response: Response<CepData>) {
                if (response.body() != null && response.body()?.cep != null) {
                    editTextStreet.setText(response.body()?.logradouro)
                    editTextCity.setText(response.body()?.localidade)
                    editTextState.setText(response.body()?.uf)
                } else
                    Toast.makeText(
                        this@RetrofitLayoutActivity, "Invalid CEP provided", Toast.LENGTH_SHORT
                    ).show()
                progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<CepData>, t: Throwable) {
                Log.e("retrofit", "Error retrieving cep ${cep}: ${t?.message}")
                progressBar.visibility = View.INVISIBLE
            }

        })
    }
}
