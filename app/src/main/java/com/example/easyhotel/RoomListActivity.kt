package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.Adapter.RoomAdapter
import com.example.easyhotel.Adapter.RoomAdapter.onItemClickListener
import com.example.easyhotel.databinding.ActivityRoomListBinding
import com.google.firebase.database.*

private lateinit var binding: ActivityRoomListBinding

class RoomListActivity : AppCompatActivity() {
    private lateinit var ds: ArrayList<Room>
    private lateinit var dbRef: DatabaseReference
    private lateinit var mAdapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo Adapter
        binding.roomList.layoutManager = LinearLayoutManager(this)
        binding.roomList.setHasFixedSize(true)

        ds = ArrayList<Room>() // Khởi tạo biến ds với một danh sách rỗng
        GetThongTin()
    }


    private fun GetThongTin() {
        dbRef = FirebaseDatabase.getInstance().getReference("hotels")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if (snapshot.exists()){
                    for(prdSnap in snapshot.children){
                        val prdData = prdSnap.getValue(Room::class.java)
                        ds.add(prdData!!)
                    }
                    // Cập nhật dữ liệu mới nhất vào Adapter
//                    mAdapter.updateData(ds)
                    val mAdapter = RoomAdapter(ds, object : LSInterface {
                        override fun onClickRoom(pos: Int) {
                            // xử lí sự kiện click đến activity_details.xml
                            val intent = Intent(this@RoomListActivity, DetailsActivity::class.java)
                            intent.putExtra("roomId", ds[pos].roomId)
                            startActivity(intent)

                            Toast.makeText(
                                this@RoomListActivity,
                                "Bạn chọn ${ds[pos].roomName}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                    // Gán adapter mới với RecyclerView
                    binding.roomList.adapter = mAdapter
                    // code lắng nghe sự kiện lên item rv
                    mAdapter.setOnItemClickListener(object : onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@RoomListActivity, DetailsActivity::class.java)
                            // put Extra
                            intent.putExtra("roomId", ds[position].roomId)
                            intent.putExtra("roomName", ds[position].roomName)
                            intent.putExtra("hotelAddress", ds[position].hotelAddress)
                            intent.putExtra("roomPrice", ds[position].roomPrice)
                            intent.putExtra("roomRating", ds[position].roomRating)
                            intent.putExtra("roomImage", ds[position].roomImage)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lí lỗi khi đọc dữ liệu từ Firebase thất bại
                Toast.makeText(
                    this@RoomListActivity,
                    "Đã có lỗi xảy ra khi đọc dữ liệu từ Firebase",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}
