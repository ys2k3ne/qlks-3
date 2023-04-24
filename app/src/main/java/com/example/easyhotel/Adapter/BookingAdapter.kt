package com.example.easyhotel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.R
import java.text.NumberFormat
import java.util.*

class BookingAdapter(private val bookingList: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomType: TextView = itemView.findViewById(R.id.txtRoomType)
        val checkIn: TextView = itemView.findViewById(R.id.txtCheckIn)
        val checkOut: TextView = itemView.findViewById(R.id.txtCheckOut)
        val price: TextView = itemView.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_booking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBooking = bookingList[position]
        holder.roomType.text = currentBooking.roomType
        holder.checkIn.text = currentBooking.checkIn
        holder.checkOut.text = currentBooking.checkOut
        val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        holder.price.text = priceFormat.format(currentBooking.price)
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }
}
