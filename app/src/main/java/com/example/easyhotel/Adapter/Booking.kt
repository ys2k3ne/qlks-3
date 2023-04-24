package com.example.easyhotel.Adapter

data class Booking(
    val id: String?,
    val roomType: String?,
    val checkIn: String?,
    val checkOut: String?,
    val price: Double?
) {
    constructor() : this("", "", "", "", 0.0)
}

