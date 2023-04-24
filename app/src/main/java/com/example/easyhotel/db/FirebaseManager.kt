package com.example.easyhotel.db

import com.example.easyhotel.Adapter.Booking
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirebaseManager {
    private val db = FirebaseFirestore.getInstance()

    fun addBooking(booking: Booking, listener: OnCompleteListener<DocumentReference>) {
        db.collection("bookings")
            .add(booking)
            .addOnCompleteListener(listener)
    }


    fun updateBooking(id: String, booking: Booking, listener: OnCompleteListener<Void>) {
        db.collection("bookings")
            .document(id)
            .set(booking)
            .addOnCompleteListener(listener)
    }

    fun deleteBooking(id: String, listener: OnCompleteListener<Void>) {
        db.collection("bookings")
            .document(id)
            .delete()
            .addOnCompleteListener(listener)
    }

    fun getBookings(listener: OnCompleteListener<QuerySnapshot>) {
        db.collection("bookings")
            .get()
            .addOnCompleteListener(listener)
    }

    fun getBooking(id: String, listener: OnCompleteListener<DocumentSnapshot>) {
        db.collection("bookings")
            .document(id)
            .get()
            .addOnCompleteListener(listener)
    }
}
