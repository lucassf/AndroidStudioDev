package com.fiapon.helloworld.services

import com.fiapon.helloworld.data.CepData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {

    @GET("{cep}/json/")
    fun getCep(@Path("cep") cep: String): Call<CepData>

    @GET("{estado}/{cidade}/{endereco}/json/")
    fun getRce(
        @Path("estado") estado: String,
        @Path("cidade") cidade: String,
        @Path("endereco") endereco: String
    ): Call<List<CepData>>
}