package udit.programmer.co.cryptocurrencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import udit.programmer.co.cryptocurrencytracker.Adapter.CoinAdapter
import udit.programmer.co.cryptocurrencytracker.Common.Common
import udit.programmer.co.cryptocurrencytracker.Interface.LoadMore
import udit.programmer.co.cryptocurrencytracker.Models.CoinModel
import udit.programmer.co.cryptocurrencytracker.Retrofit.API
import java.io.IOException

class MainActivity : AppCompatActivity(), LoadMore {

    internal var list = mutableListOf<CoinModel>()
    internal lateinit var adapter: CoinAdapter

    //    internal val gson =
//        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
//    internal lateinit var retrofit_client: Retrofit
    internal lateinit var http_client: OkHttpClient
    internal lateinit var request: Request
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeContainer.post { loadFirst10Coins() }
        swipeContainer.setOnRefreshListener {
            list.clear()
            loadFirst10Coins()
            setUpAdapter()
        }

        rv_layout.layoutManager = LinearLayoutManager(this)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter = CoinAdapter(rv_layout, list)
        rv_layout.adapter = adapter
        adapter.setloadMore(this)
    }

    override fun onLoadMore() {
        if (list.size <= Common.MAX_COIN_LOAD) {
            loadNext10Coins(list.size)
        } else {
            Toast.makeText(this, "Data Max is ${Common.MAX_COIN_LOAD}", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadNext10Coins(index: Int) {
        http_client = OkHttpClient()
        request = Request.Builder()
            .url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10", index))
            .build()
        swipeContainer.isRefreshing = true
        http_client.newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d("ERROR", e.toString())
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    val b = response.body()!!.toString()
                    val gson = Gson()
                    val newItems = gson.fromJson<List<CoinModel>>(
                        b,
                        object : TypeToken<List<CoinModel>>() {}.type
                    )
                    runOnUiThread {
                        list.addAll(newItems)
                        adapter.setLoaded()
                        adapter.updateData(list)
                        swipeContainer.isRefreshing = false
                    }
                }

            })

//        retrofit_client = Retrofit.Builder()
//            .baseUrl(
//                String.format(
//                    "https://api.coinmarketcap.com/",
//                    index
//                )
//            )
//            .addConverterFactory(GsonConverterFactory.create(this.gson)).build()
//        val api = retrofit_client.create(API::class.java)
//        swipeContainer.isRefreshing = true
//        GlobalScope.launch(Dispatchers.Main) {
//            val response =
//                withContext(Dispatchers.IO) { api.get() }
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    list.addAll(it)
//                    adapter.setLoaded()
//                    adapter.updateData(it)
//                    swipeContainer.isRefreshing = false
//                }
//            }
//        }
    }

    private fun loadFirst10Coins() {
        http_client = OkHttpClient()
        request = Request.Builder()
            .url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=0&limit=10"))
            .build()
        http_client.newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d("ERROR", e.toString())
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    val b = response.body()!!.string()
                    val c = object : TypeToken<List<CoinModel>>() {}.type
                    list = Gson().fromJson(b, c)
                    runOnUiThread { adapter.updateData(list) }
                }
            })

//        retrofit_client = Retrofit.Builder()
//            .baseUrl(String.format("https://api.coinmarketcap.com/"))
//            .addConverterFactory(GsonConverterFactory.create(this.gson)).build()
//        val api = retrofit_client.create(API::class.java)
//        GlobalScope.launch(Dispatchers.Main) {
//            val response =
//                withContext(Dispatchers.IO) { api.get() }
//            Log.d("CeasedMeteor", "Yo man 00")
//            if (response.isSuccessful) {
//                Log.d("CeasedMeteor", "Yo man 01")
//                response.body()?.let {
//                    list.addAll(it)
//                    adapter.updateData(it)
//                }
//            }
//            Log.d("CeasedMeteor", "Yo man 02")
//        }
    }

}