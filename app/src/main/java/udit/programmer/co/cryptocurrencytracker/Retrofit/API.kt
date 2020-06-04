package udit.programmer.co.cryptocurrencytracker.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface API {
    @GET
    suspend fun getPath(@Url url: String): Call<String>
}