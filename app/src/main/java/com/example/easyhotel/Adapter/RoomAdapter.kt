package com.example.easyhotel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.LSInterface
import com.example.easyhotel.R
import com.squareup.picasso.Picasso

class RoomAdapter(var ds: List<Room>, private val onClickRoom: LSInterface) : RecyclerView.Adapter<RoomAdapter.RoomHolderAdapter>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener (clickListener: onItemClickListener){
        mListener = clickListener
    }
    class RoomHolderAdapter(itemview: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemview) {
        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
        private val roomName: TextView = itemview.findViewById(R.id.tv_room_name)
        private val roomPrice: TextView = itemview.findViewById(R.id.tv_room_price)
        private val roomImg: ImageView = itemview.findViewById(R.id.img_room)
        private val hotelAddress: TextView = itemview.findViewById(R.id.hotel_address)
        private val roomRate: RatingBar = itemview.findViewById(R.id.rating_bar)

        fun bind(rooms: Room) {
            roomName.text = rooms.roomName
            roomPrice.text = rooms.roomPrice
            Picasso.get().load(rooms.roomImage).into(roomImg)
            hotelAddress.text = rooms.hotelAddress
            roomRate.rating = rooms.roomRating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolderAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomHolderAdapter(view, mListener)
    }

    override fun onBindViewHolder(holder: RoomHolderAdapter, position: Int) {
        holder.bind(ds[position])
        holder.itemView.setOnClickListener{
            onClickRoom.onClickRoom(position)
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }

}
