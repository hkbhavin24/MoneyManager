package com.example.newmoneymanager.DialogueFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.newmoneymanager.Activity.MainActivity
import com.example.newmoneymanager.ParentFragment.HomeFragment
import com.example.newmoneymanager.databinding.FragmentAddExpencesBinding


class AddExpencesFragment : DialogFragment() {
    lateinit var binding: FragmentAddExpencesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddExpencesBinding.inflate(layoutInflater)
        binding.BtnDialogAdd.setOnClickListener {
            var name = binding.Addname.text.toString()
            var amount = binding.Addamount.text.toString().toInt()
            var note = binding.Addnote.text.toString()

            HomeFragment.database.insertdata(name, amount,note,0)
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