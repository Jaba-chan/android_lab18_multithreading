package ru.evgenykuzakov.lab18

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class СurrencyAdapter(private val currencies: List<CurrencyToRuble>): RecyclerView.Adapter<СurrencyAdapter.CurrencyHolder>() {
    class CurrencyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvCurrencyName: TextView
        var tvAmount: TextView
         init {
                tvCurrencyName = itemView.findViewById(R.id.tvCurrencyName)
                tvAmount = itemView.findViewById(R.id.tvAmount)
         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.tvCurrencyName.text = currencies[position].name
        holder.tvAmount.text = currencies[position].value
    }

    override fun getItemCount(): Int {
        return currencies.size
    }
}