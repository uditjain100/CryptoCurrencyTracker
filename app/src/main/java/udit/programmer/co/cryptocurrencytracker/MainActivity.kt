package udit.programmer.co.cryptocurrencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import udit.programmer.co.cryptocurrencytracker.Adapter.CoinAdapter
import udit.programmer.co.cryptocurrencytracker.Common.Common
import udit.programmer.co.cryptocurrencytracker.Interface.LoadMore
import udit.programmer.co.cryptocurrencytracker.Models.DataItem
import udit.programmer.co.cryptocurrencytracker.Retrofit.Client
import udit.programmer.co.cryptocurrencytracker.Retrofit.Client02

class MainActivity : AppCompatActivity(), LoadMore {

    internal lateinit var list: MutableList<DataItem>
    internal lateinit var adapter: CoinAdapter

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
        swipeContainer.isRefreshing = true
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client(index).api.get() }
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
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client02().api.get() }
            if (response.isSuccessful) {
                response.body()?.let {
                    list.addAll(it)
                    adapter.updateData(it)
                }
            }
        }
    }
}