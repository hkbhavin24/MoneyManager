package com.example.newmoneymanager.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.newmoneymanager.Activity.MainActivity
import com.example.newmoneymanager.ModelClass.UserModel


class Database(context: Context?) : SQLiteOpenHelper(context, "budget.db", null, 1) {
    var context = context
    override fun onCreate(p0: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE budget (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, amount INTEGER, note Text,type INTEGER)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun insertdata(name: String, amount: Int, note: String,  type: Int) {
        var db = writableDatabase
        var value = ContentValues()
        value.put("name", name)
        value.put("amount", amount)
        value.put("note", note)
        value.put("type", type)

        var iss = db.insert("budget", null, value)
        if (iss.toInt() == -1) {
            Toast.makeText(context, "data is not inserted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "data inserted", Toast.LENGTH_SHORT).show()
        }

    }


    @SuppressLint("Range")
    fun showData(): ArrayList<UserModel> {

        var modellist = ArrayList<UserModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM budget"
        var cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        for (x in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var amount = cursor.getInt(2)
            var note = cursor.getString(3)
            var type = cursor.getInt(cursor.getColumnIndex("type"))
            var model = UserModel(id, name, amount,note, type)
            modellist.add(model)
            cursor.moveToNext()
        }

        return modellist
    }
    fun updatedata(id: Int, name: String, amount: String, note: String) {
        var db = writableDatabase
        var values = ContentValues()
        values.put("name", name)
        values.put("amount", amount)
        values.put("note", note)
        db.update("budget", values, "id = $id", null)
    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        db.delete("budget", "id=$id", null)
    }
//    fun totalbalance(total: Int) {
//        var total = 0;
//
//        for (l in list) {
//            total += l.amount
//        }
//    }
}
