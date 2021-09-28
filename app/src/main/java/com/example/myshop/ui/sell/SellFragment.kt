package com.example.myshop.ui.sell

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSellBinding
import com.example.myshop.model.Item
import com.example.myshop.ui.adapters.CartItemAdapter
import com.example.myshop.ui.adapters.SellItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SellFragment : Fragment() {

    private val sellViewModel: SellViewModel by viewModels()
    private val sellAdapter = SellItemAdapter()
    private val itemAdapter = CartItemAdapter()
    private lateinit var binding: FragmentSellBinding

    var itemsInDatabase = listOf<Item>()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false)

        itemAdapter.setOnItemClickListener {
            removeFromCart(it)
        }

        binding.tvItemCount.setOnClickListener {
            findNavController().navigate(R.id.action_sellFragment_to_sellHistoryFragment)
        }

        //Here, I am checking for the items in the cart and submitting that list to the cart recyclerview
        sellViewModel.sellCart2.observe(viewLifecycleOwner,{ list->
            binding.tvItemCount.text = "${list.sumOf { it.sellingPrice }} /="
            binding.tvCartCount.text = "${list.count()} items"
            itemAdapter.cartList=list.toMutableList()
            itemAdapter.differ.submitList(list.toSet().toList())
            binding.rvCart.adapter=itemAdapter
        })

        sellViewModel.items.observe(viewLifecycleOwner, { itemsInDataBase->
            itemsInDatabase=itemsInDataBase
        })

        sellViewModel.sellCart2.observe(viewLifecycleOwner,{ itemsInCart->
            binding.btnSell.setOnClickListener {
                itemsInCart.forEach { cartItem->
                    val sameItemInDb = itemsInDatabase.first { it.name ==  cartItem.name}
                    sellViewModel.deleteItem(sameItemInDb)
                }

            }
        })

//        binding.btnSell.setOnClickListener {
//            sellViewModel.sellCart()
//        }

        var search : Job? =null

        binding.etSearch.addTextChangedListener { editable->
            search?.cancel()
            search = lifecycleScope.launch {
                editable.let {
                    if(editable.toString().isNotEmpty()){
                        val itemName = "%${editable.toString()}%"
                        sellViewModel.searchItemByName(itemName)
                    }
                }
            }
        }

        //The sell items recyclerview will only be populated by items that match
        //the search pattern
        sellViewModel.itemsByName.observe(viewLifecycleOwner,{ searchedItems ->
            sellAdapter.setOnItemClickListener {ite->
                addToCart(ite)
            }
            sellAdapter.differ.submitList(searchedItems.toSet().toList())
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

    /**
     * This function is responsible for drawing the bottom sheet that hosts that items cart
     */
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