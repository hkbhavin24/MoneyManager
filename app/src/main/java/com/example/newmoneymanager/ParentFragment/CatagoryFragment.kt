package com.example.newmoneymanager.ParentFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.FragmentCatagoryBinding

class CatagoryFragment : Fragment() {
lateinit var binding : FragmentCatagoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatagoryBinding.inflate(layoutInflater)
        return binding.root
    }

  }