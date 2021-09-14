package com.example.myshop.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myshop.R
import com.example.myshop.databinding.FragmentSellBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SellFragment : Fragment() {
    private lateinit var binding: FragmentSellBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sell, container, false)

        BottomSheetBehavior.from(binding.bottomSheetLayout).apply {
            peekHeight=200
            this.state=BottomSheetBehavior.STATE_COLLAPSED
            addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    binding.ivState.background=when(newState){
                        BottomSheetBehavior.STATE_COLLAPSED-> resources.getDrawable(R.drawable.ic_arrow_up,context?.theme)
                        else -> resources.getDrawable(R.drawable.ic_arrow_down,context?.theme)
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            }
            )
        }


        return binding.root
    }
}