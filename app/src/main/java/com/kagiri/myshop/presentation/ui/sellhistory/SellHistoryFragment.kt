package com.kagiri.myshop.presentation.ui.sellhistory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kagiri.myshop.R
import com.kagiri.myshop.databinding.FragmentSellHistoryBinding
import com.kagiri.myshop.presentation.ui.adapters.SellHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SellHistoryFragment : Fragment() {
    private val sellHistoryViewModel: SellHistoryViewModel by viewModels()
    private lateinit var binding: FragmentSellHistoryBinding

    @Inject
    lateinit var sellHistoryAdapter: SellHistoryAdapter

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd/M/yyyy")
    private val dateToday: String = format.format(Calendar.getInstance().time)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sell_history, container, false)
        binding.rvSellHistory.layoutManager = GridLayoutManager(requireContext(), 2)
        lifecycleScope.launch {
            sellHistoryViewModel.getSellEntries().collect { entriesList->
                val dailyProfit = entriesList.filter { it.timeSold.contains(dateToday) }.sumOf { it.totalProfit }
                sellHistoryAdapter.differ.submitList(entriesList)
                binding.rvSellHistory.adapter = sellHistoryAdapter
                binding.tvDailyProfit.text = dailyProfit.toString()
            }
        }
        return binding.root
    }

}