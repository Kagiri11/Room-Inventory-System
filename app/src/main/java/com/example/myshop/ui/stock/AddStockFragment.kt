package com.example.myshop.ui.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.myshop.R
import com.example.myshop.databinding.FragmentAddStockBinding
import com.example.myshop.model.Item
import com.example.myshop.ui.sell.SellViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStockFragment : Fragment() {
    private val addStockViewModel: AddStockViewModel by viewModels()
    private lateinit var binding: FragmentAddStockBinding

    val stockCart = mutableListOf<Item>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_stock, container, false)




        binding.btnAddStock.setOnClickListener {
            val itemName = binding.etStockName.text.toString()
            val itemBuyingPrice = binding.etStockBuyingPrice.text.toString().toDouble()
            val itemSellingPrice = binding.etStockSellingPrice.text.toString().toDouble()
            val units = binding.etUnits.text.toString().toInt()
            val item = Item(name = itemName,buyingPrice = itemBuyingPrice,sellingPrice = itemSellingPrice)
            addToStockCart(item,units.toInt())
            addStockViewModel.addItems(stockCart.toList())
            updateStockCart()
        }
        return binding.root
    }

    private fun addToStockCart(item: Item, units:Int){
        for (i in 1..units){
            stockCart.add(item)
        }
    }

    private fun updateStockCart(){
        stockCart.clear()
    }
}