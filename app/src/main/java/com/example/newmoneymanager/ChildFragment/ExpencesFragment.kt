package com.example.newmoneymanager.ChildFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.FragmentExpencesBinding

class ExpencesFragment : Fragment() {
    lateinit var binding: FragmentExpencesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpencesBinding.inflate(layoutInflater)

        return binding.root
    }
}