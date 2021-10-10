package com.example.myshop.presentation.ui.sell

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSellBinding
import com.example.myshop.presentation.ui.adapters.CartItemAdapter
import com.example.myshop.presentation.ui.adapters.SellItemAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SellFragment : Fragment() {

    private val sellViewModel: SellViewModel by viewModels()

    @Inject
    lateinit var sellAdapter: SellItemAdapter

    @Inject
    lateinit var itemAdapter: CartItemAdapter
    private lateinit var binding: FragmentSellBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false)

        itemAdapter.setOnItemClickListener {
            sellViewModel.removeFromCart(it)
        }

        binding.tvItemCount.setOnClickListener {
            findNavController().navigate(R.id.action_sellFragment_to_sellHistoryFragment)
        }

        sellViewModel.apply {
            /**
             * checking for the items in the cart and submitting that list to the cart recyclerview
             */
            sellCart.observe(viewLifecycleOwner, { list ->
                itemAdapter.cartList = list.toMutableList()
                itemAdapter.differ.submitList(list.toSet().toList())
                binding.apply {
                    rvCart.adapter = itemAdapter
                    tvCartCount.text = "${list.count()} items"
                }
            })

            lifecycleScope.launch {
                getDailyProfits().collect {
                    binding.tvItemCount.text = it.size.toString()
                }
            }

            /**
             * The sell items recyclerview will only be populated by items that match the search pattern
             */
//            itemsByName.observe(viewLifecycleOwner, { searchedItems ->
//                sellAdapter.setOnItemClickListener { ite ->
//                    addToCart(ite)
//                }
//                sellAdapter.differ.submitList(searchedItems.toSet().toList())
//                binding.rvSellItems.adapter = sellAdapter
//            })


        }

        binding.btnSell.setOnClickListener {
            sellViewModel.sellCart()
            sellViewModel.addDailyProfit()
        }

        var search: Job? = null

        binding.etSearch.addTextChangedListener { editable ->
            search?.cancel()
            search = lifecycleScope.launch {
                editable.let {
                    if (editable.toString().isNotEmpty()) {
                        val itemName = "%${editable.toString()}%"
                        sellViewModel.getItemsByName(itemName).collect { searchedItems->
                            sellAdapter.setOnItemClickListener { ite ->
                                sellViewModel.addToCart(ite)
                            }
                            sellAdapter.differ.submitList(searchedItems.take(1))
                            binding.rvSellItems.adapter = sellAdapter
                        }
                    }
                }
            }
        }

        handleBottomSheet()

        return binding.root
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