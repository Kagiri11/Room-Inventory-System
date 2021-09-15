package com.example.myshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemSellBinding
import com.example.myshop.model.Item

class SellItemAdapter(val itemList:List<Item>) : RecyclerView.Adapter<SellItemAdapter.SellItemViewHolder>(){

    class SellItemViewHolder(val binding:ItemSellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSellBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_sell,parent,false)
        return SellItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.apply {
            tvSellItemName.text = item.name
            ivAddToCart.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}