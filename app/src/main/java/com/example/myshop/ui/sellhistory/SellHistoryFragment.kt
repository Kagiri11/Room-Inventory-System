package com.example.myshop.ui.sellhistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSellHistoryBinding
import com.example.myshop.ui.adapters.SellHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellHistoryFragment : Fragment() {
    private val sellHistoryViewModel : SellHistoryViewModel by viewModels()
    private lateinit var binding: FragmentSellHistoryBinding
    private val sellHistoryAdapter = SellHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sell_history, container, false)
        binding.rvSellHistory.layoutManager = GridLayoutManager(requireContext(),2)

        sellHistoryViewModel.sellEntries.observe(viewLifecycleOwner, {  entries ->
            sellHistoryAdapter.differ.submitList(entries)
            binding.rvSellHistory.adapter = sellHistoryAdapter
        })

        return binding.root
    }

}