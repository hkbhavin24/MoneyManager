package com.example.newmoneymanager.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newmoneymanager.Adapter.UserDataAdapter
import com.example.newmoneymanager.Database.Database
import com.example.newmoneymanager.ModelClass.UserModel
import com.example.newmoneymanager.ParentFragment.CatagoryFragment
import com.example.newmoneymanager.ParentFragment.HomeFragment
import com.example.newmoneymanager.ParentFragment.TransactionFragment
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityMainBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        loadFragment(HomeFragment())
        runfragment()
        setContentView(binding.root)
    }

    fun runfragment() {
        binding.bottomnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.category -> {
                    loadFragment(CatagoryFragment())
                    true
                }

                R.id.transaction -> {
                    loadFragment(TransactionFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragpage, fragment)
            .commit()
    }
}