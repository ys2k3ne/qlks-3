package com.example.easyhotel.Adapter
import java.util.*
data class Booking(
    val bookingId: String,
    val roomId: String,
    val roomName: String,
    val checkInDate: Date,
    val checkOutDate: Date
)

