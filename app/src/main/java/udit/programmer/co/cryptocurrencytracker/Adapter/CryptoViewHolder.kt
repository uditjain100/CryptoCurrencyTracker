package udit.programmer.co.cryptocurrencytracker.Adapter

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*
import udit.programmer.co.cryptocurrencytracker.Common.Common
import udit.programmer.co.cryptocurrencytracker.Models.*
import java.lang.StringBuilder

class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(dataItem: DataItem?) {
        Picasso.get().load(
            StringBuilder(Common.imageUrl).append(dataItem?.symbol!!.toLowerCase()).append(".png")
                .toString()
        ).into(itemView.coinIcon)
        itemView.coin_name.text = dataItem.name
        itemView.coin_symbol.text = dataItem.symbol
        itemView.priceUsd.text = dataItem.quote?.uSD?.price.toString()
        itemView.oneHour.text = dataItem.quote?.uSD?.percentChange1h.toString()
        itemView.twentyFourHour.text = dataItem.quote?.uSD?.percentChange24h.toString()
        itemView.sevenDay.text = dataItem.quote?.uSD?.percentChange7d.toString()
    }
}