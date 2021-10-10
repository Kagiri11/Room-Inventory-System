package com.example.myshop.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemSoldStringBinding

class CartListStringAdapter(private val listOfItems : List<String>) : RecyclerView.Adapter<CartListStringAdapter.CartListStringViewHolder>() {
    class CartListStringViewHolder(val binding:ItemSoldStringBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val dif= AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListStringViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding : ItemSoldStringBinding =   DataBindingUtil.inflate(layoutInflater, R.layout.item_sold_string,parent,false)
        return CartListStringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartListStringViewHolder, position: Int) {
        val name = listOfItems[position]
        holder.binding.tvSoldItemName.text=name
    }

    override fun getItemCount(): Int = listOfItems.size
}