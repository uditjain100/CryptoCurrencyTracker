package udit.programmer.co.cryptocurrencytracker.Retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import udit.programmer.co.cryptocurrencytracker.Models.DataItem

interface API {
    @GET
    suspend fun get(): Response<List<DataItem>>
}