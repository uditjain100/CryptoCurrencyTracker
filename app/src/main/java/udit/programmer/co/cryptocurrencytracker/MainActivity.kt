package udit.programmer.co.cryptocurrencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import udit.programmer.co.cryptocurrencytracker.Adapter.CoinAdapter
import udit.programmer.co.cryptocurrencytracker.Common.Common
import udit.programmer.co.cryptocurrencytracker.Interface.LoadMore
import udit.programmer.co.cryptocurrencytracker.Models.DataItem
import udit.programmer.co.cryptocurrencytracker.Retrofit.API

class MainActivity : AppCompatActivity(), LoadMore {

    internal var list = mutableListOf<DataItem>()
    internal lateinit var adapter: CoinAdapter

    internal val gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    internal lateinit var retrofit_client: Retrofit

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
        retrofit_client = Retrofit.Builder()
            .baseUrl(
                String.format(
                    "https://pro-api.coinmarketcap.com/",
                    index
                )
            )
            .addConverterFactory(GsonConverterFactory.create(this.gson)).build()
        val api = retrofit_client.create(API::class.java)
        swipeContainer.isRefreshing = true
        GlobalScope.launch(Dispatchers.Main) {
            val response =
                withContext(Dispatchers.IO) { api.get("3ad4a080-bb76-4b11-82f0-be28effcf237") }
            if (response.isSuccessful) {
                response.body()?.let {
                    list.addAll(it)
                    adapter.setLoaded()
                    adapter.updateData(it)
                    swipeContainer.isRefreshing = false
                }
            }
        }
    }

    private fun loadFirst10Coins() {
        retrofit_client = Retrofit.Builder()
            .baseUrl(String.format("https://pro-api.coinmarketcap.com/"))
            .addConverterFactory(GsonConverterFactory.create(this.gson)).build()
        val api = retrofit_client.create(API::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val response =
                withContext(Dispatchers.IO) { api.get("3ad4a080-bb76-4b11-82f0-be28effcf237") }
            Log.d("Ceased Meteor", "Yo Man I am Here")
            if (response.isSuccessful) {
                response.body()?.let {
                    list.addAll(it)
                    adapter.updateData(it)
                }
            }
        }
    }
}