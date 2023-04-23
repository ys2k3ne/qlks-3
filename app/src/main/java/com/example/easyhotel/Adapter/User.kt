package com.example.easyhotel.Adapter

data class User(val userId: String, val name: String, val email: String, val phone: String, val password: String) {
    constructor() : this("", "", "", "", "")
}

