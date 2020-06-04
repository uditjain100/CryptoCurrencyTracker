package udit.programmer.co.cryptocurrencytracker.Retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    val retrofit_client = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val google_api = retrofit_client.create(API::class.java)
}