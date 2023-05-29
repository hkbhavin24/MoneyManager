package com.example.newmoneymanager.ModelClass

class UserModel {

    var id = 0
    lateinit var name: String
    var amount: Int = 0
    lateinit var note: String
    var type = 0

    constructor(id: Int, name: String, amount: Int, note: String, type: Int) {
        this.id = id
        this.name = name
        this.amount = amount
        this.note = note
        this.type = type


    }

    constructor()

}