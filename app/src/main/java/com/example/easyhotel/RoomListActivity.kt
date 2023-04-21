package com.example.easyhotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.Adapter.RoomAdapter
import com.example.easyhotel.databinding.ActivityRoomListBinding

private lateinit var binding:ActivityRoomListBinding
class RoomListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var ds = mutableListOf<Room>()
        ds.add(
            Room(
                1, "Phòng 1", "Hà Nội", 200.0, 3.5f,R.drawable.room1)
        )
        ds.add(
            Room(
                2, "Phòng 1", "Hội An", 200.0, 3.5f,R.drawable.room1)
        )
        ds.add(
            Room(
                3, "Phòng 1", "Đà Nẵng", 200.0, 3.5f,R.drawable.room1)
        )
        ds.add(
            Room(
                4, "Phòng 1", "Sài Gòn", 200.0, 3.5f,R.drawable.room1)
        )
        ds.add(
            Room(
                5, "Phòng 1", "Hải Phòng", 200.0, 3.5f,R.drawable.room1)
        )
        ds.add(
            Room(
                6, "Phòng 1", "Nha Trang", 200.0, 3.5f,R.drawable.room1)
        )
        val adapter = RoomAdapter(ds)
        binding.roomList.adapter=adapter
        binding.roomList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}
