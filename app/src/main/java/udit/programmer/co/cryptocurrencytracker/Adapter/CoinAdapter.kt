package udit.programmer.co.cryptocurrencytracker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import udit.programmer.co.cryptocurrencytracker.Interface.LoadMore
import udit.programmer.co.cryptocurrencytracker.Models.CoinModel
import udit.programmer.co.cryptocurrencytracker.R

class CoinAdapter(recyclerView: RecyclerView, var list: List<CoinModel>) :
    RecyclerView.Adapter<CryptoViewHolder>() {

    var loadMore: LoadMore? = null
    var isLoading: Boolean = false
    var visibleThreshold = 5
    var lastVisibleItem = 0
    var totalItemCount = 0

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayoutManager.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && (totalItemCount <= lastVisibleItem + visibleThreshold)) {
                    if (loadMore != null) loadMore!!.onLoadMore()
                    isLoading = true
                }
            }
        })
    }

    fun setloadMore(loadMore: LoadMore) {
        this.loadMore = loadMore
    }

    fun setLoaded() {
        isLoading = true
    }

    fun updateData(list: List<CoinModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.oneHour.setTextColor(
            if (list[position].percent_change_1h.toString().contains("-")) {
                R.color.Blue
            } else {
                R.color.colorAccent
            }
        )

        holder.itemView.oneHour.setTextColor(
            if (list[position].percent_change_24h.toString().contains("-")) {
                R.color.Brown
            } else {
                R.color.DarkGray
            }
        )

        holder.itemView.oneHour.setTextColor(
            if (list[position].percent_change_7d.toString().contains("-")) {
                R.color.BlanchedAlmond
            } else {
                R.color.Crimson
            }
        )

    }
}