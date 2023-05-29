package com.example.newmoneymanager.ParentFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newmoneymanager.Adapter.UserDataAdapter
import com.example.newmoneymanager.Database.Database
import com.example.newmoneymanager.ModelClass.UserModel
import com.example.newmoneymanager.databinding.FragmentTransactionBinding


class TransactionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        database = Database(context)
        list = database.showData()
        adapter = UserDataAdapter(list)
        adapter.update(list)
        binding.rcvincome.layoutManager = LinearLayoutManager(context)
        binding.rcvincome.adapter = adapter
        UpdateTotal()
        return binding.root
    }
    companion object {

        lateinit var adapter: UserDataAdapter
        lateinit var binding: FragmentTransactionBinding
        var list = ArrayList<UserModel>()
        lateinit var database: Database
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
                HomeFragment.binding.balance.text = "${income_total - expence_total}"

            }
        }
        fun updated() {
            list.clear()
            list = database.showData()
            adapter.update(list)
            UpdateTotal()
        }
    }
}