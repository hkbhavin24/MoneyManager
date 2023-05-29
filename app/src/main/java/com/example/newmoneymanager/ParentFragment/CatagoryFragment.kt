package com.example.newmoneymanager.ParentFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newmoneymanager.Adapter.UserDataAdapter
import com.example.newmoneymanager.Database.Database
import com.example.newmoneymanager.ModelClass.UserModel
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.FragmentCatagoryBinding
import com.example.newmoneymanager.databinding.FragmentHomeBinding

class CatagoryFragment : Fragment() {
lateinit var binding : FragmentCatagoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatagoryBinding.inflate(layoutInflater)


        return binding.root
    }
    companion object {
        lateinit var database: Database
        lateinit var binding: FragmentHomeBinding
        lateinit var adapter: UserDataAdapter
        var list = ArrayList<UserModel>()

        fun updated() {
            list.clear()
            list = database.showData()
            adapter.update(list)
            UpdateTotal()
        }

        fun UpdateTotal() {
            var total = 0;
            var income_total = 0;
            var expence_total = 0;

            for (l in list) {
                total += l.amount
                if (l.type == 1) {
                    income_total += l.amount
                } else if (l.type == 0) {
                    expence_total += l.amount
                }

                binding.balance.text = "${income_total - expence_total}"

            }
        }
    }

  }