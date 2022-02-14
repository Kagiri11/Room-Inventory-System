package com.kagiri.myshop.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kagiri.myshop.R
import com.kagiri.myshop.databinding.ItemSellEntryBinding
import com.kagiri.myshop.domain.model.SoldEntry

class SellHistoryAdapter : RecyclerView.Adapter<SellHistoryAdapter.SellHistoryViewHolder>() {
    class SellHistoryViewHolder(val binding: ItemSellEntryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<SoldEntry>() {

        override fun areItemsTheSame(oldItem: SoldEntry, newItem: SoldEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SoldEntry, newItem: SoldEntry): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SellHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSellEntryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_sell_entry, parent, false)
        return SellHistoryViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SellHistoryViewHolder, position: Int) {
        val entry = differ.currentList[position]
        val stringAdapter = CartListStringAdapter(entry.soldItems)
        holder.binding.apply {
            tvSellEntryId.text = entry.id.toString()
            tvSellEntryTime.text = "Sell time: ${entry.timeSold.take(5)}"
            tvSellEntryProfit.text = "profit: ${entry.totalProfit} /="
            rvSoldItems.adapter = stringAdapter
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}