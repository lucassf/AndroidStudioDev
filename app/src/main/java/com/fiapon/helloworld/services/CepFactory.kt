package com.fiapon.helloworld.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepFactory {
    private val url = "https://viacep.com.br/ws/"
    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitService(): CepService {
        return retrofitFactory.create(CepService::class.java)
    }
}