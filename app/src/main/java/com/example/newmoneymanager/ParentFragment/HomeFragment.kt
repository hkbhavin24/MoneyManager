package com.example.newmoneymanager.ParentFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newmoneymanager.Adapter.UserDataAdapter
import com.example.newmoneymanager.ChildFragment.ExpencesFragment
import com.example.newmoneymanager.ChildFragment.IncomeFragment
import com.example.newmoneymanager.Database.Database
import com.example.newmoneymanager.DialogueFragment.AddExpencesFragment
import com.example.newmoneymanager.DialogueFragment.AddIncomeFragment
import com.example.newmoneymanager.ModelClass.UserModel
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = Database(context)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        list = database.showData()
        adapter = UserDataAdapter(list)
        runfragment()
        binding.btnAdd.setOnClickListener {
            onincome()
        }
        binding.btnexp.setOnClickListener {
            onexp()
        }
        UpdateTotal()
        return binding.root
    }

    fun runfragment() {
        fragfragment(IncomeFragment())
        binding.incexpnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.incomenav -> {
                    fragfragment(IncomeFragment())
                    true
                }

                else -> {
                    fragfragment(ExpencesFragment())
                    true
                }
            }
        }
    }

    fun fragfragment(incomeFragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.incomeloadfragment, incomeFragment)
            .commit()
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

    private fun onexp() {
        AddExpencesFragment().show(childFragmentManager, "my custom fragment")

    }

    private fun onincome() {
        AddIncomeFragment().show(childFragmentManager, "my CustomFragment")

    }
}