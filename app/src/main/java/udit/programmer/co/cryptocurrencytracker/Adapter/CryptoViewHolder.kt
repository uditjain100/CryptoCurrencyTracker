package udit.programmer.co.cryptocurrencytracker.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*
import udit.programmer.co.cryptocurrencytracker.Common.Common
import udit.programmer.co.cryptocurrencytracker.Models.*
import java.lang.StringBuilder
import java.util.*

class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(coinModel: CoinModel?) {
        Picasso.get().load(
            StringBuilder(Common.imageUrl).append(coinModel?.symbol!!.toLowerCase(Locale.getDefault()))
                .append(".png")
                .toString()
        ).into(itemView.coinIcon)
        itemView.coin_name.text = coinModel.name
        itemView.coin_symbol.text = coinModel.symbol
        itemView.priceUsd.text = coinModel.price_usd.toString()
        itemView.oneHour.text = coinModel.percent_change_1h.toString()
        itemView.twentyFourHour.text = coinModel.percent_change_24h.toString()
        itemView.sevenDay.text = coinModel.percent_change_7d.toString()
    }
}