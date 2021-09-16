package com.example.myshop.ui.sellFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSellBinding
import com.example.myshop.model.Item
import com.example.myshop.ui.adapters.CartItemAdapter
import com.example.myshop.ui.adapters.SellItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellFragment : Fragment() {

    private val sellViewModel: SellViewModel by viewModels()
    private val sellAdapter = SellItemAdapter()
    private val itemAdapter = CartItemAdapter()
    private lateinit var binding: FragmentSellBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false)

        sellAdapter.setOnItemClickListener {
            addToCart(it)
        }

        itemAdapter.setOnItemClickListener {
            removeFromCart(it)
        }


        sellViewModel.sellCart2.observe(viewLifecycleOwner,{ list->
            binding.tvItemCount.text = "${list.sumOf { it.sellingPrice }} /="
            binding.tvCartCount.text = "${list.count()} items"
            itemAdapter.cartList=list.toMutableList()
            itemAdapter.differ.submitList(list)
            binding.rvCart.adapter=itemAdapter
        })

        sellViewModel.items.observe(viewLifecycleOwner, {
            sellAdapter.differ.submitList(it)
            binding.rvSellItems.adapter = sellAdapter
        })

        handleBottomSheet()

        return binding.root
    }

    private fun addToCart(item: Item) {
        sellViewModel.addToCart(item)
    }

    private fun removeFromCart(item: Item) {
        sellViewModel.removeFromCart(item)
    }

    private fun handleBottomSheet() {
        BottomSheetBehavior.from(binding.bottomSheetLayout).apply {
            peekHeight = 150
            this.state = BottomSheetBehavior.STATE_COLLAPSED
            addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    binding.ivState.background = when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> resources.getDrawable(
                            R.drawable.ic_arrow_up,
                            context?.theme
                        )
                        else -> resources.getDrawable(R.drawable.ic_arrow_down, context?.theme)
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            }
            )
        }
    }

}