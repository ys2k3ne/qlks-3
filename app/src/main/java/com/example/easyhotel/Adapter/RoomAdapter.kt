package com.example.easyhotel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.R

class RoomAdapter (var ds:List<Room>): RecyclerView.Adapter<RoomAdapter.RoomHolderAdapter>(){
    class RoomHolderAdapter(itemview : View): RecyclerView.ViewHolder(itemview){
        private val roomName :TextView = itemview.findViewById(R.id.tv_room_name)
        private val roomPrice :TextView = itemview.findViewById(R.id.tv_room_price)
        private val roomImg :ImageView = itemview.findViewById(R.id.img_room)
        private val hotelAddress: TextView = itemview.findViewById(R.id.hotel_address)
        private val roomRate :RatingBar = itemview.findViewById(R.id.rating_bar)

        fun bind(rooms: Room) {
            roomName.text = rooms.roomName
            roomPrice.text = rooms.roomPrice.toString()
            roomImg.setImageResource(rooms.roomImage)
            hotelAddress.text = rooms.hotelAddress
            roomRate.rating = rooms.roomRating

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolderAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false    )
        return RoomHolderAdapter(view)
    }

    override fun onBindViewHolder(holder: RoomHolderAdapter, position: Int) {
        holder.bind(ds[position])
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}