package com.example.newmoneymanager.Adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newmoneymanager.ChildFragment.IncomeFragment
import com.example.newmoneymanager.Database.Database
import com.example.newmoneymanager.ModelClass.UserModel
import com.example.newmoneymanager.ParentFragment.CatagoryFragment
import com.example.newmoneymanager.ParentFragment.HomeFragment
import com.example.newmoneymanager.ParentFragment.TransactionFragment
import com.example.newmoneymanager.R

class UserDataAdapter(list: ArrayList<UserModel>) :
    RecyclerView.Adapter<UserDataAdapter.UserDataHolder>() {

    var modellist = list
    lateinit var database: Database
    lateinit var context: Context

    class UserDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtname = itemView.findViewById<TextView>(R.id.txtName)

        var btnUpdate = itemView.findViewById<Button>(R.id.btnUpdate)
        var btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
        var amount = itemView.findViewById<TextView>(R.id.amount)
        var txtnote = itemView.findViewById<TextView>(R.id.txtnote)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        context = parent.context
        return UserDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return modellist.size
    }

    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {

        holder.txtname.text = modellist.get(position).name
        holder.amount.text = modellist.get(position).amount.toString()
        holder.txtnote.text = modellist.get(position).note
        database = Database(context)
        holder.btnUpdate.setOnClickListener {
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.update_dialog)
            var idd = dialog.findViewById<TextView>(R.id.txtId)
            var edtname = dialog.findViewById<TextView>(R.id.name)
            var edtamount = dialog.findViewById<TextView>(R.id.amount)
            var btnUpdates = dialog.findViewById<Button>(R.id.btnUpdate)
            var edtnote = dialog.findViewById<TextView>(R.id.note)

            idd.text = modellist.get(position).id.toString()
            edtname.text = modellist.get(position).name
            edtamount.text = modellist.get(position).amount.toString()
            edtnote.text = modellist.get(position).note
            btnUpdates.setOnClickListener {
                database.updatedata(
                    modellist.get(position).id,
                    edtname.text.toString(),
                    edtamount.text.toString(),
                    edtnote.text.toString()
                )
                HomeFragment.updated()
                TransactionFragment.updated()
                dialog.dismiss()
            }
            dialog.show()
        }
        holder.btnDelete.setOnClickListener {
            database.deleteData(modellist.get(position).id)
            HomeFragment.updated()
            TransactionFragment.updated()
            IncomeFragment.updated()
        }
//        var total = 0;
//
//        for (l in MainActivity.list) {
//            total += l.amount
//        }
//
//        binding.balance.text = "$total"
    }

    fun update(list: ArrayList<UserModel>) {
        modellist = list
        notifyDataSetChanged()
    }

}