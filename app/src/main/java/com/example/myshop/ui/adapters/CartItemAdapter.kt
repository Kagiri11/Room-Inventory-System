package com.example.myshop.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.databinding.ItemCartBinding
import com.example.myshop.model.Item

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>(){

    var cartList = mutableListOf<Item>()

    class CartItemViewHolder(val binding:ItemCartBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCartBinding= DataBindingUtil.inflate(layoutInflater, R.layout.item_cart,parent,false)
        return CartItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        val itemOccurrences = cartList.filter { it.name == item.name }.count()
        val occurrenceTotalPrice = cartList.filter{it.name == item.name}.sumOf { it.sellingPrice }

        holder.binding.apply {
            tvCartItemName.text = item.name
            tvCartItemQuantity.text = "($itemOccurrences)"
            tvCartItemPrice.text = occurrenceTotalPrice.toString()
            ivDecreaseItemQuantity.setOnClickListener {
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