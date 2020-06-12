package udit.programmer.co.cryptocurrencytracker.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import udit.programmer.co.cryptocurrencytracker.Models.CoinModel

interface API {

    @GET("v1/ticker/?start=100&limit=10")
    suspend fun get(): Response<List<CoinModel>>

}