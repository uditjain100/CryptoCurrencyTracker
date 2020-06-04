package udit.programmer.co.cryptocurrencytracker.Retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client(index: Int) {
    val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    val retrofit_client = Retrofit.Builder()
        .baseUrl(
            String.format(
                "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=3ad4a080-bb76-4b11-82f0-be28effcf237",
                index
            )
        )
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val api = retrofit_client.create(API::class.java)
}

class Client02() {
    val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    val retrofit_client = Retrofit.Builder()
        .baseUrl(String.format("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=3ad4a080-bb76-4b11-82f0-be28effcf237"))
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val api = retrofit_client.create(API::class.java)
}