package com.kagiri.myshop.presentation.ui.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kagiri.myshop.R
import com.kagiri.myshop.databinding.FragmentAddStockBinding
import com.kagiri.myshop.domain.model.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            addToStockCart(item,units)
            addStockViewModel.addItems(stockCart.toList())
            updateStockCart()

            lifecycleScope.launch {
                delay(2500)
                Toast.makeText(requireContext(),"Your stock of $itemName has been added",Toast.LENGTH_SHORT).show()
            }

        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
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

    fun delaySwitching() = lifecycleScope.launch {
        delay(Toast.LENGTH_SHORT.toLong())
    }
}