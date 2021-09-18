package com.example.myshop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.apply {
            upperLayout.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addStockFragment)
            }
            lowerLayout.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_sellFragment)
            }
        }
        return binding.root
    }
}