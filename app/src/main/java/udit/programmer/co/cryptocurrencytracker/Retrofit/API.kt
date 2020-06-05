package udit.programmer.co.cryptocurrencytracker.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import udit.programmer.co.cryptocurrencytracker.Models.CoinModel
import udit.programmer.co.cryptocurrencytracker.Models.DataItem

interface API {
    @GET("v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=")
    suspend fun get(@Query("KEY") KEY: String): Response<List<DataItem>>
}