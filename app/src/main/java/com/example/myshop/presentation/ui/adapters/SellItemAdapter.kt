package com.example.myshop.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemSellBinding
import com.example.myshop.domain.model.Item

class SellItemAdapter : RecyclerView.Adapter<SellItemAdapter.SellItemViewHolder>(){

    class SellItemViewHolder(val binding: ItemSellBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSellBinding= DataBindingUtil.inflate(layoutInflater, R.layout.item_sell,parent,false)
        return SellItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            tvSellItemName.text = item.name
            ivAddToCart.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }

    }

    private var onItemClickListener : ((Item) -> Unit)?=null

    fun setOnItemClickListener(listener : ((Item) -> Unit)){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = differ.currentList.size
}