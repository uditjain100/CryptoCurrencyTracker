package udit.programmer.co.cryptocurrencytracker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import udit.programmer.co.cryptocurrencytracker.Models.CoinModel
import udit.programmer.co.cryptocurrencytracker.Models.DataItem
import udit.programmer.co.cryptocurrencytracker.R

class CoinAdapter(var list: List<DataItem>) : RecyclerView.Adapter<CryptoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.oneHour.setTextColor(
            if (list[position].quote?.uSD?.percentChange1h.toString().contains("-")) {
                R.color.Blue
            } else {
                R.color.colorAccent
            }
        )

        holder.itemView.oneHour.setTextColor(
            if (list[position].quote?.uSD?.percentChange1h.toString().contains("-")) {
                R.color.Brown
            } else {
                R.color.DarkGray
            }
        )

        holder.itemView.oneHour.setTextColor(
            if (list[position].quote?.uSD?.percentChange1h.toString().contains("-")) {
                R.color.BlanchedAlmond
            } else {
                R.color.Crimson
            }
        )
        
    }
}