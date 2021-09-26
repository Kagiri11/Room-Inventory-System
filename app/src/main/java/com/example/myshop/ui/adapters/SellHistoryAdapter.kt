package com.example.myshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemSellEntryBinding
import com.example.myshop.model.SellEntry

class SellHistoryAdapter : RecyclerView.Adapter<SellItemAdapter.SellItemViewHolder>() {
    class SellHistoryViewHolder(val binding : ItemSellEntryBinding): RecyclerView.ViewHolder(binding.root)

    val itemList = listOf<String>()

    private val differCallback = object : DiffUtil.ItemCallback<SellEntry>() {

        override fun areItemsTheSame(oldItem: SellEntry, newItem: SellEntry): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: SellEntry, newItem: SellEntry): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SellItemAdapter.SellItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemSellEntryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_sell_entry,parent,false)
        return SellItemAdapter.SellItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellItemAdapter.SellItemViewHolder, position: Int) {
        val entry = differ.currentList[position]
        holder.binding.apply {
            tvSellEntryId.text = entry.id.toString()
            tvSellEntryTime.text = entry.sellTime
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}