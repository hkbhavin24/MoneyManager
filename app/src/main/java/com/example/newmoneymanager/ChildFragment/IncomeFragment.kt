package com.example.newmoneymanager.ChildFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.newmoneymanager.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment() {
    lateinit var binding: FragmentIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeBinding.inflate(layoutInflater)



        return binding.root
    }
}