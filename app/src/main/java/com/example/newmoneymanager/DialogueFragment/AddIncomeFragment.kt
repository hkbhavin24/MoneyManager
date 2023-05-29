package com.example.newmoneymanager.DialogueFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.newmoneymanager.Activity.MainActivity
import com.example.newmoneymanager.ParentFragment.HomeFragment
import com.example.newmoneymanager.databinding.FragmentAddIncomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class AddIncomeFragment : DialogFragment() {
    lateinit var binding: FragmentAddIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddIncomeBinding.inflate(layoutInflater)
        binding.BtnDialogAdd.setOnClickListener {
            var id = binding.txtId.text.toString()
            var name = binding.Addname.text.toString()
            var amount = binding.Addamount.text.toString().toInt()
            var note = binding.AddNote.text.toString()
            HomeFragment.database.insertdata(name, amount, note, 1)
            HomeFragment.updated()
            dialog?.dismiss()

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}