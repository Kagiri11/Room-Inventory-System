package com.example.myshop.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemSellEntryBinding
import com.example.myshop.model.SellEntry

class SellHistoryAdapter : RecyclerView.Adapter<SellHistoryAdapter.SellHistoryViewHolder>() {
    class SellHistoryViewHolder(val binding: ItemSellEntryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<SellEntry>() {

        override fun areItemsTheSame(oldItem: SellEntry, newItem: SellEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SellEntry, newItem: SellEntry): Boolean {
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