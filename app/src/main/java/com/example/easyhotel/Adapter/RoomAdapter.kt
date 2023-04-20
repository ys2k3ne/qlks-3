package com.example.easyhotel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.R
import com.squareup.picasso.Picasso

class RoomAdapter(private val rooms: List<Room>) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    // code adapter lắng nghe sự kiện
    // tạo class viewholder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomName: TextView =itemView.findViewById(R.id.tv_room_name)
        val roomPrice: TextView =itemView.findViewById(R.id.tv_room_price)
        val roomImg: ImageView =itemView.findViewById(R.id.img_room)
        val roomRate: RatingBar =itemView.findViewById(R.id.rating_bar)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = rooms[position]


        Picasso.get().load(room.roomImage).into(holder.roomImg)
        holder.roomName.text = room.roomName
        holder.roomPrice.text = room.roomPrice.toString()
        holder.roomRate.rating = room.roomRating
    }

    override fun getItemCount() : Int{
        return rooms.size
    }

//    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        fun bind(room: Room) {
//            // Cập nhật dữ liệu cho view tương ứng với phòng
//            itemView.findViewById<TextView>(R.id.tv_room_name).text = room.name
//            itemView.findViewById<TextView>(R.id.tv_room_price).text = room.price.toString()
//            itemView.findViewById<ImageView>(R.id.img_room).setImageResource(room.imageResource)
//            itemView.findViewById<RatingBar>(R.id.rating_bar).rating = room.rating
//        }
//    }
}
